package psu.edu.paradiseClone.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="tag")
public class Tag {
	
	@Id
	@Column(name="tag_id")
	private int id;
	
	@Column(name="tag_name")
	private String name;
	
	@Transient
	private String link;
	
	@ManyToOne()
	@JoinColumn(name="image_id")
	private Image image;
	
	@ManyToMany(mappedBy = "tags")
	@JsonBackReference
	private List<Product> products;
	
	public Tag() {
		
	}

	public Tag(int id, String name, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.link = name.replace(' ', '-');
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
		this.link = name.replace(' ', '-');
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public void generateLink() {
		this.link = "/store/catalog?filters="+id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
