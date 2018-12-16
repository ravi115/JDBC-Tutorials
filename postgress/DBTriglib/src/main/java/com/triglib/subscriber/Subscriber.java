package com.triglib.subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.triglib.connection.PostgresConnection;

/**
 * Exposed to client to subscribe to any trigger.
 * 
 * @author raviranjan
 *
 */
public class Subscriber implements ISubscriber {

	private static final Integer NO_WORKER_THREAD = 10;
	private static final ExecutorService services = Executors.newFixedThreadPool(NO_WORKER_THREAD);

	@Override
	public void subscribe(String subscribe) {

		services.submit(() -> {
			PostgresConnection.listen(subscribe);
		});
	}

	@Override
	public void unsubscribe(String unsubscribe) {

		services.submit(() -> {
			PostgresConnection.unlisten(unsubscribe);
		});
	}

	@Override
	public void close() throws Exception {
		services.submit(() -> {
			PostgresConnection.shutdown();
		});
		services.shutdown();
	}

}
