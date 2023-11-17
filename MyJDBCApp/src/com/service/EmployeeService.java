package com.service;

import java.util.List;
import java.util.stream.Collectors;

import com.exception.InvalidIdException;
import com.exception.InvalidInputException;
import com.model.Employee;
import com.repository.EmployeeRepository;

public class EmployeeService {

	EmployeeRepository employeeRepository = new EmployeeRepository();

	public void insertEmployee(Employee employee) {
		employeeRepository.insertEmployee(employee);

	}

	public List<Employee> fetchAllEmployees() {
		
		return employeeRepository.fetchAllEmployees();
	}
	public Employee getOneEmployee(int id) throws InvalidIdException {
		Employee employee=employeeRepository.getOneEmployee(id);
		if(employee.getId()==0) {
			throw new InvalidIdException("Invalid ID given try again");
		}
		return employee;
	}

	public void deleteEmployee(int id) throws InvalidIdException {
		getOneEmployee(id);
		employeeRepository.deleteEmployee(id);
	}

	public List<Employee> filterEmployees(String city) throws InvalidInputException {
		if(city==null)
			throw new InvalidInputException("input value of city is null");
		List<Employee> list=fetchAllEmployees();
		list=list.stream()
				.filter(e->e.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());
		return list;
	}

}
