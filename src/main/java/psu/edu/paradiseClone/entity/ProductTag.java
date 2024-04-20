package psu.edu.paradiseClone.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_tag")
public class ProductTag {
	
	@Id
	@Column(name="product_id")
	private int id;
//	
//	@JoinColumn
//	private List<Tag> tags;

}
