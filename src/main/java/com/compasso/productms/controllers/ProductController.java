package com.compasso.productms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.productms.repository.ProductRepository;

/**
 * Controller for Product Entity
 * @author Carlos Gabriel
 *
 */
@RestController
@RequestMapping(value="/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

}
