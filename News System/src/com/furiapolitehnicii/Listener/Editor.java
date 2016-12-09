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
		if(isNewsDeleted(news))
			return;
		Event newsAppearEvent = new NewsAppearEvent(news);
		events.add(newsAppearEvent);
	}

	public void onNewsDeleteEvent(News news) {
		if(isNewsDeleted(news))
			return;
		news.delete();
		Event newsAppearEvent = new NewsDeleteEvent(news);
		events.add(newsAppearEvent);

	}

	public void onNewsUpdateEvent(News news) {
		if(isNewsDeleted(news))
			return;
		Event newsAppearEvent = new NewsUpdateEvent(news);
		events.add(newsAppearEvent);
	}
	
	private static boolean isNewsDeleted(News news)
	{
		if (news.isDeleted()) {
			System.out.println("The news was erased from the editorial. Cannot publish/update/delete a deleted news" + System.lineSeparator() + news.getTitle());
			return true;
		}	
		return false;
	}

}
