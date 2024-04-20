package psu.edu.paradiseClone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psu.edu.paradiseClone.dao.OrderRepository;
import psu.edu.paradiseClone.entity.Order;

@Service
public class OrderServiceImpl implements OrderService{

	OrderRepository oRepo;
	
	
	
	
	@Autowired
	public OrderServiceImpl(OrderRepository oRepo) {
		super();
		this.oRepo = oRepo;
	}





	@Override
	public void save(Order order) {
		oRepo.save(order);
	}





	@Override
	public List<Order> findByCustomerId(int id) {
		return oRepo.findByCustomerId(id);
	}

}
