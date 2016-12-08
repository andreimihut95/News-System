package com.furiapolitehnicii.Listener;

import java.util.HashSet;
import java.util.Set;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Event.Event;

public class Reader implements Listener{
	private String name;
	private Set<Dispatcher> newsDispatcher = new HashSet<Dispatcher>();
	
	public Reader(String name) {
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public void attachDispatcher(Dispatcher dispatcher)
	{
		newsDispatcher.add(dispatcher);
	}
	public void dettachDispatcher(Dispatcher dispatcher)
	{
		newsDispatcher.remove(dispatcher);
	}

	@Override
	public void dispatch(Event event) {
		System.out.println("Hello " + name+", "+ event.getIntro() + System.lineSeparator() + event.getNews());
	}

}
