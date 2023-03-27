package com.agrify.dl;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DAOConnection
 */
public class DAOConnection {
	
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			
			String DB_NAME = "mandi";
			String DB_HOST = "localhost";
			String DB_USER = "abhishek";
			String DB_PASS = "Abhi@123";
			String DB_PORT = "3306";
			String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT +"/" + DB_NAME;

			// Old class do not use
			// Class.forName("com.mysql.jdbc.Driver");
			// New class, but class should now be loaded automatically although will still
			// produces warning
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			return connection;
		} catch (Exception e) {
			// System.out.println(e);
			throw new Exception(e.getMessage());
		}
	}
}