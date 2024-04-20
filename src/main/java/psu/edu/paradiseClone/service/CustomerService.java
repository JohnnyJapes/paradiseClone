package psu.edu.paradiseClone.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.validation.CustomerValidation;

public interface CustomerService extends UserDetailsService{

	Customer findById(int ID);
	Customer findByEmail(String email);
	void save(CustomerValidation data);
	//Customer findByUserName(String email);
}
