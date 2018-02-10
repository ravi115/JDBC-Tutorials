package com.jdbc.tutorials.app;

import java.util.List;

import com.jdbc.tutorials.dao.BasicDAO;
import com.jdbc.tutorials.dao.BatchDAO;
import com.jdbc.tutorials.dao.CallableDAO;
import com.jdbc.tutorials.dao.ImageStoreDAO;
import com.jdbc.tutorials.dao.IndexDAO;
import com.jdbc.tutorials.dao.JDBCPoolDAO;
import com.jdbc.tutorials.entity.Student;

public class JDBCApp {

	private static BasicDAO theStudentDao = new BasicDAO();

	private static void insert() {

		System.out.println("\n\n<---------- ** insert ** -------->\n\n");
		Student student1 = new Student(1, "ravi ranjan kumar", 25);
		Student student2 = new Student(2, "sunny raj", 24);
		Student student3 = new Student(3, "raunak", 26);
		Student student4 = new Student(4, "prem sagar", 23);
		Student student5 = new Student(6, "swetha", 27);
		Student student6 = new Student(7, "pooja", 25);

		theStudentDao.insert(student1);
		theStudentDao.insert(student2);
		theStudentDao.insert(student3);
		theStudentDao.insert(student4);
		theStudentDao.insert(student5);
		theStudentDao.insert(student6);
	}

	private static void update() {

		System.out.println("\n\n<---------- ** update ** -------->\n\n");

		Student student = new Student(7, "neha", 85);
		theStudentDao.update(7, student);
	}

	private static void delete() {

		System.out.println("\n\n<---------- ** delete ** -------->\n\n");
		theStudentDao.delete(3);
	}

	private static void display() {

		System.out.println("\n\n<---------- ** display ** -------->\n\n");
		for(Student students : theStudentDao.select()) {

			System.out.println(students);
		}
	}

	private static void callable_insert() {

		Student student = new Student(15, "ravi ranjan kumar", 25);

		CallableDAO objCallableDAO = new CallableDAO();
		objCallableDAO.insert_using_StoredProcedure(student);
	}


	private static void callable_select() {

		CallableDAO objCallableDAO = new CallableDAO();

		List<Student> theStudent = objCallableDAO.fetch_using_storedProcedure(15);

		System.out.println("\n\n<----- *** displaying all the student *** ---------->\n\n");
		System.out.println(theStudent);
	}

	private static void batch() {

		new BatchDAO().insert_using_batch();
	}


	private static void index() {

		IndexDAO objIndexDAO = new IndexDAO();
		objIndexDAO.createIndex();
		objIndexDAO.getIndex();
		objIndexDAO.getIndex2();
		objIndexDAO.drop_index();
	}

	private static void pool() {

		new JDBCPoolDAO().poolTest();
	}
	
	private static void storeImage() {
		
		Student student = new Student(11, "Mohan Raj", 26);
		
		ImageStoreDAO objImageStoreDAO = new ImageStoreDAO();
		objImageStoreDAO.store_image(student);
		objImageStoreDAO.retriveImage();
	}
	
	public static void main(String[] args) {

		storeImage();
		
		//pool();

		//index();

		//batch();
		//callable_insert();
		callable_select();
		//insert();
		display();
		update();
		display();
		delete();
		display();
	}

}
