package com.cglia.dao;

import java.sql.*;

public class DBConnection {

	public static Connection getConnection() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.60.22:3306/rahul_gupta","RAHUL","RAHUL@12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
