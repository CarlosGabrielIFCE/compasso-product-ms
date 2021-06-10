package com.compasso.productms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.productms.models.Product;
import com.compasso.productms.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product save(Product product) {
		if (!verifyFields(product)) {
			return null;
		}
		
		return productRepository.save(product);
	}
	
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
