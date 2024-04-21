package psu.edu.paradiseClone.entity;

import java.io.Serializable;

//class defines the primary composite key for Order Item

public class OrderItemId  implements Serializable{
	

	private int order_id;
	
	private Product product;

	public OrderItemId() {};
	
	public OrderItemId(int order_id, Product product) {
		super();
		this.order_id = order_id;
		this.product = product;
	}

	
	
}
