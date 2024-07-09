package com.wipro.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.wipro.dao.EmployeeDao;
import com.wipro.model.Employee;
import com.wipro.model.Salary;

public class EmployeeController implements EmployeeInterface {
	Employee emp;
	Scanner sc = new Scanner(System.in);
	Salary salary;
	List<Employee> emplist = new ArrayList();
	EmployeeDao dao = new EmployeeDao();

	public void addEmployee() {
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
		salary.setHra();
		salary.setPf();
		salary.setGross();
		salary.setNet();
		emp.setSal(salary);
		emplist.add(emp);
		dao.saveEmployee(emp);
		System.out.println("Employee Successfully Added !!!");
	}

	@Override
	public void viewEmployee() {
		// System.out.println(emp.getEmpno() + " " + emp.getEname());
		// System.out.println(emplist);
//		Iterator<Employee> it = emplist.iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}

		emplist.forEach(li -> System.out.println(li));
		emplist= dao.viewEmployee();
		System.out.println("After fetching from DB, emplist = ");
		emplist.forEach(li -> System.out.println(li));
	}

	public void getEmpById() {
		System.out.println("Enter your empno");
		int eno = sc.nextInt();
		emp = dao.getEmpById(eno);
		System.out.println(emp);

	}

	@Override
	public void serializeEmployee() {
		try {
			FileOutputStream fos = new FileOutputStream("empser.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(emplist);
			System.out.println("Employee Serialized....");

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deserializeEmployee() {

		try {
			FileInputStream fis = new FileInputStream("empser.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			List<Employee> elist = (List<Employee>) ois.readObject();
			System.out.println(elist);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void updateEmployee() {
		dao.updateEmployee();

	}

	@Override
	public void deleteEmployee() {
		dao.deleteEmployee();
	}

	@Override
	public void callProcToInsertEmployee() {

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
		salary.setHra();
		salary.setPf();
		salary.setGross();
		salary.setNet();
		emp.setSal(salary);

		dao.callProc(emp);
		System.out.println("Procedure success !!!");

	}

	@Override
	public void sortByEmpid() {
		System.out.println("*** After sorting ****");
		Collections.sort(emplist);
		System.out.println(emplist);
		
	}

	@Override
	public void sortByEmpName() {
		System.out.println("*** After sorting by Name****");
		Collections.sort(emplist, Employee.nameComparator);
		emplist.forEach(li -> System.out.println(li));
		
	}

}
