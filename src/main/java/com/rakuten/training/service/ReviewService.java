package com.rakuten.training.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Review;

public interface ReviewService {

	public int addNewReview(Review toBeAdded, int pid);
	 
	 public void removeReview(int id);
	 
	 public List<Review> findAll();
	 
	 public Review findById(int id);

	public List<Review> findAllByPid(int proId);

}
