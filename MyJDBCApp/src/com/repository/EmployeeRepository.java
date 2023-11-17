package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.InvalidIdException;
import com.model.Employee;
import com.mysql.cj.xdevapi.PreparableStatement;

public class EmployeeRepository {

	private String url = "jdbc:mysql://localhost:3306/jdbcapp";
	private String userdb = "root";
	private String passdb = "Abc@123#";
	private String driver = "com.mysql.cj.jdbc.Driver";
	Connection con;

	public void dbConnect() {
		// load the driver
		try {
			Class.forName(driver);
			// System.out.println("driver loaded...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Establish Connection
		try {
			con = DriverManager.getConnection(url, userdb, passdb);
			// System.out.println("conn establish..");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// close Connection
	public void dbClose() {
		try {
			con.close();
			// System.out.println("conn close..");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertEmployee(Employee employee) {
		dbConnect();
		String sql = "insert into employee(name,email,salary,city) values(?,?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setString(4, employee.getCity());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		dbClose();
	}

	public List<Employee> fetchAllEmployees() {
		dbConnect();
		String sql = "select * from employee";
		List<Employee> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			ResultSet rst = preparedStatement.executeQuery();
			while (rst.next()) {
				Employee employee = new Employee();
				// fetch each coloum from db and save it in object
				employee.setId(rst.getInt("id"));
				employee.setName(rst.getString("name"));
				employee.setEmail(rst.getString("email"));
				employee.setSalary(rst.getDouble("salary"));
				employee.setCity(rst.getString("city"));
				// save obj in list
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	
	public Employee getOneEmployee(int id) {
		dbConnect();
		String sql = "select * from employee where id=?";
		Employee employee=new Employee();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rst = preparedStatement.executeQuery();
			if(rst.next()) {
				// fetch each coloum from db and save it in object
				employee.setId(rst.getInt("id"));
				employee.setName(rst.getString("name"));
				employee.setEmail(rst.getString("email"));
				employee.setSalary(rst.getDouble("salary"));
				employee.setCity(rst.getString("city"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return employee;
	}

	public void deleteEmployee(int id) {
		dbConnect();
		String sql = "delete from employee where id=?";
			try {
		    PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbClose();	
	}
}
