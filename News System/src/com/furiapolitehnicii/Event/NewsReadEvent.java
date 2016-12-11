package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public class NewsReadEvent extends Event {
	private int currentViews;
	public NewsReadEvent(News news) {
		super(news);
		currentViews = news.getNumberOfViews();
	}

	@Override
	public String getIntro() {
		return "this news has " + currentViews + " readers!";
	}
}
