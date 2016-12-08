package com.furiapolitehnicii.Event;

import com.furiapolitehnicii.News.News;

public class NewsUpdateEvent extends Event {

	public NewsUpdateEvent(News news) {
		super(news);
	}

	@Override
	public String getIntro() {
		return "this news has been updated!";
	}

}
