package com.compasso.productms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.productms.models.Product;
import com.compasso.productms.repository.ProductRepository;
import com.compasso.productms.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller da Entidade Product
 * @author Carlos Gabriel
 *
 */
@RestController
@RequestMapping(value="/")
@Api(value="API Rest Products")
@CrossOrigin(origins="*")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	/**
	 * Função do Controller que é chamada
	 * no momento de salvar um produto
	 * @param product
	 * @return
	 */
	@PostMapping("products")
	@ApiOperation(value="Verifica os campos do produto, caso válidos, realiza a inserção no banco.")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		return productService.save(product);
	}
	
	/**
	 * Função do Controller que é chamada
	 * no momento de atualizar um produto
	 * @param id
	 * @return
	 */
	@PutMapping("products/{id}")
	@ApiOperation(value="Verifica se o produto e os seus campos existem e são válidos, caso ok, realiza a atualização no banco.")
	public ResponseEntity<?> updateProduct(@PathVariable(value="id") String id, @RequestBody Product product) {
		return productService.update(id, product);
	}
	
	/**
	 * Função do Controller que é chamada
	 * no momento de buscar um produto
	 * pelo seu id
	 * @param id
	 * @return
	 */
	@GetMapping("products/{id}")
	@ApiOperation(value="Busca um produto pelo seu id.")
	public ResponseEntity<?> getProduct(@PathVariable(value="id") String id) {
		return productService.findById(id);
	}
	
	/**
	 * Função do Controller que é chamada
	 * no momento de buscar todos os
	 * produtos
	 * @return
	 */
	@GetMapping("products")
	@ApiOperation(value="Busca todos os produtos presentes no banco de dados.")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}
	
	/**
	 * Função do Controller que é chamada
	 * no momento de deletar um produto
	 * @param product
	 * @return
	 */
	@DeleteMapping("products/{id}")
	@ApiOperation(value="Verifica se o produto existe, caso exista, realiza a deleção no banco.")
	public ResponseEntity<?> deleteProduct(@PathVariable(value="id") String id) {
		return productService.delete(id);
	}
	
	/**
	 * Função do Controller que é chamada
	 * no momento de buscar um produto
	 * pelo preço ou pelos campos
	 * @param min_value
	 * @param max_value
	 * @param q
	 * @return
	 */
	@GetMapping("products/search")
	@ResponseBody
	@ApiOperation(value="Realiza a busca de produtos no banco pelos campos min_value, max_value e q.")
	public List<Product> getByParameters(@RequestParam(required=false) String min_value, @RequestParam(required=false) String max_value, @RequestParam(required=false) String q) {
		return productService.findByDateAndNameOrDescription(min_value, max_value, q);
	}

}
