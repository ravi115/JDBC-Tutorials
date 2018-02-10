package com.jdbc.tutorials.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.tutorials.connection.DBConnectionUtil;
import com.jdbc.tutorials.entity.Student;

public class ImageStoreDAO extends DBConnectionUtil {

	private FileInputStream readImage() {
		
		FileInputStream fileInputStream = null;
		try {
			
			fileInputStream = new FileInputStream("img\\flower.JPG");
			
		}catch(Exception e) {
			System.out.println("Caught Exception while reading image : " + e.getMessage());
		}
		
		return fileInputStream;
	}
	
	
	public void store_image(Student student) {
		
		System.out.println("\n\n<--- *** saving image*** ------->");
		
		try {
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			
			final String query = "insert into student(id, name, age, date, updated, photo) values(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setDate(4, date);
			preparedStatement.setDate(5, null);
			
			//read image file into inputStream
			
			FileInputStream fileInputStream = readImage();
			if(null != fileInputStream) 
				preparedStatement.setBinaryStream(6, fileInputStream, fileInputStream.available());
			
			int n = preparedStatement.executeUpdate();
			
			if(n > 0) {
				System.out.println("Date stored successfully...");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void retriveImage() {
		
		System.out.println("\n\n<--- *** retrive image from database *** ------->");
		FileOutputStream file  = null;
		try {
			
			Blob blob = null;
			ResultSet result = statement.executeQuery("select * from student");
			while(result.next()) {
				
				System.out.println("Id : " 		+ result.getInt("id"));
				System.out.println("Name : " 	+ result.getString("name"));
				System.out.println("AGE : " 	+ result.getInt("age"));
				System.out.println("Date : " 	+ result.getDate("date"));
				System.out.println("Updated : " + result.getDate("updated"));
				
				blob = result.getBlob("photo");
				if(blob != null) {
					
					//InputStream inputStream = blob.getBinaryStream();
					
					byte[] b_img = blob.getBytes(1, (int)blob.length());
					file = new FileOutputStream("img_from_db\\pic.JPG");
					file.write(b_img);
				}
			}
			
		}catch(Exception e) {
			System.out.println("caught exception while retriving image from database : " + e.getMessage());
		}finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
