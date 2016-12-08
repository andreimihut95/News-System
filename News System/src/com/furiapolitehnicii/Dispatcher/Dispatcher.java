package com.furiapolitehnicii.Dispatcher;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;

public interface Dispatcher {
	
	void subscribeListener(Class<?> eventType , Filter filter , Listener listener);
	void unsubscribeListener(Class<?> eventType, Filter filter , Listener listener);
	void publish(Event event);
}
