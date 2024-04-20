package psu.edu.paradiseClone.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name="product")
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private int id;
	
	@Column(name = "product_name")
	private String name;
	

	@Column(name = "product_description")
	private String description;
	@Column(name = "product_price")
	private Double price;
	@Column(name = "product_SKU")
	private String sku;
	
	@OneToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;
	
	@ManyToMany
	@JoinTable(name = "product_tag", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name="tag_id"))
	@JsonManagedReference
	private List<Tag> tags;
	
	@ManyToMany
	@JoinTable(name = "product_image", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name="image_id"))
	private List<Image> images;
	
	public Product() {};
	
	public Product(int id, String name, String description, Double price, String sku, Manufacturer manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.sku = sku;
		this.manufacturer = manufacturer;
	}

	
	@Override
	public String toString() {
		return "\nProduct [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", sku="
				+ sku + ", manufacturer=" + manufacturer + ", tags=" + tags + ", images=" + images + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	

}


