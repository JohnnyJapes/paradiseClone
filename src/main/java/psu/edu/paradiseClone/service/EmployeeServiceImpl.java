package psu.edu.paradiseClone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import psu.edu.paradiseClone.dao.EmployeeRepository;
import psu.edu.paradiseClone.entity.Employee;
import psu.edu.paradiseClone.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository empRepository;
	
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empRepository) {
		this.empRepository = empRepository;
	}
	
	@Override
	public List<Employee> findAll() {
		return empRepository.findAll();
	}

	@Override
	public Employee findById(int ID) {
		Optional<Employee> result = empRepository.findById(ID);
		if (result.isPresent()) return  result.get();
		else
			throw new RuntimeException("Did not find employee ID - " +ID);
	}

	@Transactional
	@Override
	public Employee save(Employee theEmployee) {
		return empRepository.save(theEmployee);
	}

	@Transactional
	@Override
	public void deleteById(int ID) {
		empRepository.deleteById(ID);
		return;
		
	
		
	}

	@Override
	public List<Employee> findBySalaryGreaterThan(int salary) {
		
		return empRepository.findBySalaryGreaterThan(salary);
	}

}
