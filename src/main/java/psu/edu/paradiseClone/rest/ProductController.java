package psu.edu.paradiseClone.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psu.edu.paradiseClone.entity.Product;
import psu.edu.paradiseClone.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	private ProductService proService;

	@Autowired
	public ProductController(ProductService proService) {
		super();
		this.proService = proService;
	}
	
	//add mapping for GET "/employees/{id}" and return an employee
	@GetMapping("/products/{productID}")
	public Product getEmployee(@PathVariable int productID) {
		
		Product result = proService.findById(productID);
		
		System.out.println("get product: " +productID);
		if (result == null)
			throw new RuntimeException("Product Id not found - " +productID);
		
		return result;
	}
	
	
}
