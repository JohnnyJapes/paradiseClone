package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
