package psu.edu.paradiseClone.service;

import java.util.List;

import psu.edu.paradiseClone.entity.Order;

public interface OrderService {
	
	public void save(Order order);
	List<Order> findByCustomerId(int id);

}
