package com.compasso.productms.services;

import com.compasso.productms.models.Product;

public interface ProductService {
	
	public abstract Product save(Product product);
	
	public abstract Product update(Product product);
	
}
