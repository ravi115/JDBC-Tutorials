package com.jdbc.tutorials.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.tutorials.connection.DBConnectionUtil;
import com.jdbc.tutorials.entity.Student;


public class BasicDAO extends DBConnectionUtil {


	public List<Student> select() {

		List<Student> student = new ArrayList<>();
		String sql = "SELECT * from student";
		try {

			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {

				Student theStudent  = new Student();
				theStudent.setId(result.getInt("id"));
				theStudent.setName(result.getString("name"));
				theStudent.setAge(result.getInt("age"));
				theStudent.setDate(result.getDate("date"));
				theStudent.setUpdated(result.getDate("updated"));
				student.add(theStudent);
			}
		}catch(Exception e) {
			System.out.println("Caught Exception is : " + e.getMessage());
		}
		return student;
	}

	public void insert(Student student) {

		try {
			long time = System.currentTimeMillis();
			Date date = new Date(time);

			PreparedStatement preparedStatement = 
					connection.prepareStatement("INSERT INTO student (id, name, age, date) VALUES (?, ?, ?, ?) ");

			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setDate(4, date);

			int rowInserted = preparedStatement.executeUpdate();

			if(rowInserted > 0 ) {
				System.out.println("A new Student hasbeen added successfully.......!!");
			}
		}catch(Exception e) {
			System.out.println("Caught Exception is : " + e.getMessage());
		}
	}

	public void update(int id, Student student) {

		try {
			
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			
			String updateQuery = "update student set name = ?, age = ? , updated = ? where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setDate(3, date);
			preparedStatement.setInt(4, id);
			
			int rowUpdated  = preparedStatement.executeUpdate();
			if(rowUpdated > 0 ) {
				System.out.println("update student with the id : " + id);
			}
		}catch(Exception e) {
			System.out.println("Caught Exception : " + e.getMessage());
		}
	}

	public void delete(int id) {

		try {
			
			String deleteQuery = "DELETE FROM student WHERE id = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setInt(1, id);
			int rowDeleted = preparedStatement.executeUpdate();
			if(rowDeleted >  0 ) {
				System.out.println("deleted the student with id : " + id);
			}
			
		}catch(Exception e) {
			System.out.println("Caught Exception is : " + e.getMessage());
		}
	}

	public void indexing() {

		try {

		}catch(Exception e) {
			System.out.println("Caught Exception is : " + e.getMessage());
		}
	}
}
