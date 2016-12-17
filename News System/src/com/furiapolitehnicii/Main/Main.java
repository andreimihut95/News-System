package com.furiapolitehnicii.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.furiapolitehnicii.Resource.ConcurentQueue;

public class Main {
	public static void main(String[] args) {
		ConcurentQueue<Event> events = new ConcurentQueue<Event>();
		Dispatcher antena1Dispatcher = new NewsDispatcher();
		Editorial antena1 = new Editorial("Antena1", antena1Dispatcher, events);
		Thread antena1Thread = new Thread(antena1);
		antena1Thread.start();

		News trumpNews = new News("Trump won the elections!", "Mihai Gadea",
				"Politics", new ArrayList<String>(), "America",
				"Donald Trump won the key swing states of Florida, North Carolina and Ohio early this morning, giving him a clear path to the White House.");
		News sportNews = new News(
				"Lionel Messi and father get 21-month prison term!",
				"Mircea Badea", "Sports", new ArrayList<String>(),
				"Vadim Tudor",
				"Lionel Messi and his father have been fined 3.6m euros and given a 21-month prison sentence for a Barcelona tax fraud case.");
		News computerNews = new News("ASUS and ROG Launch New Gaming Desktops!",
				"Vadim Tudor", "Computer",
				Arrays.asList("Keyboard", "Mouse", "Headphones"), "Gigi Becali",
				"Gaming is in our blood at ASUS. It's long been one of the most entertaining and strenuous things you can do with a PC, and its demands have guided our pursuit of better performance and groundbreaking features since the company's early days.");
		NewsFilter politicsFilter = new NewsFilter();
		politicsFilter.setDomain("Politics");
		NewsFilter subdomainsFilter = new NewsFilter();
		NewsFilter sportFilter = new NewsFilter();
		sportFilter.setDomain("Sports");
		List<String> subdomains = Arrays.asList("Keyboard", "Mouse");
		subdomainsFilter.setSubdomains(subdomains);

		Reader AndreiMihut = new Reader("Andrei Mihut", events) {
			@Override
			public void run() {

				antena1.subscribe(NewsAppearEvent.class, sportFilter, this);
			}
		};

		Reader CosminMemetea = new Reader("Memetea Cosmin", events) {

			@Override
			public void run() {
				antena1.subscribe(NewsUpdateEvent.class, this);
				antena1.subscribe(NewsDeleteEvent.class, this);
			}
		};

		Reader IonPanfilii = new Reader("Panfilii Ion", events) {

			@Override
			public void run() {
				antena1.subscribe(NewsAppearEvent.class, politicsFilter, this);
			}
		};

		Reader DaianaBalaci = new Reader("Balaci Daiana", events) {

			@Override
			public void run() {
				antena1.subscribe(NewsAppearEvent.class, this);
			}
		};
		Reader AndreiMorariu = new Reader("Morariu Andrei", events) {

			@Override
			public void run() {
				antena1.subscribe(NewsAppearEvent.class, subdomainsFilter,
						this);
			}
		};
		Thread AndreiMihutThread = new Thread(AndreiMihut);
		AndreiMihutThread.start();
		Thread CosminMemeteaThread = new Thread(CosminMemetea);
		CosminMemeteaThread.start();
		Thread IonPanfiliiThread = new Thread(IonPanfilii);
		IonPanfiliiThread.start();
		Thread DaianaBalaciThread = new Thread(DaianaBalaci);
		DaianaBalaciThread.start();
		Thread AndreiMorariuThread = new Thread(AndreiMorariu);
		AndreiMorariuThread.start();

		Editor MirceaBadea = new Editor("Mircea Badea", events) {

			@Override
			public void run() {
				antena1.subscribe(NewsReadEvent.class, politicsFilter, this);
				this.onNewsAppearEvent(computerNews);
				this.onNewsAppearEvent(sportNews);
				this.onNewsDeleteEvent(trumpNews);
			}
		};

		Editor MihaiGadea = new Editor("Mihai Gadea", events) {

			@Override
			public void run() {
				antena1.subscribe(NewsReadEvent.class, this);
				this.onNewsAppearEvent(trumpNews);
				this.onNewsUpdateEvent(computerNews);
			}
		};
		Thread MirceaBadeaThread = new Thread(MirceaBadea);
		MirceaBadeaThread.start();
		Thread MihaiGadeaThread = new Thread(MihaiGadea);
		MihaiGadeaThread.start();
	}
}
