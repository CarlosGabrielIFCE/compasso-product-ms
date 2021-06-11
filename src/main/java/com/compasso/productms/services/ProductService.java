package com.compasso.productms.services;

import java.util.List;

import com.compasso.productms.models.Product;

public interface ProductService {
	
	public abstract Product save(Product product);
	
	public abstract Product update(Product product);
	
	public abstract List<Product> findByDateAndNameOrDescription(String min_value, String max_value, String q);
	
}
