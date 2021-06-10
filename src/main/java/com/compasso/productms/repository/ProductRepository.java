package com.compasso.productms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.productms.models.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	Product findById(long id);

}
