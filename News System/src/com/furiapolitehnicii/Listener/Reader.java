package com.furiapolitehnicii.Listener;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.Event.NewsReadEvent;
import com.furiapolitehnicii.Event.NewsUpdateEvent;
import com.furiapolitehnicii.News.News;
import com.furiapolitehnicii.Resource.ConcurentQueue;

public abstract class Reader implements Listener, Runnable {
	private String name;
	private ConcurentQueue<Event> events;

	public Reader(String name, ConcurentQueue<Event> events) {
		this.name = name;
		this.events = events;
	}

	public String getName() {
		return this.name;
	}

	public void onNewsReadEvent(News news) {
		news.incrementNumberOfViews();
		Event newsEvent = new NewsReadEvent(news);
		events.add(newsEvent);
	}

	@Override
	public void dispatch(Event event) {
		System.out.println("Hello " + name + ", " + event.getIntro()
				+ System.lineSeparator() + event.getNews());
		if (isReadEvent(event))
			onNewsReadEvent(event.getNews());
	}

	private static boolean isReadEvent(Event event) {
		String eventType = event.getType();
		if (eventType.equals(NewsAppearEvent.class.getSimpleName())
				|| eventType.equals(NewsUpdateEvent.class.getSimpleName()))
			return true;
		return false;
	}
}
