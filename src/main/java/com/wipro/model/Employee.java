package com.wipro.model;

import java.io.Serializable;
import java.util.Comparator;

public class Employee implements Serializable,Comparable<Employee>{

	private int empno;
	private transient String ename;
	private Salary sal;

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Salary getSal() {
		return sal;
	}

	public void setSal(Salary sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", sal=" + sal + "]";
	}

	@Override
	public int compareTo(Employee e1) {	
		return this.empno - e1.empno;
	}
	
	public static Comparator<Employee> nameComparator = new Comparator<Employee>() {
		
		@Override
		public int compare(Employee e1, Employee e2) {
			
			return e1.getEname().compareTo(e2.getEname());
		}
	};
	

}
