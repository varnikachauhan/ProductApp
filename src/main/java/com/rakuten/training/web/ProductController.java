package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

import ch.qos.logback.classic.layout.TTLLLayout;

@RestController 
public class ProductController {

	@Autowired
	ProductService service;
	
	//@RequestMapping helps specifying that product Controller is  trying to map a request
    //of type method to value through the method specified.
	//method specifies the type of request sent and value is the location
//	@RequestMapping(method = RequestMethod.GET, value = "/products") 
	@GetMapping("/products")
	public List<Product> getAllProduct(){
		return service.findAll(); //by default Spring convert the list<Product> into a json of array
	}
	
	@GetMapping("/products/{id}") //path template
	public ResponseEntity<Product> getProductById(@PathVariable("id")int id) {
		Product p = service.findById(id);
		if(p!= null) {
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		}else {
			return new ResponseEntity<Product>( HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> addProduct(@RequestBody Product toBeAdded){
		try {
		int id  = service.addNewproduct(toBeAdded);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/products/"+id));
		return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
}
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteId(@PathVariable("id")int id){
		try {
			service.removeProduct(id);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
		catch(IllegalArgumentException e) {
			return new ResponseEntity<Product>(HttpStatus.CONFLICT);
			}
		catch(NullPointerException n) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
}
