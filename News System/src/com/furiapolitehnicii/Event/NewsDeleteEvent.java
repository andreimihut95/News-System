package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public class NewsDeleteEvent extends Event {

	public NewsDeleteEvent(News news) {
		super(news);
	}

	@Override
	public String getIntro() {
		return "this news was removed from the editorial!";
	}

}
