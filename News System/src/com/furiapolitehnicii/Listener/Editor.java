package com.furiapolitehnicii.Listener;

import java.util.Queue;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.Event.NewsDeleteEvent;
import com.furiapolitehnicii.Event.NewsUpdateEvent;
import com.furiapolitehnicii.News.News;

public class Editor implements Listener {
	private String name;
	private Queue<Event> events;

	public Editor(String name, Queue<Event> events) {
		this.name = name;
		this.events = events;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void dispatch(Event event) {
		System.out.println("Hei " + name + ", " + event.getIntro() + System.lineSeparator() + event.getNews().getTitle()
				+ System.lineSeparator());
	}

	public void onNewsAppearEvent(News news) {
		Event newsAppearEvent = new NewsAppearEvent(news);
		events.add(newsAppearEvent);
	}

	public void onNewsDeleteEvent(News news) {
		news.delete();
		Event newsAppearEvent = new NewsDeleteEvent(news);
		events.add(newsAppearEvent);

	}

	public void onNewsUpdateEvent(News news) {
		Event newsAppearEvent = new NewsUpdateEvent(news);
		events.add(newsAppearEvent);
	}

}
