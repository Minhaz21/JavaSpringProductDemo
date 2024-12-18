package com.minhaz.ProductSpringWeb;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

	List<Product> products = new ArrayList<>();
	
	@Autowired
	ProductDB db; 

	public void addProdcut(Product p) {
		db.save(p);
		products.add(p);
	}

	public List<Product> getAllProducts() {

		return db.findAll();

	}

	public Product getProduct(String name) {
		return products.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get();

	}

	public List<Product> getProductWithSearch(String text) {

		List<Product> productSearchList = products.stream()
				.filter(p -> (p.getName().contains(text) || p.getPlace().contains(text) || p.getType().contains(text)))
				.collect(Collectors.toList());

		return productSearchList;
	}

}
