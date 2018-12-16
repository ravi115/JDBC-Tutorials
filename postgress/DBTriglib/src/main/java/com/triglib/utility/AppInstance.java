package com.triglib.utility;

/**
 * 
 * @author raviranjan
 *
 */
public class AppInstance {

	/**
	 * 
	 * @param appName
	 * @return
	 */
	public static Object getInstance(final String appName) {
		Object object = null;

		try {
			Class<?> clazz = Class.forName("com.triglib.listener." + appName + "Listener");
			object = clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}
}
