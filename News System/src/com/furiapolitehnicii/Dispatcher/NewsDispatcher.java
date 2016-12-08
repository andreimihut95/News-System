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
		private String typeEvent ;

		public Subscription(Listener listener, Filter filter, String typeEvent) {
			this.listener = listener;
			this.filter = filter;
			this.typeEvent = typeEvent;
		}

		public Listener getListener() {
			return listener;
		}

		public Filter getFilter() {
			return filter;
		}

		public String getEvent() {
			return typeEvent;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((typeEvent == null) ? 0 : typeEvent.hashCode());
			result = prime * result + ((filter == null) ? 0 : filter.hashCode());
			result = prime * result + ((listener == null) ? 0 : listener.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Subscription other = (Subscription) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (typeEvent == null) {
				if (other.typeEvent != null)
					return false;
			} else if (!typeEvent.equals(other.typeEvent))
				return false;
			if (filter == null) {
				if (other.filter != null)
					return false;
			} else if (!filter.equals(other.filter))
				return false;
			if (listener == null) {
				if (other.listener != null)
					return false;
			} else if (!listener.equals(other.listener))
				return false;
			return true;
		}

		private NewsDispatcher getOuterType() {
			return NewsDispatcher.this;
		}

	}

	private final Set<Subscription> subscriptions = new HashSet<Subscription>();

	@Override
	public void subscribeListener(String typeEvent, Filter filter, Listener listener) {
		subscriptions.add(new Subscription(listener, filter, typeEvent));
		listener.attachDispatcher(this);
	}

	@Override
	public void unsubscribeListener(String typeEvent, Filter filter, Listener listener) {
		subscriptions.remove(new Subscription(listener, filter, typeEvent));
		listener.dettachDispatcher(this);
	}

	@Override
	public void publish(Event event) {
		for (Subscription subscription : subscriptions) {
			String thisEvent = subscription.getEvent();
			Listener thisListener = subscription.getListener();
			Filter thisFilter = subscription.getFilter();
			
			if(thisEvent.equals(event) && (thisFilter == null || thisFilter.verify(event)))
			{
				thisListener.dispatch(event);
			}
		}
	}
}
