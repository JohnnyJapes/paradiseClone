package psu.edu.paradiseClone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByCustomerId(int id);

}
