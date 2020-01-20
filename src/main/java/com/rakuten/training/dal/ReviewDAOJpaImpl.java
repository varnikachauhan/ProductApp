package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;

@Repository
@Transactional
public class ReviewDAOJpaImpl implements ReviewDAO {
	
	@Autowired
	EntityManager em;
	
	@Override
  public Review findById(int id) {
		return em.find(Review.class, id);
	}
	@Override
  public Review save(Review toBeSaved) {
//		Product p = em.find(Product.class, productId);
//		toBeSaved.setProduct(p);
		em.persist(toBeSaved);
		return toBeSaved;
	}
	 @Override
  public void deleteById(int id) {
		 Review r = em.getReference(Review.class, id);
		  em.remove(r);
	}
	@Override
  public List<Review> findAll(){
		Query q = em.createQuery("select r from Review r");
		List<Review> allReview = q.getResultList();
		return allReview;
	}
	public List<Review> findAllByPid(int pid){
		TypedQuery q = em.createQuery("select r from Review r where r.product.id = :param", Review.class);
		q.setParameter("param", pid);
		List<Review> allReview = q.getResultList();
		return allReview;
	}
}
