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
		
			String DB_NAME = "";
			String DB_HOST = "";
			String DB_USER = "";
			String DB_PASS = "";
			String DB_URL = "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME;

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