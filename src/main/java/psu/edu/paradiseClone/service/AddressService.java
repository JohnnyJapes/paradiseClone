package psu.edu.paradiseClone.service;

import psu.edu.paradiseClone.entity.Address;

public interface AddressService {

	public void save(Address newAddress);
	public Address findById(int id);
	public void deleteById(Integer id);
}
