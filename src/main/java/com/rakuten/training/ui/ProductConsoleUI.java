package com.rakuten.training.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;
//import com.rakuten.training.service.ProductServiceimpl;

@Component
public class ProductConsoleUI {

  ProductService service; // = new ProductServiceimpl();

  @Autowired
  public void setService(ProductService service) {
    this.service = service;
  }

  public void createProductWithUI() {
    Scanner sc = new Scanner(System.in, "UTF-8");
    System.out.println("Enter name: ");
    String name = sc.next();
    System.out.println("Enter Price: ");
    Float price = sc.nextFloat();
    System.out.println("enter QOH:");
    int qoh = sc.nextInt();

    Product p = new Product(name, price, qoh);
    int id = service.addNewproduct(p);
    System.out.println("Create product with id: " + id);
    
    System.out.println("Enter name: ");
    String name1 = sc.next();
    System.out.println("Enter Price: ");
    Float price1 = sc.nextFloat();
    System.out.println("enter QOH:");
    int qoh1 = sc.nextInt();

    Product p1 = new Product(name1, price1, qoh1);
    service.addNewproduct(p1);
    System.out.println("Create product with id: " + id);
  }
}
