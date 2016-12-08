package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public class NewsAppearEvent extends Event {

	public NewsAppearEvent(News news) {
		super(news);
	}

	@Override
	public String getIntro() {
		return "Senzational! A aparut o noua stire!";
	}

}
