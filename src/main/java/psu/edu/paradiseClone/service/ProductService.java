package psu.edu.paradiseClone.service;

import java.util.List;

import psu.edu.paradiseClone.entity.Product;

public interface ProductService {
	
	public Product findBySku(String sku);
	
	public Product findById(int id);

	public List<Product> findAll();
}
