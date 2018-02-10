package com.jdbc.tutorials.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.jdbc.tutorials.connection.DBConnectionUtil;

public class BatchDAO extends DBConnectionUtil {

	
	public void insert_using_batch() {
		
		System.out.println("\n\n<---- Executing Batch Operation ---- >\n");
		try(Scanner scan = new Scanner(System.in)) {
			
			
			long time = System.currentTimeMillis();
			Date date = new Date(time);

			PreparedStatement preparedStatement = 
					connection.prepareStatement("INSERT INTO student (id, name, age, date) VALUES (?, ?, ?, ?) ");
			
			preparedStatement.setDate(4, date);
			
			while(true) {
				
				System.out.println("\n<----- Let's prepare batch ------>\n");
				
				System.out.print("\nEnter Id: ");
				preparedStatement.setInt(1, scan.nextInt());
				scan.nextLine();
				System.out.print("\nEnter Name: ");
				preparedStatement.setString(2, scan.nextLine());
				
				System.out.print("\nEnter Age: ");
				preparedStatement.setInt(3, scan.nextInt());
				scan.nextLine();
				
				preparedStatement.addBatch();
				
				System.out.println("\n---- want to more Entries: Press Y else N : ");
				String str = scan.nextLine();
				if(str.equalsIgnoreCase("n"))
					break;
			}
			
			int[] n = preparedStatement.executeBatch();
			
			System.out.println("<-- check the returned values --- >\n");
			for(int n1: n) {
				System.out.println(n1);
			}
		}catch(Exception e) {
			
		}
	}
}
