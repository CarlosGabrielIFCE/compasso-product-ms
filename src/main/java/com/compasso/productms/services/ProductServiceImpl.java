package com.compasso.productms.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
	
	@Override
	public Product update(Product product) {
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
	
	public List<Product> findByDateAndNameOrDescription(String min_value, String max_value, String q) {
		BigDecimal minValue = !Objects.isNull(min_value) ? new BigDecimal(min_value) : new BigDecimal(0);
		BigDecimal maxValue = !Objects.isNull(max_value) ? new BigDecimal(max_value) : new BigDecimal(1000000);
		
		if (Objects.isNull(q)) {
			return productRepository.findByPriceFields(minValue, maxValue);
		}else {
			return productRepository.findByAllFields(minValue, maxValue, q);
		}
	}

}
