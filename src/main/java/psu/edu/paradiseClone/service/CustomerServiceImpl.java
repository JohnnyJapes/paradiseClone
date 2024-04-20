package psu.edu.paradiseClone.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import psu.edu.paradiseClone.dao.CustomerRepository;
import psu.edu.paradiseClone.dao.RoleRepository;
import psu.edu.paradiseClone.entity.Customer;
import psu.edu.paradiseClone.entity.Role;
import psu.edu.paradiseClone.validation.CustomerValidation;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	CustomerRepository cRepo;
	private RoleRepository rRepo;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository cRepo, RoleRepository rRepo, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.cRepo = cRepo;
		this.rRepo = rRepo;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public Customer findById(int ID) {
		
		Optional<Customer> result = cRepo.findById(ID);
		if (result.isPresent()) return  result.get();
		else
			throw new RuntimeException("Did not find Customer ID - " +ID);

	}
	@Override
	public Customer findByEmail(String email) {
		
		Customer result = cRepo.findByEmail(email);
		if (result!=null) return  result;
		else {
			System.out.println("Did not find Email - " +email);
			return result;
		}

	}


	@Override
	public void save(CustomerValidation data) {
		Customer customer = new Customer();
		
		customer.setEmail(data.getEmail());
		customer.setFirstName(data.getFirstName());
		customer.setLastName(data.getLastName());
		customer.setPassword(passwordEncoder.encode(data.getPassword()));
		customer.setEnabled(true);
		
		// give user default role of "employee"
		customer.setRoles(Arrays.asList(rRepo.findRoleByName("ROLE_CUSTOMER")));

		// save user in the database
		cRepo.save(customer);
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Customer user = cRepo.findByEmail(userName);

		if (user == null) {
			System.out.println("Invalid email or password");
			throw new UsernameNotFoundException("Invalid email or password.");
		}

		Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				authorities);
	}

	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role tempRole : roles) {
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
			authorities.add(tempAuthority);
		}

		return authorities;
	}

}
