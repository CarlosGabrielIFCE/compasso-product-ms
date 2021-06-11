package com.compasso.productms.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.compasso.productms.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findById(long id);
	
	@Query(value = "FROM Product WHERE price between ?1 and ?2 and (name like %?3% or description like %?3%)")
	List<Product> findByAllFields(BigDecimal min_value, BigDecimal max_value, String q);
	
	@Query(value = "FROM Product WHERE price between ?1 and ?2")
	List<Product> findByPriceFields(BigDecimal min_value, BigDecimal max_value);

}
