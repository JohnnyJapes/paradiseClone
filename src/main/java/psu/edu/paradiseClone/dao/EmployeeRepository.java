package psu.edu.paradiseClone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import psu.edu.paradiseClone.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	//no need to write code

	public List<Employee> findBySalaryGreaterThan(int salary);
}