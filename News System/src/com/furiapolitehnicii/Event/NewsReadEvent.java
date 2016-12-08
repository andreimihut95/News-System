package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public class NewsReadEvent extends Event {
	private int numberSubscribers;
	public NewsReadEvent(News news) {
		super(news);
	}

	@Override
	public String getIntro() {
		return "Ai"+ (++numberSubscribers)+ "nou abonat!";
	}
}
