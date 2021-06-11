package com.compasso.productms.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.compasso.productms.models.Product;
import com.compasso.productms.repository.ProductRepository;

/**
 * Serviço onde se encontram
 * as lógicas de implementação
 * @author Carlos Gabriel
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final int BAD_REQUEST = 400;
	private static final int NOT_FOUND = 404;
	
	@Autowired
	ProductRepository productRepository;
	
	/**
	 * Método responsável por salvar um produto
	 * Verifica os campos, caso válidos
	 * Salva o produto na base
	 */
	@Override
	public ResponseEntity<?> save(Product product) {
		Map<String, Object> map = new HashMap<>();
		
		if (!verifyFields(product)) {
			map.put("status_code", BAD_REQUEST);
			map.put("message", "Error on creating a Product.");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		
		Product productCreated = productRepository.save(product);
		
		return new ResponseEntity<>(productCreated, HttpStatus.OK);		
	}
	
	/**
	 * Método responsável por atualizar um produto
	 * Verifica se o produto existe, caso válido
	 * Verifica os campos, caso válidos
	 * Atualiza o produto na base
	 */
	@Override
	public ResponseEntity<?> update(long id, Product product) {
		Map<String, Object> map = new HashMap<>();
		Product productToUpdate = productRepository.findById(id);
		
		if (Objects.isNull(productToUpdate)) {
			map.put("status_code", NOT_FOUND);
			map.put("message", "Product not found.");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}else {
			product.setId(id);
		}
		
		if (!verifyFields(product)) {
			map.put("status_code", BAD_REQUEST);
			map.put("message", "Error on validating a Product.");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}
		
		Product productUpdated = productRepository.save(product);
		
		return new ResponseEntity<>(productUpdated, HttpStatus.OK);
	}
	
	/**
	 * Método responsável por buscar um produto
	 * Caso não encontrado, retorna um HTTP 404
	 */
	public ResponseEntity<?> findById(long id) {
		Product productToGet = productRepository.findById(id);
		
		if (Objects.isNull(productToGet)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(productToGet, HttpStatus.OK);
	}
	
	/**
	 * Método responsável por remover um produto
	 * Verifica se o produto existe, caso valido
	 * deleta o produto na base
	 */
	public ResponseEntity<?> delete(long id) {
		Product productToDelete = productRepository.findById(id);
		
		if (Objects.isNull(productToDelete)) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		productRepository.deleteById(id);
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	/**
	 * Método responsável por buscar um produto
	 * por query params
	 * Caso os campos min_value e max_value não venham preenchidos
	 * são assumidos valores padrão
	 * Caso o campo "q" não venha preenchido, busca apenas pelo preço
	 * Caso contrário, busca pelo preço e pelo campo
	 * Salva o produto na base
	 */
	public List<Product> findByDateAndNameOrDescription(String min_value, String max_value, String q) {
		BigDecimal minValue = !Objects.isNull(min_value) ? new BigDecimal(min_value) : new BigDecimal(0);
		BigDecimal maxValue = !Objects.isNull(max_value) ? new BigDecimal(max_value) : new BigDecimal(1000000);
		
		if (Objects.isNull(q)) {
			return productRepository.findByPriceFields(minValue, maxValue);
		}else {
			return productRepository.findByAllFields(minValue, maxValue, q);
		}
	}
	
	/**
	 * Método responsável por realizar a validação
	 * dos campos do produto
	 */
	public Boolean verifyFields(Product product) {
		if (product.getName().isEmpty()) {
			return false;
		}else if (product.getDescription().isEmpty()) {
			return false;
		}else if (product.getPrice().intValue() < 0) {
			return false;
		}
		
		return true;
	}

}
