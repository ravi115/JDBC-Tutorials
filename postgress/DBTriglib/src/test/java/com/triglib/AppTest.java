package com.triglib;

import com.triglib.subscriber.ISubscriber;
import com.triglib.subscriber.Subscriber;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) {
		ISubscriber subscriber = null;
		try {
			subscriber = new Subscriber();
			subscriber.subscribe("password_change");
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("I've successfully subscribed to the trigger called loglevelchange");
	}
}
