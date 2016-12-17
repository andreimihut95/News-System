package com.furiapolitehnicii.Editorial;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;
import com.furiapolitehnicii.Resource.ConcurentQueue;

public class Editorial implements Runnable {
	private String name;
	private Dispatcher dispatcher;
	private ConcurentQueue<Event> events;

	public Editorial(String name, Dispatcher dispatcher,
			ConcurentQueue<Event> events) {
		this.name = name;
		this.dispatcher = dispatcher;
		this.events = events;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void subscribe(Class<? extends Event> eventType, Filter filter,
			Listener listener) {
		dispatcher.subscribeListener(eventType, filter, listener);
	}
	public void subscribe(Class<? extends Event> eventType, Listener listener) {
		dispatcher.subscribeListener(eventType, null, listener);
	}

	public void unsubscribe(Class<? extends Event> eventType, Filter filter,
			Listener listener) {
		dispatcher.unsubscribeListener(eventType, filter, listener);
	}

	@Override
	public void run() {
		while (true) {
			Event event = events.remove();
			dispatcher.dispatch(event);
		}
	}
}
