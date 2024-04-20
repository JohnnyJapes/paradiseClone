package psu.edu.paradiseClone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	

	Address findById(int id);

}
