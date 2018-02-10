package com.jdbc.tutorials.dao;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.tutorials.connection.DBConnectionUtil;

public class IndexDAO extends DBConnectionUtil{


	public void createIndex() {

		System.out.println("\n<--- *** creating the index ***------>");
		try {

			int n = statement.executeUpdate("CREATE INDEX MyIndex ON student(name, date)");
			if(n > 0) {
				System.out.println("index got created successfully........!!");
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 *ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate)
                       throws SQLException 
	 * 
	 */
	public void getIndex() {

		System.out.println("This is just to display to index...!!");
		try {

			DatabaseMetaData dbMetaData = connection.getMetaData();
			ResultSet result  = dbMetaData.getIndexInfo(null, "training", "student", false, true);
			
			while(result.next()) {
				
				System.out.println(result.getString("INDEX_NAME"));
				System.out.println(result.getBoolean("NON_UNIQUE"));
				System.out.println(result.getShort("TYPE"));
				System.out.println(result.getInt("ORDINAL_POSITION"));
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void getIndex2() {

		System.out.println("Retrive indexes using [ information_schema.statistics ]--");
		try {
			 PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT INDEX_NAME FROM information_schema.statistics WHERE table_name = ?");
		        ps.setString(1, "student");
		        ResultSet rs = ps.executeQuery();

		        while(rs.next()){
		            System.out.println(rs.getString(1));
		        }
		        rs.close();
		        ps.close();//important to close to prevent resource leaks
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void drop_index() {

		System.out.println("\n< -- dropping the index ** ------->");
		try {
			
			int n = statement.executeUpdate("ALTER TABLE student DROP INDEX MyIndex;");
			if(n > 0) {
				System.out.println("deleted the index..");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
