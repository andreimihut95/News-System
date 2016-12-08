package com.furiapolitehnicii.Listener;

import java.util.HashSet;
import java.util.Set;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.Event.NewsDeleteEvent;
import com.furiapolitehnicii.Event.NewsUpdateEvent;
import com.furiapolitehnicii.News.News;

public class Editor implements Listener {
	private String name;
	private Set<Dispatcher> newsDispatcher = new HashSet<Dispatcher>();
	
	public Editor(String name) {
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}

	@Override
	public void dispatch(Event event) {
		System.out.println("Hei " +name +", " + event.getIntro() + System.lineSeparator() + event.getNews());
	}

	@Override
	public void attachDispatcher(Dispatcher dispatcher) {
		newsDispatcher.add(dispatcher);
		
	}

	@Override
	public void dettachDispatcher(Dispatcher dispatcher) {
		newsDispatcher.remove(dispatcher);
		
	}
	public void onNewsAppearEvent(News news)
	{
		Event newsAppearEvent = new NewsAppearEvent(news);
		for(Dispatcher dispatcher : newsDispatcher)
			dispatcher.publish(newsAppearEvent);
	}
	public void onNewsDeleteEvent(News news)
	{
		Event newsAppearEvent = new NewsDeleteEvent(news);
		for(Dispatcher dispatcher : newsDispatcher)
			dispatcher.publish(newsAppearEvent);
	}
	public void onNewsUpdateEvent(News news)
	{
		Event newsAppearEvent = new NewsUpdateEvent(news);
		for(Dispatcher dispatcher : newsDispatcher)
			dispatcher.publish(newsAppearEvent);
	}

}
