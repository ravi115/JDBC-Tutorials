package com.triglib.connection;

import java.sql.SQLException;
import java.sql.Statement;

import com.impossibl.postgres.api.jdbc.PGConnection;
import com.impossibl.postgres.api.jdbc.PGNotificationListener;
import com.impossibl.postgres.jdbc.PGDataSource;
import com.triglib.listener.AppListener;
import com.triglib.utility.AppConfig;
import com.triglib.utility.AppInstance;

/**
 * Establishes the connection with postgres and also start listening to the
 * notification.
 * 
 * @author ravi ranjan kumar
 *
 */
public class PostgresConnection {

	private static PGConnection connection;

	static {
		try {

			if (connection == null) {

				PGDataSource dataSource = new PGDataSource();
				dataSource.setHost(AppConfig.getValue("db.host"));
				dataSource.setPort(Integer.parseInt(AppConfig.getValue("db.port")));
				dataSource.setDatabase(AppConfig.getValue("db.database"));
				dataSource.setUser(AppConfig.getValue("db.username"));
				dataSource.setPassword(AppConfig.getValue("db.password"));

				System.out.println("Establishing connection to the postgres db");
				connection = (PGConnection) dataSource.getConnection();

				if (connection != null) {
					init();
				}
			}

		} catch (Exception e) {
			System.out.println("Unbale to connect to the database");
			e.printStackTrace();
		}
	}

	private PostgresConnection() {
	}

	/**
	 * This method will receive notification from postgres and based on the channel
	 * Id it will create the object of that class at runtime.
	 */
	private static void init() {
		try {

			PGNotificationListener listener = (int processId, String channelName, String payload) -> {

				System.out.println("process Id " + processId);
				System.out.println("channel Name " + channelName);

				AppListener appListener = (AppListener) AppInstance.getInstance(AppConfig.getValue(channelName));
				if (null != appListener) {
					appListener.process(payload);
				} else {
					try {
						throw new Exception("Unable to load " + AppConfig.getValue(channelName) + " Listener class ");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			connection.addNotificationListener(listener);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * It will start listening to the postgres notification.
	 * 
	 * @param listen
	 */
	public static void listen(final String listen) {

		if (null != connection) {

			try (Statement statement = connection.createStatement()) {
				statement.execute("LISTEN " + listen);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * It stop listening to the postgres notification.
	 * 
	 * @param unlisten
	 */
	public static void unlisten(final String unlisten) {

		if (null != connection) {

			try (Statement statement = connection.createStatement()) {
				statement.execute("UNLISTEN " + unlisten);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * closes the connection to the postgres.
	 */
	public static void shutdown() {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
