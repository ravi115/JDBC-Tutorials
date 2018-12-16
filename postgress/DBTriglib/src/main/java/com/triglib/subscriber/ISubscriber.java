package com.triglib.subscriber;

/**
 * 
 * @author raviranjan
 *
 */
public interface ISubscriber extends AutoCloseable {

	public void subscribe(String subscribe);

	public void unsubscribe(String unsubscribe);

}
