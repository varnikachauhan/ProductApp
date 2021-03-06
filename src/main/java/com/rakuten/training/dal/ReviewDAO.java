package com.rakuten.training.dal;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface ReviewDAO {

  Review findById(int id);

  Review save(Review toBeAdded);

  void deleteById(int id);

  List<Review> findAll();

List<Review> findAllByPid(int proId);

}