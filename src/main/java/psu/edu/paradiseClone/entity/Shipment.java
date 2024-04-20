package psu.edu.paradiseClone.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="shipment")
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shipment_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(name = "shipment_date")
	private String shipmentDate;
	
	@Column(name= "delivered")
	private Boolean delivered;
	
	public Shipment() {}

	
	@Override
	public String toString() {
		return "Shipment [id=" + id + ", address=" + address + ", order=" + order + ", shipmentDate=" + shipmentDate
				+ ", delivered=" + delivered + "]";
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
	public String getShipmentDate() {
		return shipmentDate;
	}


	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}


	public String getOrderDate() {
		return shipmentDate;
	}

	public void setOrderDate(String orderDate) {
		this.shipmentDate = orderDate;
	}

	public Boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	};
	
}
