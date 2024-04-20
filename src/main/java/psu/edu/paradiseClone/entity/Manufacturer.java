package psu.edu.paradiseClone.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//Luke Hanrahan
//ETI 461

@Entity
@Table(name="manufacturer")
public class Manufacturer {
//names left as they are in the table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="manufacturer_id")
	private int manufacturerId;
	
	@Column(name="manufacturer_name")
	@NotEmpty(message="Name Empty")
	private String manufacturerName;
	
	public Manufacturer() {}

	public Manufacturer(int manufacturerId, @NotEmpty(message = "Name Empty") String manufacturerName) {
		super();
		this.manufacturerId = manufacturerId;
		this.manufacturerName = manufacturerName;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	
	





}

	