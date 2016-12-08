package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public class NewsReadEvent extends Event {
	public NewsReadEvent(News news) {
		super(news);
	}

	@Override
	public String getIntro() {
		return "This news has "+ this.getNews().getNumberOfViews()+ " number of readers!";
	}
}
