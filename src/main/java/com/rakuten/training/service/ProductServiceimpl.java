package com.rakuten.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

@Service
@Transactional
public class ProductServiceimpl implements ProductService {

  ProductDAO dao; // = new ProductDAOInMemimpl();

  @Autowired
  public void setDao(ProductDAO dao) {
    this.dao = dao;
  }

  @Override
  public int addNewproduct(Product toBeAdded) {
    if (toBeAdded.getPrice() * toBeAdded.getQoh() >= 10000) {
      Product added = dao.save(toBeAdded);
      return added.getId();
    } else {
      throw new IllegalArgumentException("The monetory value of product < 10,000");
    }
  }

  @Override
  public void removeProduct(int id) {
    Product existing = dao.findById(id);
    if (existing != null) {
      if (existing.getPrice() * existing.getQoh() >= 1000000) {
        throw new IllegalArgumentException("Monetory value>10000, Can't Delete");
      } else {
        dao.deleteById(id);
      }
    }
    else {
    	throw new NullPointerException("no value");
    }
  }

  @Override
  public List<Product> findAll() {
    return dao.findAll();
  }

  @Override
  public Product findById(int id) {
    return dao.findById(id);
  }
}
