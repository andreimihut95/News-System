package com.furiapolitehnicii.Listener;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Event.Event;

public interface Listener {
	void dispatch(Event event);
	void attachDispatcher(Dispatcher dispatcher);
    void dettachDispatcher(Dispatcher dispatcher);
}
