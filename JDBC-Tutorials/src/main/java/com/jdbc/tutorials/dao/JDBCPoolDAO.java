package com.jdbc.tutorials.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.jdbc.tutorials.pool.DBConnectionPool;

public class JDBCPoolDAO {


	public void poolTest() {

		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		DBConnectionPool dBConnectionPool = new DBConnectionPool();
		try {

			DataSource dataSource = dBConnectionPool.setUpPool();
			dBConnectionPool.printDbStatus();

			System.out.println("\n<--- *** making a new connection ** ---->");
			connection  = dataSource.getConnection();
			dBConnectionPool.printDbStatus();

			preparedStatement = connection.prepareStatement("SELECT * FROM student");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("Username: " + resultSet.getString("name"));
			}
			System.out.println("\n=====Releasing Connection Object To Pool=====\n");
			
		}catch(Exception e) {

			System.out.println(e.getMessage());
		}finally {
			try {
				// Closing ResultSet Object
				if(resultSet != null) {
					resultSet.close();
				}
				// Closing PreparedStatement Object
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				// Closing Connection Object
				if(connection != null) {
					connection.close();
				}
			} catch(Exception sqlException) {
				sqlException.printStackTrace();
			}
		}
		dBConnectionPool.printDbStatus();
	}
}
