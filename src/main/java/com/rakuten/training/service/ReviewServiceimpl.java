package com.rakuten.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.dal.ReviewDAO;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Service
@Transactional
public class ReviewServiceimpl implements ReviewService {

	@Autowired
	ReviewDAO dao; // = new ProductDAOInMemimpl();

	@Autowired
	ProductDAO prod;
	@Override
	public int addNewReview(Review toBeAdded, int pid) {
		ProductService p = new ProductServiceimpl();
		Product pro = p.findById(pid);
		if (pro != null) {
			toBeAdded.setProduct(pro);
			Review added = dao.save(toBeAdded);
			return added.getId();
		} else {
			throw new NoSuchProductException("no product wit such id exists");
		}
	}

	@Override
	public void removeReview(int id) {
		Review existing = dao.findById(id);
		if (existing != null) {
			throw new IllegalArgumentException("Monetory value>10000, Can't Delete");
		} else {
			dao.deleteById(id);
		}
	}

	@Override
	public List<Review> findAll() {
		return dao.findAll();
	}

	public List<Review> findAllByPid(int proId){
		Product p = prod.findById(proId);
		if(p == null){
			throw new IllegalArgumentException("no such product");
		}else {
			return dao.findAllByPid(proId);
		}
  }

	@Override
	public Review findById(int id) {
		return dao.findById(id);
	}
}
