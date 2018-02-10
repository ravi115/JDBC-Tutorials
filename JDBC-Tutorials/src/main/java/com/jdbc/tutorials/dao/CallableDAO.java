package com.jdbc.tutorials.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.tutorials.connection.DBConnectionUtil;
import com.jdbc.tutorials.entity.Student;

/**
 * CallableStatement interface is used to call the stored procedures and functions.
 * 
 * @author raviranjan
 *
 */
public class CallableDAO extends DBConnectionUtil {




	public void insert_using_StoredProcedure(Student student) {

		System.out.println("\n\n<-- Running stored procedure ---- >\n\n");
		try {
			long time = System.currentTimeMillis();
			Date date = new Date(time);

			CallableStatement callableStatement =
					connection.prepareCall("{call insert_student(?, ?, ? ,? ,? )}");

			callableStatement.setInt(1, student.getId());
			callableStatement.setString(2, student.getName());
			callableStatement.setInt(3, student.getAge());
			callableStatement.setDate(4, date);
			callableStatement.setDate(5, null);

			callableStatement.executeUpdate();

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}


	}

	public List<Student> fetch_using_storedProcedure(int id) {
		
		System.out.println("\n\n<---- doing select operation ---- >\n");
		List<Student> Students = new ArrayList<>();
		
		try {

			CallableStatement callableStatement =
				    connection.prepareCall("{call select_operation(?)}");
			callableStatement.setInt(1, id);
			
			ResultSet result = callableStatement.executeQuery();
			
			
			while(result.next()) {

				Student theStudent  = new Student();
				theStudent.setId(result.getInt("id"));
				theStudent.setName(result.getString("name"));
				theStudent.setAge(result.getInt("age"));
				theStudent.setDate(result.getDate("date"));
				theStudent.setUpdated(result.getDate("updated"));
				Students.add(theStudent);
			}
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
		return Students;
	}

}
