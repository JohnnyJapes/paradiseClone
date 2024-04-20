package psu.edu.paradiseClone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psu.edu.paradiseClone.dao.ShipmentRepository;
import psu.edu.paradiseClone.entity.Shipment;

@Service
public class ShipmentServiceImpl implements ShipmentService{

	ShipmentRepository shipRepo;
	
	@Autowired
	public ShipmentServiceImpl(ShipmentRepository shipRepo) {
		super();
		this.shipRepo = shipRepo;
	}

	
	@Override
	public void save(Shipment shipment) {
		shipRepo.save(shipment);
		
	}


}
