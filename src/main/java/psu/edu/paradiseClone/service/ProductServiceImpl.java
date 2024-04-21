package psu.edu.paradiseClone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psu.edu.paradiseClone.dao.ProductRepository;
import psu.edu.paradiseClone.entity.Product;
import psu.edu.paradiseClone.entity.Tag;

@Service
public class ProductServiceImpl implements ProductService {

	
	ProductRepository proRepo;

	@Autowired
	public ProductServiceImpl(ProductRepository proRepository) {
		this.proRepo = proRepository;
	}
	@Override
	public Product findBySku(String sku) {
		Product result = proRepo.findBySku(sku);
		if (result != null) return  result;
		else
			throw new RuntimeException("Did not find sku - " +sku);
	}
	@Override
	public Product findById(int id) {
		Product result = proRepo.findById(id);
		if (result != null) return  result;
		else
			throw new RuntimeException("Did not find id - " +id);
	}
	@Override
	public List<Product> findAll() {
		return proRepo.findAll();
		
	}
	@Override
	public List<Product> findByNameLike(String name) {
	//	return proRepo.findByNameLike(name);
		return proRepo.findByNameContaining(name);
	}

}
