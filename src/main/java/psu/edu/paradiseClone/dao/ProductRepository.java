package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product findBySku(String sku);
	public Product findById(int id);


}
