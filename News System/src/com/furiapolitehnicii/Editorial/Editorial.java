package com.furiapolitehnicii.Editorial;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.Event.NewsDeleteEvent;
import com.furiapolitehnicii.Event.NewsUpdateEvent;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;
import com.furiapolitehnicii.News.News;

public class Editorial {
	private String name;
	private Dispatcher dispatcher;
	private Queue<Event> events;
	private Set<News> news;

	private static final String APPEAR_NEWS = NewsAppearEvent.class.getSimpleName();
	private static final String DELETE_NEWS = NewsDeleteEvent.class.getSimpleName();
	private static final String UPDATE_NEWS = NewsUpdateEvent.class.getSimpleName();

	public Editorial(String name, Dispatcher dispatcher, Queue<Event> events) {
		this.name = name;
		this.dispatcher = dispatcher;
		this.events = events;
		news = new HashSet<News>();
	}

	public String getName() {
		return name;
	}

	public void subscribe(Class<?> eventType, Filter filter, Listener listener) {
		dispatcher.subscribeListener(eventType, filter, listener);
	}

	public void unsubscribe(Class<?> eventType, Filter filter, Listener listener) {
		dispatcher.subscribeListener(eventType, filter, listener);
	}

	public void work() {
		while (!events.isEmpty()) {
			Event event = events.remove();
			processNews(event.getType(), event.getNews());
			dispatcher.publish(event);
		}
	}

	private void processNews(String eventType, News news) {
		if (eventType.equals(APPEAR_NEWS) || eventType.equals(UPDATE_NEWS)) {
			this.news.add(news);
		} else if (eventType.equals(DELETE_NEWS)) {
			this.news.remove(news);
		}
	}
}
