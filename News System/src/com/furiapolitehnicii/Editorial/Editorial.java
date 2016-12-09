package com.furiapolitehnicii.Editorial;

import java.util.Queue;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;

public class Editorial {
	private String name;
	private Dispatcher dispatcher;
	private Queue<Event> events;
	
	public Editorial(String name, Dispatcher dispatcher, Queue<Event> events) {
		this.name = name;
		this.dispatcher = dispatcher;
		this.events = events;
	}
	
	public String toString()
	{
		return name;
	}
	
	public String getName() {
		return name;
	}

	public void subscribe(Class<? extends Event> eventType, Filter filter, Listener listener) {
		dispatcher.subscribeListener(eventType, filter, listener);
	}

	public void unsubscribe(Class<? extends Event> eventType, Filter filter, Listener listener) {
		dispatcher.subscribeListener(eventType, filter, listener);
	}

	public void work() {
		while (!events.isEmpty()) {
			Event event = events.remove();
			dispatcher.publish(event);
		}
	}
}
