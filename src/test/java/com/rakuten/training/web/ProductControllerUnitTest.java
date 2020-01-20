package com.rakuten.training.web;

import static org.junit.Assert.*;
//import static org.springframework.test.web.sevlet.request.MockMvcRequestBuilders.*;
import org.json.*;
import org.json.JSONString;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest({ ProductController.class }) // MVC will keep this controller ready for testing
public class ProductControllerUnitTest {

	@Autowired
	MockMvc mockMvc; // use to stimulate get/post type request

	@MockBean // used to mock bean in productService
	ProductService service;

	@Test
	public void getProductById_returnsOK_withCorrectId_ifFound() throws Exception {
		// Arrange
		Product found = new Product("test", 123.0f, 100);
		int id = 1;
		found.setId(id);
		Mockito.when(service.findById(id)).thenReturn(found);
		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id)) // we're making a get request to the specified
																			// UTL
				.andExpect(MockMvcResultMatchers.status().isOk()) // we're expecting "OK" status
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id))); // it evaluates the json file
																							// for the "id" with the
																							// actual id Or does the
																							// Json file have the id

	}

	@Test
	public void getProductById_returnsNOT_FOUND_IfId_DoesNotExist() throws Exception {
		// Arrange
		Product pro = new Product("test1", 234.0f, 200);
		int id = 10;
		pro.setId(id);
		Mockito.when(service.findById(id)).thenReturn(null);

		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", id))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void addProduct_andItIsCreated() throws Exception {
		// Arrange
		Product prod = new Product("test2", 345.0f, 30);
		int id = 9;
		prod.setId(id);
		Mockito.when(service.addNewproduct(Mockito.any(Product.class))).thenReturn(id);
		JSONObject jo = new JSONObject();
		jo.put("name", "cars");  	//the name of the key will be same as names of fields in class
		jo.put("price", 100000);
		jo.put("qoh", 19);
		String j1 = jo.toString();
//		 System.out.println("-----" + j1);

		ObjectMapper mapper = new ObjectMapper();
		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/products")
				.contentType(MediaType.APPLICATION_JSON).content(j1))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.header().string(HttpHeaders.LOCATION, "/products/"+id));
		
		Mockito.verify(service, Mockito.times(1)).addNewproduct(Mockito.any(Product.class));
	}
	
	@Test
	public void addProduct_andItIsNNotCreated() throws Exception {
		// Arrange
		Product prod = new Product("test3", 345.0f, 30);
		int id = 9;
		prod.setId(id);
		Mockito.when(service.addNewproduct(Mockito.any(Product.class))).thenThrow( new IllegalArgumentException());
		JSONObject jo = new JSONObject();
		jo.put("name", "cars");  	//the name of the key will be same as names of fields in class
		jo.put("price", 100000);
		jo.put("qoh", 19);
		String j2 = jo.toString();
//		 System.out.println("-----" + j1);

//		ObjectMapper mapper = new ObjectMapper();
		// Act and Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/products")
				.contentType(MediaType.APPLICATION_JSON).content(j2))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(service, Mockito.times(1)).addNewproduct(Mockito.any(Product.class));
	}
	
	@Test
	public void deleteGivenId() {
		Product prod = new Product("test4", 345.0f, 30);
		int id = 4;
		prod.setId(id);
		Mockito.when(service.removeProduct(id);(Mockito.any(Product.class))).thenReturn(null);
		
	}
	
	

}
