package com.triglib.listener;

/**
 * 
 * @author raviranjan
 *
 */
public class DemoListener extends AppListener {

	@Override
	public void process(String notification) {

		System.out.println("notification form " + getClass().getName() + " : " + notification);
	}

}
