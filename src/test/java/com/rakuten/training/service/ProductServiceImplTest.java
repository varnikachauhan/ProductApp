package com.rakuten.training.service;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

public class ProductServiceImplTest {

  @Test
  public void addNewProduct_Returns_ValidId_When_ProductVal_GTE_MinVal() {
    // Arrange
	  ProductServiceimpl serv = new ProductServiceimpl();
	  Product toBeAdded = new Product("test", 10000, 1); // N.B: 10000*1 >= 10000
	  ProductDAO mockDAO = Mockito.mock(ProductDAO.class); // mock object for product dao
	  Product saved = new Product("test", 10000, 1);
	  saved.setId(1);
	  Mockito.when(mockDAO.save(toBeAdded)).thenReturn(saved);
	  serv.setDao(mockDAO);
    // Act
	  int id = serv.addNewproduct(toBeAdded);
    // Assert
	  assertTrue(id>0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addNewProduct_Throws_Error_When_ProductVal_LT_MinVal() {
	  //Arrange
	  ProductServiceimpl serv = new ProductServiceimpl();
	  Product toBeAdded = new Product("test", 9999, 1); // N.B: 10000*1 >= 10000
	  
	  //Act
	  serv.addNewproduct(toBeAdded);
  }
   @Test
   public void removeProduct_ActuallyDeletes() {
	   //Arrange
	   ProductServiceimpl service = new ProductServiceimpl();
	   ProductDAO mockDAO = Mockito.mock(ProductDAO.class); 
	   int del_id = 2;
	   Product checking_product = new Product("t1",2000,34);
	   checking_product.setId(del_id);
	   Mockito.when(mockDAO.findById(del_id)).thenReturn(checking_product);
	   service.setDao(mockDAO);
	   
	   //Act
	   service.removeProduct(del_id);
	   Mockito.verify(mockDAO, Mockito.times(1)).deleteById(del_id);  
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void removeProduct_throwsException() {
	   //Arrange
	   ProductServiceimpl service = new ProductServiceimpl();
	   ProductDAO mockDAO = Mockito.mock(ProductDAO.class); 
	   int id = 2;
	   Product checking_product = new Product("t1",2000000,34);
	   checking_product.setId(id);
	   Mockito.when(mockDAO.findById(id)).thenReturn(checking_product);
	   service.setDao(mockDAO);
	   
	   //Act
	   service.removeProduct(id);
   }
   
   @Test
   public void findById_testing() {
	   ProductServiceimpl service = new ProductServiceimpl();
	   int id = 2;
	   ProductDAO mockDAO = Mockito.mock(ProductDAO.class); 
	   Product checking_byId = new Product("t1",2000000,34);
	   checking_byId.setId(id);
	   Mockito.when(mockDAO.findById(id)).thenReturn(checking_byId);
	   service.setDao(mockDAO);
	   
	   //Act
	   Product p = service.findById(id);
	   
	   //assert
	   assertNotNull(p);
	  
   }
}
