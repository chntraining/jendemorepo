package com.wipro.view;

import java.util.Scanner;

import com.wipro.controller.EmployeeController;
import com.wipro.controller.EmployeeInterface;

public class EmployeeMain {

	public static void main(String[] args) {
		EmployeeInterface ec = new EmployeeController();  
		Scanner sc = new Scanner(System.in);
		String ch =null;
		do {
		System.out.println("Enter your choice");
		System.out.println("1. Add Employee");
		System.out.println("2. View Employee");
		System.out.println("3. Serialize Employee");
		System.out.println("4. Deserialize Employee");
		System.out.println("5. Update Employee");
		System.out.println("6. Delete Employee");
		System.out.println("7. Call Producure to insert");
		System.out.println("8. Get Employee by ID");
		System.out.println("9. Sort by empno");
		System.out.println("10. Sort by emp name");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1: {
			ec.addEmployee();
			break;
		}
		case 2: {
			ec.viewEmployee();
			break;
		}
		case 3: {
			ec.serializeEmployee();
			break;
		}
		case 4: {
			ec.deserializeEmployee();
			break;
		}
		case 5: {
			ec.updateEmployee();
			break;
		}
		case 6: {
			ec.deleteEmployee();
			break;
		}
		case 7: {
			ec.callProcToInsertEmployee();
			break;
		}
		case 8: {
			ec.getEmpById();
			break;
		}
		case 9: {
			ec.sortByEmpid();
			break;
		}
		case 10: {
			ec.sortByEmpName();
			break;
		}
		default: {
			System.out.println("Enter a proper choice :(");
			break;
		}
		}
		System.out.println("DO u want to continue? Y | y");
		ch = sc.next();
		}while(ch.equals("Y")  || ch.equals("y"));
		System.out.println("Thanks for using our Appln !!!");
		System.exit(0);
		
	}

}
