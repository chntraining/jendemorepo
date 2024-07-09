package com.wipro.exception;

public class EmployeeNotFounException {

	public EmployeeNotFounException() {
		
	}
	
	public EmployeeNotFounException(String message) {
		System.out.println(message);
	}
	
	
	@Override
	public String toString() {
		return "EmployeeNotFounException to string";
	}
	
	

}
