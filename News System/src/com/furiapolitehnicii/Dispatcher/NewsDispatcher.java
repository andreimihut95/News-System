package com.furiapolitehnicii.Dispatcher;

import java.util.HashSet;
import java.util.Set;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;

public class NewsDispatcher implements Dispatcher {
	private class Subscription {
		private Listener listener;
		private Filter filter;
		private Class<? extends Event> typeEvent;

		public Subscription(Listener listener, Filter filter, Class<? extends Event> typeEvent) {
			this.listener = listener;
			this.filter = filter;
			this.typeEvent = typeEvent;
		}

		@Override
		public String toString() {
			return "Subscription [listener=" + listener + ", filter=" + filter + ", typeEvent=" + typeEvent + "]";
		}

		public Listener getListener() {
			return listener;
		}

		public Filter getFilter() {
			return filter;
		}

		public Class<?> getEvent() {
			return typeEvent;
		}
	}

	private final Set<Subscription> subscriptions = new HashSet<Subscription>();

	@Override
	public void subscribeListener(Class<? extends Event> eventType, Filter filter, Listener listener) {
		subscriptions.add(new Subscription(listener, filter, eventType));
	}

	@Override
	public void unsubscribeListener(Class<? extends Event> eventType, Filter filter, Listener listener) {
		subscriptions.remove(new Subscription(listener, filter, eventType));
	}

	@Override
	public void publish(Event event) {
		for (Subscription subscription : subscriptions) {
			String thisEventType = subscription.getEvent().getSimpleName();
			Listener thisListener = subscription.getListener();
			Filter thisFilter = subscription.getFilter();

			if (thisEventType.equals(event.getType()) && (thisFilter == null || thisFilter.verify(event))) {
				thisListener.dispatch(event);
			}
		}
	}
}
