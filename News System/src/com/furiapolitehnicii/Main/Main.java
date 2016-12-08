package com.furiapolitehnicii.Main;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Dispatcher.NewsDispatcher;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.Event.NewsReadEvent;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Filter.NewsFilter;
import com.furiapolitehnicii.Listener.Editor;
import com.furiapolitehnicii.Listener.Listener;
import com.furiapolitehnicii.Listener.Reader;
import com.furiapolitehnicii.News.News;

public class Main {
	public static void main(String[] args)
	{
		Dispatcher newsDispatcher = new NewsDispatcher();
		
		Listener Mihut = new Reader("Mihut Andrei");
		Listener Memetea = new Reader("Memetea Cosmin");
		Listener Balaci = new Reader("Balaci Daiana");
		Editor Badea = new Editor("Badea");
		
		News news1 = new News("Trump a castigat!", "Cartarescu", "Politica", null, "Obama" , "Trump a castigat alegerile din iarna in Rusia!");
		NewsFilter domainPoliticsFilter = new NewsFilter();
		domainPoliticsFilter.setDomain("Politica");
		newsDispatcher.subscribeListener(NewsAppearEvent.class.getName(), domainPoliticsFilter, Mihut);
		newsDispatcher.subscribeListener(NewsReadEvent.class.getName(), null, Badea);
		
		Badea.onNewsAppearEvent(news1);
		
	}

}
