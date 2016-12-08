package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public abstract class Event {

	private News news;
	private final String type = this.getClass().getName();
	abstract public String getIntro();
	
	public String getType()
	{
		return this.type;
	}
	public Event(News news)
	{
		this.news = news;
	}
	public News getNews()
	{
		return this.news;
	}
}
