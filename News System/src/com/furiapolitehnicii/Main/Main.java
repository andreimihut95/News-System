package com.furiapolitehnicii.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Dispatcher.NewsDispatcher;
import com.furiapolitehnicii.Editorial.Editorial;
import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
import com.furiapolitehnicii.Event.NewsDeleteEvent;
import com.furiapolitehnicii.Event.NewsReadEvent;
import com.furiapolitehnicii.Event.NewsUpdateEvent;
import com.furiapolitehnicii.Filter.NewsFilter;
import com.furiapolitehnicii.Listener.Editor;
import com.furiapolitehnicii.Listener.Reader;
import com.furiapolitehnicii.News.News;

public class Main {
	public static void main(String[] args) {
		Queue<Event> events = new LinkedList<Event>();
		Dispatcher antena1Dispatcher = new NewsDispatcher();
		Editorial antena1 = new Editorial("Antena1", antena1Dispatcher, events);

		Reader AndreiMihut = new Reader("Mihut Andrei", events);
		Reader CosminMemetea = new Reader("Memetea Cosmin", events);
		Reader IonPanfilii = new Reader("Panfilii Ion", events);
		Reader DaianaBalaci = new Reader("Balaci Daiana", events);
		Reader AndreiMorariu = new Reader("Morariu Andrei", events);

		Editor MihaiGadea = new Editor("Mihai Gadea", events);
		Editor MirceaBadea = new Editor("Mircea Badea", events);

		News trumpNews = new News("Trump won the elections!", "Mihai Gadea", "Politics", new ArrayList<String>(),
				"America",
				"Donald Trump won the key swing states of Florida, North Carolina and Ohio early this morning, giving him a clear path to the White House.");
		News sportNews = new News("Lionel Messi and father get 21-month prison term!", "Mircea Badea", "Sports",
				new ArrayList<String>(), "Vadim Tudor",
				"Lionel Messi and his father have been fined 3.6m euros and given a 21-month prison sentence for a Barcelona tax fraud case.");
		News computerNews = new News("ASUS and ROG Launch New Gaming Desktops!", "Vadim Tudor", "Computer",
				Arrays.asList("Keyboard", "Mouse", "Headphones"), "Gigi Becali",
				"Gaming is in our blood at ASUS. It's long been one of the most entertaining and strenuous things you can do with a PC, and its demands have guided our pursuit of better performance and groundbreaking features since the company's early days.");
		NewsFilter sportFilter = new NewsFilter();
		sportFilter.setDomain("Sports");
		NewsFilter politicsFilter = new NewsFilter();
		politicsFilter.setDomain("Politics");
		NewsFilter subdomainsFilter = new NewsFilter();
		List<String> subdomains = Arrays.asList("Keyboard", "Mouse");
		subdomainsFilter.setSubdomains(subdomains);

		antena1.subscribe(NewsAppearEvent.class, sportFilter, AndreiMihut);
		antena1.subscribe(NewsAppearEvent.class, politicsFilter, IonPanfilii);
		antena1.subscribe(NewsUpdateEvent.class, CosminMemetea);
		antena1.subscribe(NewsReadEvent.class, MihaiGadea);
		antena1.subscribe(NewsReadEvent.class, politicsFilter, MirceaBadea);
		antena1.subscribe(NewsDeleteEvent.class, CosminMemetea);
		antena1.subscribe(NewsAppearEvent.class, DaianaBalaci);
		antena1.subscribe(NewsAppearEvent.class, subdomainsFilter, AndreiMorariu);

		 MirceaBadea.onNewsAppearEvent(computerNews);
		 MihaiGadea.onNewsAppearEvent(trumpNews);
		 MirceaBadea.onNewsAppearEvent(sportNews);
		 MirceaBadea.onNewsDeleteEvent(trumpNews);
		 MihaiGadea.onNewsUpdateEvent(computerNews);
		 antena1.work();


	}

}
