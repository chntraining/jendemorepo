package com.wipro.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wipro.dao.EmployeeDao;
import com.wipro.model.Employee;
import com.wipro.model.Salary;
import com.wipro.util.DBConnection;

public class EmployeeDaoTest {
	EmployeeDao dao;
	Employee emp;
	Salary salary;
	Scanner sc;
	PreparedStatement ps;
	ResultSet rs;
	
	static Connection con ;
	@BeforeClass
	public static void setUpClass() {
		System.out.println("From static setupclass()");
	
		con = DBConnection.getMyDBConn();
		String dropDumTable = "drop table dumemp";
		try {
			con.createStatement().execute(dropDumTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String  createDummyTable ="create table dumemp(eno int, ename varchar(20), basic int, hra decimal(8,2), pf decimal(8,2), \r\n"
				+ "gross decimal(8,2), net decimal(8,2));";
		
		try {
			con.createStatement().execute(createDummyTable);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() {
		System.out.println("Set up  before");
		dao = new EmployeeDao();
		sc = new Scanner(System.in);
	
		//emp = new Employee();
	}
	
	@Test
	public void testGetEmpById() {
		
		emp = dao.getEmpById(1);		
		assertEquals("Emp Found","Shiva",emp.getEname());
		
	}
	@Test
	public void testSaveEmployee() throws SQLException   {
		emp = new Employee();
		System.out.println("Enter your empno");
		int eno = sc.nextInt();
		System.out.println("Enter your ename");
		String ename = sc.next();

		emp.setEmpno(eno);
		emp.setEname(ename);
		salary = new Salary();
		System.out.println("Enter basic Salary");
		int basic = sc.nextInt();
		salary.setBasic(basic);
		salary.setHra((basic *0.1));
		salary.setPf((basic * 0.05));
		salary.setGross((basic+salary.getHra()+salary.getPf()));
		salary.setNet(salary.getGross() - salary.getPf());
		emp.setSal(salary);
		saveDumEmployee(emp);
		ps = con.prepareStatement("select * from dumemp where ename=?");
		ps.setString(1,ename);
		rs = ps.executeQuery();
				
		assertTrue(rs.next());
		assertEquals(eno,rs.getInt(1));
		assertEquals(ename,rs.getString(2));
		assertEquals(basic,rs.getInt(3));
		assertEquals(salary.getHra(),rs.getDouble(4),0.001);
		assertEquals(salary.getPf(),rs.getDouble(5),0.001);
		assertEquals(salary.getGross(),rs.getDouble(6),0.001);
		assertEquals(salary.getNet(), rs.getDouble(7),0.001);
		
		
		
	}
	
	public void saveDumEmployee(Employee emp) {
		try {

			con = DBConnection.getMyDBConn();
			ps = con.prepareStatement("insert into dumemp values(?,?,?,?,?,?,?)");
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

	
	@After
	public void tearDown() {
		dao=null;
		System.out.println("Tear down after ");
		
	}
	@AfterClass
	public static void tearDownClass()  {
		
		con=null;
	
		
		System.out.println("From static teardownclass()");
	}
}
