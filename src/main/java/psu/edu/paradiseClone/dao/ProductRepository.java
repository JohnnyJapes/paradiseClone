package psu.edu.paradiseClone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product findBySku(String sku);
	public Product findById(int id);
	public List<Product> findByNameLike(String name);
	public List<Product> findByNameContaining(String name);


}
