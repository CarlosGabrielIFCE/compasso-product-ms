package com.compasso.productms.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.compasso.productms.controllers.ProductController;
import com.compasso.productms.models.Product;
import com.compasso.productms.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	ProductRepository productRepository;

	Product product1 = new Product(1, "macBookPro", "Computador", new BigDecimal(15000));
	Product product2 = new Product(2, "iPhone12", "Celular", new BigDecimal(12000));
	Product product3 = new Product(3, "watchOS", "Relógio", new BigDecimal(1500));

	/**
	 * Teste de busca por todos os produtos
	 * @throws Exception
	 */
	@Test
	public void getAllProducts_success() throws Exception {
		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);
		products.add(product3);

		Mockito.when(productRepository.findAll()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].name", is("Relógio")));

	}

	/**
	 * Teste de busca por um produto by Id
	 * @throws Exception
	 */
	@Test
	public void getProductById_success() throws Exception {
		Mockito.when(productRepository.findById(product1.getId())).thenReturn(product1);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("macBookPro")));
	}

	/**
	 * Teste Unitário de POST de Produto
	 * @throws Exception
	 */
	@Test
	public void createProduct_success() throws Exception {
		Product product = new Product("macMini", "Roteador", new BigDecimal(3000));

		Mockito.when(productRepository.save(product)).thenReturn(product);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/products")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(product));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("macMini")));
	}
	
	/**
	 * Teste Unitário de PUT de Product
	 * @throws Exception
	 */
	@Test
	public void updateProduct_success() throws Exception {
		Product product = new Product(4, "macMini", "Computador", new BigDecimal(3000));

	    Mockito.when(productRepository.findById(product.getId())).thenReturn(product);
	    Mockito.when(productRepository.save(product)).thenReturn(product);

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/products")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(product));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()))
	            .andExpect(jsonPath("$.description", is("Computador")));
	}
	
	/**
	 * Teste Unitário de DELETE de Product
	 * @throws Exception
	 */
	@Test
	public void deleteProductById_success() throws Exception {
	    Mockito.when(productRepository.findById(product2.getId())).thenReturn(product2);

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/products/2")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
}
