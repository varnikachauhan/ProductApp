package com.rakuten.training.web;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	ReviewService service;
	
	@GetMapping("/products/{pid}/reviews")
	public ResponseEntity<List<Review>> getReviewForProduct(@PathVariable("pid") int proId){
	try {
	    List<Review> reviews = service.findAllByPid(proId);
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
	}
	catch(IllegalStateException e) {
		return new ResponseEntity<List<Review>>( HttpStatus.NOT_FOUND);
	}
	}
	
	@PostMapping("/products/{pid}/reviews")
	public ResponseEntity<Review> addReviewById(@RequestBody Review toBeAdded, @PathVariable("pid") int proId){
		try {
			int id_rec = service.addNewReview(toBeAdded, proId);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/products"+ proId+"/reviews"+id_rec));
			return new ResponseEntity<Review>(toBeAdded,headers,HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
	}
}
