package com.controller;

import java.util.List;
import java.util.Scanner;

import com.exception.InvalidIdException;
import com.exception.InvalidInputException;
import com.model.Employee;
import com.service.EmployeeService;

public class EmployeeController {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EmployeeService employeeService = new EmployeeService();
		while (true) {
			System.out.println("****Employee DB Ops****");
			System.out.println("1. Insert Employee ");
			System.out.println("2. Fetch All Employees ");
			System.out.println("3. Fetch Employee by ID ");
			System.out.println("4. Delete Employee by ID ");
			System.out.println("5. Filter Employee by city");
			System.out.println("0. exit ");
			System.out.println("*****************");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting bye...");
				break;
			}
			switch (input) {

			case 1:
				System.out.println("Enter Employee Data ");
				Employee employee = new Employee();
				System.out.println("Enter name");
				employee.setName(sc.next());
				System.out.println("Enter email");
				employee.setEmail(sc.next());
				System.out.println("Enter salary");
				employee.setSalary(sc.nextDouble());
				System.out.println("Enter city");
				employee.setCity(sc.next());
				employeeService.insertEmployee(employee);
				System.out.println("Employee Inserted in DB");
				break;
			case 2:
				List<Employee> list=employeeService.fetchAllEmployees();//e1,e2,e3...
				list.stream().forEach(e->System.out.println(e));
				break;
			case 3:
				System.out.println("Enter employee Id");
				int id=sc.nextInt();
				try {
					employee=employeeService.getOneEmployee(id);
					System.out.println(employee);
					
				} catch (InvalidIdException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter employee Id");
				id=sc.nextInt();
				try {
					employeeService.deleteEmployee(id);
					System.out.println("Employee deleted..");
					
				} catch (InvalidIdException e1) {
					System.out.println(e1.getMessage());
				}
				
				break;
			case 5:
				System.out.println("Enter the city to filter the record");
				String city=sc.next();
				try {
					list=employeeService.filterEmployees(city);
					if(list.size()==0) {
						System.out.println("No Employee found");
					}
					list.stream().forEach(e->System.out.println(e));
				} catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				break;
			default:
				System.out.println("invalid entry try again");
				break;

			}

		}
		sc.close();
	}

}
