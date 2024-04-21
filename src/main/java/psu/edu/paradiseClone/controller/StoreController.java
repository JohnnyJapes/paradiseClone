package psu.edu.paradiseClone.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.validation.Valid;
import psu.edu.paradiseClone.dao.ManufacturerRepository;
import psu.edu.paradiseClone.dao.OrderItemRepository;
import psu.edu.paradiseClone.entity.Address;
import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.entity.Manufacturer;
import psu.edu.paradiseClone.entity.Order;
import psu.edu.paradiseClone.entity.OrderItem;
import psu.edu.paradiseClone.entity.Product;
import psu.edu.paradiseClone.entity.Shipment;
import psu.edu.paradiseClone.entity.Tag;
import psu.edu.paradiseClone.service.AddressService;
import psu.edu.paradiseClone.service.CustomerService;
import psu.edu.paradiseClone.service.OrderService;
import psu.edu.paradiseClone.service.ProductService;
import psu.edu.paradiseClone.service.ShipmentService;
import psu.edu.paradiseClone.service.TagService;
import psu.edu.paradiseClone.validation.CustomerValidation;


@Controller
@RequestMapping("/store")
public class StoreController {
	
    private TagService tagService;
    private CustomerService custService;
    private AddressService addressService;
    private ProductService productService;
    private OrderService orderService;
    private ShipmentService shipService;
    private OrderItemRepository oiRepo;
    private ManufacturerRepository manRepo;

    
	
	
	public StoreController(TagService tagService, CustomerService custService, ProductService productService, AddressService addressService, OrderService orderService,
			ShipmentService shipService, OrderItemRepository oiRepo, ManufacturerRepository manRepo) {
		super();
		this.tagService = tagService;
		this.custService = custService;
		this.productService = productService;
		this.addressService = addressService;
		this.orderService = orderService;
		this.shipService = shipService;
		this.oiRepo = oiRepo;
		this.manRepo = manRepo;
	}

	//@RequestMapping(method = RequestMethod.POST, value = "/create-order")
	@PostMapping("/create-order")
	public String createOrder(@ModelAttribute("order") Order order, Model model, Authentication authentication) {
		Customer customer = new Customer();
		
		if(authentication != null) {
			
			String username = authentication.getName();
			System.out.println("username/email: " +username);
			 customer = custService.findByEmail(username);
		}
		else {
			customer = custService.findByEmail("anonymousUser");
		}
		System.out.println("POST ORDER");
		
		if(order.getCustomer().getId() != customer.getId()) {
			model.addAttribute("error", "Customer ID mismatch");
			return "redirect:/store/cart";
		}
		//fill out all the null values with data from the database
		order.setCustomer(customer);
		double total = 0;
		for(OrderItem item : order.getItems()) {
			
			item.setProduct(productService.findById(item.getProduct().getId()));
			if(item.getProduct().getPrice() *item.getQuantity() != item.getPrice()) {
				//price mismatch somehow, will need error handling in a real world scenario
				item.setPrice(item.getProduct().getPrice());
			}
			total += item.getPrice();
		}
		Address temp = new Address();
		temp = order.getShipment().getAddress();
		if(customer.getId() != 4 && order.getShipment().getAddress().getId()!= 0) {
			temp = addressService.findById(order.getShipment().getAddress().getId());
			order.getShipment().setAddress(temp);
		}
		addressService.save(temp);
//		order.setShipment(new Shipment());
		order.getShipment().setShipmentDate(LocalDate.now().toString());
		order.getShipment().setDelivered(false);
		
		order.setTotalPrice(total);
		
		
		order.setOrderDate(LocalDate.now().toString());
		
		//System.out.println(order);
		
		Shipment tempShip = order.getShipment();
		
		
		shipService.save(tempShip);
		
		orderService.save(order);
		
		System.out.println(order.getId());
		
		List<OrderItem> items = order.getItems();
		
		for (OrderItem item :items) {
			item.setOrder_id(order.getId());
			oiRepo.save(item);
		}
		
		tempShip.setOrder(order);
		
		shipService.save(tempShip);
		

		model.addAttribute("success", "Order Placed");
		return "redirect:/store/cart?success=order";
	}
	@GetMapping("/products/{sku}")
	public String getProductPage(@PathVariable String sku, Model theModel, Authentication authentication) {
		
		if(authentication != null) {
			String username = authentication.getName();
			System.out.println("username/email: " +username);
			Customer customer = custService.findByEmail(username);
			if(customer != null) {
				System.out.println("authenticated");
				theModel.addAttribute("name", customer.getFirstName());
			};
		};
		
		Product product = productService.findBySku(sku);
		
		theModel.addAttribute("product", product);
		
		
		return "store/product";
	}
	
	@GetMapping("/cart")
	public String getCartPage(Model theModel, Authentication authentication) {
		if(authentication != null) {
			String username = authentication.getName();
			System.out.println("username/email: " +username);
			Customer customer = custService.findByEmail(username);
			if(customer != null) {
				System.out.println("authenticated");
				theModel.addAttribute("name", customer.getFirstName());
			};
		};
		
		return "store/cart";
	}
	
	@GetMapping("/checkout")
	public String getCheckoutPage(Authentication authentication, Model theModel) {
		Order order = new Order();
		if(authentication != null) {
			
			String username = authentication.getName();
			System.out.println("username/email: " +username);
			Customer customer = custService.findByEmail(username);
			order.setCustomer(customer);
			if(customer != null) {
				System.out.println("authenticated");
				theModel.addAttribute("name", customer.getFirstName());
			};
		}
		else {
			Customer customer = custService.findByEmail("anonymousUser");
			order.setCustomer(customer);
		}

		
	
		
		theModel.addAttribute("order", order);
		return "store/checkout";
	}
	
	@GetMapping("/catalog")
	public String getCatalog(@RequestParam(name = "manuId",required = false) Integer manuId, @RequestParam(required = false) List<String> filters,
		 Model theModel, Authentication authentication, @RequestParam(name = "searchText",required = false) String searchString) {

		System.out.println(filters);
		if(authentication != null) {
			String username = authentication.getName();
			System.out.println("username/email: " +username);
			Customer customer = custService.findByEmail(username);
			if(customer != null) {
				System.out.println("authenticated");
				theModel.addAttribute("name", customer.getFirstName());
			};

		};
		List<Product> products = new ArrayList<Product>();
    	List<Tag> tags = tagService.findAll();
    	for (Tag tag : tags) {
    		tag.generateLink();
    	}
    	theModel.addAttribute("tags",tags);
		if(filters == null) {

	    	System.out.println(tags.get(0).getLink());
	    	

			if(searchString != null && !searchString.isEmpty()) {
			 products = productService.findByNameLike(searchString);	
			}
			else products = productService.findAll();



	    	filters = new ArrayList<String>();
	    	theModel.addAttribute(filters);
		}
		else {
			List<Tag> tagFilters = new ArrayList<Tag>();
			for (String i :filters) {
				tagFilters.add(tagService.findByID(Integer.parseInt(i)));
			}
			products = productService.findAll();
			
			int i = 0;
			for (int t = 0; t <tagFilters.size(); t++) {
				for (i=0; i<products.size();i+=0) {
					boolean remove = true;
					for (int j = 0; j <products.get(i).getTags().size();j++) {
						if (products.get(i).getTags().get(j).getId() == tagFilters.get(t).getId()) remove = false;
					}
					if (remove) products.remove(i);
					else i++;
				}
				
			}

	    	theModel.addAttribute("filters",filters);
		};
		
		if(manuId != null) {
			
			Optional<Manufacturer> result = manRepo.findById(manuId);
			
			if (result.isPresent()) {
				int i = 0;
				for (i=0; i<products.size();i+=0) {
					//boolean remove = true;
					if (products.get(i).getManufacturer().getManufacturerId() != result.get().getManufacturerId())
						products.remove(i);
					else i++;
				}
				theModel.addAttribute("manufacturer", result.get());
			}
			

			
		}
		

    	theModel.addAttribute("products",products);
		
		return "store/catalog";
	}
	


}
