package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;

@Repository
@Transactional
public class ProductDAOjpaimpl implements ProductDAO {

	@Autowired
	EntityManager em;
  @Override
  public Product save(Product toBeSaved) {
    em.persist(toBeSaved);
    return toBeSaved;
  }

  @Override
  public Product findById(int id) {
    // TODO Auto-generated method stub
    return em.find(Product.class, id);
  }

  @Override
  public List<Product> findAll() {
    Query q = em.createQuery("select p from Product p");
    List<Product> all = q.getResultList();
    return all;
  }

  @Override
  public void deleteById(int id) {
	  Product p = em.getReference(Product.class, id);
	  em.remove(p);
//	  Query q = em.createNamedQuery("delete p from Product p where p.id = :param");
//	  q.setParameter("param", id);
//	  q.executeUpdate();
	  
  }
}

