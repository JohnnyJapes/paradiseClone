package psu.edu.paradiseClone.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int id;
	
	@Column(name = "order_date")
	private String orderDate;
	
	@Column(name = "order_total_price")
	private Double totalPrice;

	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany
	@JoinColumn(name="order_id")
	@JsonManagedReference
	private List<OrderItem> items;
	
	@OneToOne
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;

	public Order() {}
	
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ",\n customer=" + customer
				+ ", \nitems=" + items + ", \nshipment=" + shipment + "]";
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	};

	
}
