package com.wipro.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wipro.model.Employee;
import com.wipro.model.Salary;
import com.wipro.util.DBConnection;

public class EmployeeDao {

	Connection con;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	Employee emp;
	Salary sal;
	List<Employee> elist = new ArrayList<>();
	public void saveEmployee(Employee emp) {
		try {

			con = DBConnection.getMyDBConn();
			ps = con.prepareStatement("insert into employee values(?,?,?,?,?,?,?)");
			ps.setInt(1, emp.getEmpno());
			ps.setString(2, emp.getEname());
			ps.setInt(3, emp.getSal().getBasic());
			ps.setDouble(4, emp.getSal().getHra());
			ps.setDouble(5, emp.getSal().getPf());
			ps.setDouble(6, emp.getSal().getGross());
			ps.setDouble(7, emp.getSal().getNet());
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + "  inserted Successfully !!!!");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Employee> viewEmployee() {
		
		
		try {

			con = DBConnection.getMyDBConn();
			stmt = con.createStatement();

			rs = stmt.executeQuery("select * from employee");
			while (rs.next()) {
				emp = new Employee();
				sal = new Salary();
				/*System.out.println("Empno : " + rs.getInt(1));
				System.out.println("Name : " + rs.getString(2));
				System.out.println("Basic : " + rs.getInt(3));
				System.out.println("HRA : " + rs.getDouble(4));
				System.out.println("PF : " + rs.getDouble(5));
				System.out.println("Gross : " + rs.getDouble(6));
				System.out.println("Net : " + rs.getDouble(7));
				*/
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				sal.setBasic(rs.getInt(3));
				sal.setHra(rs.getDouble(4));
				sal.setPf(rs.getDouble(5));
				sal.setGross(rs.getDouble(6));
				sal.setNet(rs.getDouble(7));
				emp.setSal(sal);
				elist.add(emp);
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return elist;
	}

	public Employee getEmpById(int empid) {
		Employee emp = new Employee();
		Salary salary = new Salary();
		try {
            
			con = DBConnection.getMyDBConn();
			ps = con.prepareStatement("select * from employee where eno=" + empid);

			rs = ps.executeQuery();

			while (rs.next()) {
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));

				salary.setBasic(rs.getInt(3));
				salary.setHra(rs.getDouble(4));
				salary.setPf(rs.getDouble(5));
				salary.setGross(rs.getDouble(6));
				salary.setNet(rs.getDouble(7));
				emp.setSal(salary);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return emp;

	}

	public void updateEmployee() {
		try {

			con = DBConnection.getMyDBConn();
			ps = con.prepareStatement("");

			System.out.println(" updated  Successfully !!!!");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void deleteEmployee() {
		try {

			con = DBConnection.getMyDBConn();
			ps = con.prepareStatement("");

			System.out.println(" updated  Successfully !!!!");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void callProc(Employee emp) {
		try {

			con = DBConnection.getMyDBConn();
			CallableStatement cs = con.prepareCall("{call empproc(?,?,?,?,?,?,?)}");
			cs.setInt(1, emp.getEmpno());
			cs.setString(2, emp.getEname());
			cs.setInt(3, emp.getSal().getBasic());
			cs.setDouble(4, emp.getSal().getHra());
			cs.setDouble(5, emp.getSal().getPf());
			cs.setDouble(6, emp.getSal().getGross());
			cs.setDouble(7, emp.getSal().getNet());
			cs.execute();
			System.out.println("Employee " + emp.getEmpno() + " inserted Successfully !!!!");

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}

/*
 * delimiter $$ create procedure empproc( IN enum int, IN ename varchar(20), IN
 * basic int, IN hra decimal(8,2), IN pf decimal(8,2), IN gross decimal(8,2), IN
 * net decimal(8,2) ) begin insert into employee
 * values(enum,ename,basic,hra,pf,gross,net); end $$ delimiter ;
 * 
 * call empproc(3,"Geetha",1000,100,50,1150,1100);
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
