package com.furiapolitehnicii.Dispatcher;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;

public interface Dispatcher {
	
	void subscribeListener(String typeEvent , Filter filter , Listener listener);
	void unsubscribeListener(String typeEvent, Filter filter , Listener listener);
	void publish(Event event);
}
