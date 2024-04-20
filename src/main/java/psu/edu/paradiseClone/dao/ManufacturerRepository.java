package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
	

}
