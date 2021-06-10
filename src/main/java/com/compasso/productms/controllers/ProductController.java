package com.compasso.productms.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.productms.models.Product;
import com.compasso.productms.repository.ProductRepository;
import com.compasso.productms.services.ProductService;

/**
 * Controller for Product Entity
 * @author Carlos Gabriel
 *
 */
@RestController
public class ProductController {
	
	private static final int BAD_REQUEST = 400;
	private static final int NOT_FOUND = 404;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	/**
	 * Save a product
	 * @param product
	 * @return
	 */
	@PostMapping("/products")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		Map<String, Object> map = new HashMap<>();
		
		Product productCreated = productService.save(product);
		
		if (Objects.isNull(productCreated)) {
			map.put("status_code", BAD_REQUEST);
			map.put("message", "Error on creating a Product.");
		}
		
		return new ResponseEntity<>(Objects.isNull(productCreated) ? map: productCreated, Objects.isNull(productCreated) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
		
	}
	
	/**
	 * Update a product receiving the 
	 * id on header
	 * @param id
	 * @return
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value="id") long id, @RequestBody Product product) {
		Map<String, Object> map = new HashMap<>();
		product.setId(id);
		Product productToUpdate = productRepository.findById(id);
		
		if (Objects.isNull(productToUpdate)) {
			map.put("status_code", NOT_FOUND);
			map.put("message", "Product not found.");
		}
		
		Product productUpdated = productService.update(product);
		
		if (Objects.isNull(productUpdated)) {
			map.put("status_code", BAD_REQUEST);
			map.put("message", "Error on updating a Product.");
		}
		
		return new ResponseEntity<>(Objects.isNull(productUpdated) ? map: productUpdated, Objects.isNull(productUpdated) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
	}

}
