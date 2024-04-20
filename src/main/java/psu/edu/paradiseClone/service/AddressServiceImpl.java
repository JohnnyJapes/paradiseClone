package psu.edu.paradiseClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psu.edu.paradiseClone.dao.AddressRepository;
import psu.edu.paradiseClone.entity.Address;

@Service
public class AddressServiceImpl implements AddressService{

	private AddressRepository addressRepository;
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}


	@Override
	public void save(Address newAddress) {
		// TODO Auto-generated method stub
		addressRepository.save(newAddress);
		
	}


	@Override
	public Address findById(int id) {
		
		return  addressRepository.findById(id);
		
	}


	@Override
	public void deleteById(Integer id) {
		addressRepository.deleteById(id);
		
	}
	
	

}
