package com.furiapolitehnicii.Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.furiapolitehnicii.Dispatcher.Dispatcher;
import com.furiapolitehnicii.Dispatcher.NewsDispatcher;
import com.furiapolitehnicii.Editorial.Editorial;
import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Event.NewsAppearEvent;
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

		Editor MihaiGadea = new Editor("Mihai Gadea", events);
		Editor MirceaBadea = new Editor("Mircea Badea", events);

		News trumpNews = new News("Trump won the elections!", "Mihai Gadea", "Politics", new ArrayList<String>(),
				"America",
				"Donald Trump won the key swing states of Florida, North Carolina and Ohio early this morning, giving him a clear path to the White House.");
		News sportNews = new News("Lionel Messi and father get 21-month prison term!", "Mircea Badea", "Sports",
				new ArrayList<String>(), "Vadim Tudor",
				"Lionel Messi and his father have been fined 3.6m euros and given a 21-month prison sentence for a Barcelona tax fraud case.");

		NewsFilter sportFilter = new NewsFilter();
		sportFilter.setDomain("Sports");

		NewsFilter politicsFilter = new NewsFilter();
		politicsFilter.setDomain("Politics");

		antena1.subscribe(NewsAppearEvent.class, sportFilter, AndreiMihut);
		antena1.subscribe(NewsAppearEvent.class, politicsFilter, IonPanfilii);
		antena1.subscribe(NewsUpdateEvent.class, null, CosminMemetea);
		antena1.subscribe(NewsReadEvent.class, null, MihaiGadea);

		MihaiGadea.onNewsAppearEvent(sportNews);
		MihaiGadea.onNewsAppearEvent(trumpNews);
		trumpNews.setInformationSource("Gigi Becali");
		MirceaBadea.onNewsUpdateEvent(trumpNews);

		antena1.work();
	}

}
