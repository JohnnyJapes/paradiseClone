package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	Customer findByEmail(String email);
	//Customer findByEmail(String email);
}
