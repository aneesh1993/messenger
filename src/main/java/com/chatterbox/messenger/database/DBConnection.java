package com.chatterbox.messenger.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
	
	private static String USERNAME = "aneesh";
	private static String PASSWORD = "root";
	private static String URL = "jdbc:mysql://localhost:3306/messenger?useSSL=false";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private Connection conn = null;
	
	public Connection getDBConnection(){
		
		try {
			Class.forName(DRIVER).newInstance();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	
}
