package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.exception.InvalidIdException;
import com.exception.InvalidInputException;
import com.model.Employee;
import com.service.EmployeeService;

public class EmployeeServiceTest {

	private EmployeeService employeeService = new EmployeeService();

	@Test
	public void insertTest() {
		// num of record before insert
		int numOfRecords = employeeService.fetchAllEmployees().size();

		Employee employee = new Employee();
		employee.setName("test 1");
		employee.setCity("test 1");
		employee.setEmail("test@tse.com");
		employee.setSalary(100);
		employeeService.insertEmployee(employee);
		assertEquals(numOfRecords + 1, employeeService.fetchAllEmployees().size());

	}

	@Test
	public void getOneEmployeesTest() {

		Employee employee = new Employee();
		employee.setId(4);
		employee.setName("radha");
		employee.setEmail("rad@gmail.com");
		employee.setSalary(2500002d);
		employee.setCity("delhi");
		//use case :ID is valid
		try {
			assertEquals(employee, employeeService.getOneEmployee(4));
		} catch (InvalidIdException e) {

		}
		//use case :ID is Invalid
		try {
			assertEquals(employee, employeeService.getOneEmployee(40));
		} catch (InvalidIdException e) {			
			assertEquals("Invalid ID given try again", e.getMessage());
		}		
	}
	@Test
	public void deleteEmployeeTest() {
		int numOfRecords = employeeService.fetchAllEmployees().size();
		//use case 1: id is valid
		try {
			employeeService.deleteEmployee(4);
			assertEquals(numOfRecords -1, employeeService.fetchAllEmployees().size());
		} catch (InvalidIdException e) {}	
				
		//use case 2 id is invalid
		try {
			employeeService.deleteEmployee(40);
			assertEquals(numOfRecords -1, employeeService.fetchAllEmployees().size());
		} catch (InvalidIdException e) {
			assertEquals("Invalid ID given try again", e.getMessage());
	}

	}
	@Test
	public void filterEmployeesTest() {
		try {
		assertEquals(2,employeeService.filterEmployees("mumbai").size());
		assertEquals(1,employeeService.filterEmployees("delhi").size());
		assertEquals(2,employeeService.filterEmployees("mau").size());	
	}
		catch(InvalidInputException e) {}
		
		try {
			assertEquals(2, employeeService.filterEmployees(null).size());
		}catch(InvalidInputException e) {
			assertEquals("input value of city is null", e.getMessage());
		}
	
	}	
}
