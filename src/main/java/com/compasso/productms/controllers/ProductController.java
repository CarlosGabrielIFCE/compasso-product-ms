package com.compasso.productms.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.productms.models.Product;
import com.compasso.productms.services.ProductService;

/**
 * Controller for Product Entity
 * @author Carlos Gabriel
 *
 */
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
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
			map.put("status_code", 400);
			map.put("message", "Error on creating a Product.");
		} else {
			map.put("", productCreated);
		}
		
		return new ResponseEntity<>(Objects.isNull(productCreated) ? map: productCreated, Objects.isNull(productCreated) ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED);
		
	}

}
