package com.compasso.productms.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.compasso.productms.models.Product;

/**
 * Interface chamada pelo Controller
 * Lógicas de negócio se encontram no ServiceImpl
 * @author Carlos Gabriel
 *
 */
public interface ProductService {
	
	public abstract ResponseEntity<?> save(Product product);
	
	public abstract ResponseEntity<?> update(String id, Product product);
	
	public abstract ResponseEntity<?> findById(String id);
	
	public abstract ResponseEntity<?> delete(String id);
	
	public abstract List<Product> findByDateAndNameOrDescription(String min_value, String max_value, String q);
	
}
