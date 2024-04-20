package psu.edu.paradiseClone.service;

import java.util.List;

import psu.edu.paradiseClone.entity.Employee;

public interface EmployeeService {
	
	
	List<Employee> findAll();
	
	Employee findById(int ID);
	
	Employee save(Employee theEmployee);
	
	void deleteById(int ID);
	
	List<Employee> findBySalaryGreaterThan(int salary);

}
