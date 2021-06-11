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
	
	public abstract ResponseEntity<?> update(long id, Product product);
	
	public abstract ResponseEntity<?> findById(long id);
	
	public abstract ResponseEntity<?> delete(long id);
	
	public abstract List<Product> findByDateAndNameOrDescription(String min_value, String max_value, String q);
	
}
