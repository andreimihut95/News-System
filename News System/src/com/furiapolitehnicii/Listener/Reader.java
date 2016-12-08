package com.furiapolitehnicii.Listener;

import java.util.Queue;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.News.News;

public class Reader implements Listener {
	private String name;
	private Queue<Event> events;

	public Reader(String name , Queue<Event> events) {
		this.name = name;
		this.events = events;
	}

	public String getName() {
		return this.name;
	}
	public void onNewsReadEvent(News news)
	{
		news.incrementNumberOfViews();
		Event newsEvent = new NewsAppearEvent(news);
		events.add(newsEvent);
	}
	@Override
	public void dispatch(Event event) {
		System.out.println("Hello " + name + ", " + event.getIntro() + System.lineSeparator() + event.getNews());
	}

}
