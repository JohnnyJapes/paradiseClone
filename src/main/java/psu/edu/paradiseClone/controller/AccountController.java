package psu.edu.paradiseClone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import psu.edu.paradiseClone.entity.Address;
import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.entity.Order;
import psu.edu.paradiseClone.entity.Product;
import psu.edu.paradiseClone.service.AddressService;
import psu.edu.paradiseClone.service.CustomerService;
import psu.edu.paradiseClone.service.OrderService;
import psu.edu.paradiseClone.service.ProductService;
import psu.edu.paradiseClone.service.TagService;

@Controller
@RequestMapping("account")
public class AccountController {
	

    private CustomerService custService;
    private AddressService addressService;
    private ProductService productService;
    private OrderService oService;
    

    public AccountController(CustomerService custService, AddressService addressService, OrderService oService,
			ProductService productService) {
		this.custService = custService;
		this.addressService = addressService;
		this.productService = productService;
		this.oService = oService;
	}



    
	@GetMapping("/my-addresses")
	public String getMyAddresses(Authentication authentication, Model theModel) {
		
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
		//Customer customer.
		theModel.addAttribute("name", customer.getFirstName());
		
		theModel.addAttribute("customer", customer);
		
		
		
		return "account/my-addresses";
	}
	
	@GetMapping("/my-orders")
	public String getMyOrders(Authentication authentication, Model theModel) {
		
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
		//Customer customer.
		
		theModel.addAttribute("name", customer.getFirstName());
		List<Order> orders = oService.findByCustomerId(customer.getId());
		
		theModel.addAttribute("customer", customer);
		theModel.addAttribute("orders", orders);
		
		return "account/my-orders";
	}
	
	@GetMapping("/new-address")
	public String getAddressCreateForm(Authentication authentication, Model theModel) {
		
		
		//theModel.addAttribute("product", product);
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
		theModel.addAttribute("name", customer.getFirstName());
		Address address = new Address();
		address.setCustomer(customer);
		
		theModel.addAttribute("address", address);
		theModel.addAttribute("action", "/account/address/create");
		theModel.addAttribute("type", "New Address");
		
		return "account/address-form";
	}
	@GetMapping("/update-address")
	public String getAddressUpdateForm(@RequestParam("addressId") int id,Authentication authentication, Model theModel) {
		
		
		//theModel.addAttribute("product", product);
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
		
		Address address = addressService.findById(id);
		theModel.addAttribute("name", customer.getFirstName());
		theModel.addAttribute("address", address);
		theModel.addAttribute("action", "/account/address/update");
		theModel.addAttribute("type", "Update Address");
		
		return "account/address-form";
	}
	
	
    @PostMapping("/address/create") 
    public String createAddress(@ModelAttribute("address") Address  address, Model model, Authentication authentication) {
     
//	     if (result.hasErrors()) {
//	    	 model.addAttribute("error", result.getFieldError());
//	    	 return "account/address-form";
//	     }
    	
    	//check that the customer id of the one submitting matches the customer id of the address
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
		
		if (customer.getId() != address.getCustomer().getId()) {
			model.addAttribute("error", "Customer ID mismatch");
			return "redirect:/account/my-addresses";
		}
			
	     //save address
	     addressService.save(address);
	     
	     //redirect to prevent duplicate submissions
	     
	     return "redirect:/account/my-addresses";
     }
    @PostMapping("/address/update") 
    public String updateAddress(@ModelAttribute("address") Address  address, Model model, Authentication authentication) {
     
    	
    	//check that the customer id of the one submitting matches the customer id of the address
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
		
		if (customer.getId() != address.getCustomer().getId()) {
			model.addAttribute("error", "Customer ID mismatch");
			return "redirect:/account/my-addresses";
		}
			
	     //save address
	     addressService.save(address);
	     
	     //redirect to prevent duplicate submissions
	     
	     return "redirect:/account/my-addresses";
     }


	
	
    @GetMapping("/address/delete") 
    public String createEmployee(@RequestParam("addressId") int id, Model model, Authentication authentication) {
     
    	
    	//check that the customer id of the one submitting matches the customer id of the address
		String username = authentication.getName();
		System.out.println("username/email: " +username);
		Customer customer = custService.findByEmail(username);
//		List<Integer> ids = new ArrayList<Integer>();
//		int[] ab = {};
//		
//		for (Address a :customer.getAddresses()) {
//			ids.add(a.getId());
//		}
		
		Address address = addressService.findById(id);
		
		if (customer.getId() != address.getCustomer().getId()) {
			model.addAttribute("error", "Customer ID mismatch");
			return "redirect:/account/my-addresses";
		}
			
	     //save address
	     addressService.deleteById(id);
	     
	     //redirect to prevent duplicate submissions
	     
	     return "redirect:/account/my-addresses";
     }

}
