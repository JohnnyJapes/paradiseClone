package psu.edu.paradiseClone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

}
