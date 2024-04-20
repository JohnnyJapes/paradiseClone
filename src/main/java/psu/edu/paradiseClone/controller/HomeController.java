package psu.edu.paradiseClone.controller;



import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.entity.Product;
import psu.edu.paradiseClone.entity.Tag;
import psu.edu.paradiseClone.service.CustomerService;
import psu.edu.paradiseClone.service.ProductService;
import psu.edu.paradiseClone.service.TagService;

@Controller
@RequestMapping("/")
public class HomeController {
	
    private TagService tagService;
    private ProductService productService;
    private CustomerService custService;

    public HomeController(TagService tagService, ProductService productService, CustomerService custService) {
		super();
		this.tagService = tagService;
		this.productService = productService;
		this.custService = custService;
	}

	@GetMapping("/home")
    public String homePage(Authentication authentication,Model theModel) {
    	
		if(authentication != null) {
			System.out.println(authentication.getName());
			String username = authentication.getName();
			System.out.println("username/email: " +username);
			Customer customer = custService.findByEmail(username);
			System.out.println("authenticated");
			theModel.addAttribute("name", customer.getFirstName());
		};
    	
    	
    	List<Tag> tags = tagService.findByImageIdNotNull();
    	for (Tag tag : tags) {
    		tag.generateLink();
    	}
    	System.out.println(tags.get(0).getLink());
    	List<Product> products = tags.get(1).getProducts();
    	
    	theModel.addAttribute("tags",tags);
    	theModel.addAttribute("products",products);

        return "paradise";
    }

    
}