package com.wipro.controller;



public interface EmployeeInterface {
	public void addEmployee();
	public void viewEmployee();
	public void serializeEmployee();
	public void deserializeEmployee();
	public void updateEmployee();
	public void deleteEmployee();
	public void callProcToInsertEmployee();
	
	public void getEmpById();
	public void sortByEmpid();
	public void sortByEmpName();
}
