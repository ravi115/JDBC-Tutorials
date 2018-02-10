package com.jdbc.tutorials.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnectionUtil {

	private static final String username= "root";
	private static final String password= "root";
	private static final String database_url = "jdbc:mysql://localhost:3306/training";

	protected static Connection connection = getConnection();
	protected static Statement statement  = getStatement();


	private static Connection getConnection() {

		Connection connection = null;
		if(null == connection) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(database_url, username, password);
			}catch(Exception e) {
				System.out.println("Caught Exception is : " + e.getMessage());
			}
		}

		return connection;
	}

	private static Statement getStatement() {

		Statement statement = null;
		if(null == statement) {
			try {
				statement = connection.createStatement();
			}catch(Exception e) {
				System.out.println("Caught Exception is : " + e.getMessage());
			}
		}
		return statement;
	}

	protected void close() {

		try {
			if(null != connection)
				connection.close();
			if(null != statement)
				statement.close();
		}catch(Exception e) {
			System.out.println("caught Exception is : " + e.getMessage());
		}
	}
}
