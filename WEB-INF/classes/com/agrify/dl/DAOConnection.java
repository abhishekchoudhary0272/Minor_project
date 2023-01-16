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
			// Old class do not use
			// Class.forName("com.mysql.jdbc.Driver");
			// New class, but class should now be loaded automatically although will still
			// produces warning
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mandi", "chinmay", "chinmay");
			return connection;
		} catch (Exception e) {
			// System.out.println(e);
			throw new Exception(e.getMessage());
		}
	}
}