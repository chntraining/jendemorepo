package com.wipro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection con;

	public static Connection getMyDBConn() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wipdb", "root", "root");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return con;
	}

	public static void main(String[] args) {
		System.out.println(getMyDBConn());

	}

}
