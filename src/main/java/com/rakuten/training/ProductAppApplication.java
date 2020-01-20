package com.rakuten.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.ui.ProductConsoleUI;

@SpringBootApplication
public class ProductAppApplication {

	public static void main(String[] args) {
//		ApplicationContext springContainer = 
				SpringApplication.run(ProductAppApplication.class, args);
//		    ProductConsoleUI ui = springContainer.getBean(ProductConsoleUI.class);
//		    ui.createProductWithUI();

		/*
		 * ReviewDAO reviewDAO = springContainer.getBean(ReviewDAO.class); Review sample
		 * = new Review("self", "this is good", 5); Review saved =
		 * reviewDAO.save(sample,1); System.out.println("Created review with id: " +
		 * saved.getId());
		 */

//		    ProductDAO productDAO = springContainer.getBean(ProductDAO.class);
//		    Product p = productDAO.findById(1);
//		    System.out.println(p.getName());
//		    System.out.println("This product has "+p.getReviews().size()+" reviews");
	}
}
