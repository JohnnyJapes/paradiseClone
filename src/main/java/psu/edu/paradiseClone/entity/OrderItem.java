package psu.edu.paradiseClone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Join table had additional information so it needed to be created for this to work in JPA
@Entity
@Table(name="order_items")
public class OrderItem {

	
	@Id
	@Column(name="order_id")
	private int order_id;
	

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name = "price_at_checkout")
	private Double price;

	@Column(name="quantity")
	private int quantity;
	
	public OrderItem() {}

	
	
	@Override
	public String toString() {
		return "OrderItem [order_id=" + order_id + ", product=" + product + ", price=" + price + ", quantity="
				+ quantity + "]";
	}



	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	};
	
}
