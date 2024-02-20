package com.BitProject.productservice;

import com.BitProject.productservice.dao.ProductRepository;
import com.BitProject.productservice.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.1");

	@Autowired
	private MockMvc mockMvc;

	// converts to project object or json
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;


	/**
	 * To connect Database
	 * @param dymDynamicPropertyRegistry
	 */
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		// şifre ekleme gerekebilir
		dymDynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		dymDynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		dymDynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}


	/**
	 * test method
	 */
	@Test
	void shouldCreateProduct() throws Exception{
		ProductRequest productRequest = getProductRequest();

		// Converting object to string
		String productRequestString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
				.andExpect(status().isCreated());

		Assertions.assertEquals(productRepository.findAll().size(), 1);
	}

	/**
	 * helper test method that creates product
	 * @return
	 */
	private ProductRequest getProductRequest() {

		return ProductRequest.builder()
				.name("Iphone 13")
				.description("Eski model Iphone")
				.price(30000)
				.stock(24)
				.build();
	}

}
