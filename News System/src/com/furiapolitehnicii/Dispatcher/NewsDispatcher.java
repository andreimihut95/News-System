package com.furiapolitehnicii.Dispatcher;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.furiapolitehnicii.Event.Event;
import com.furiapolitehnicii.Filter.Filter;
import com.furiapolitehnicii.Listener.Listener;

public class NewsDispatcher implements Dispatcher {
	private class Subscription {
		private Listener listener;
		private Filter filter;
		private Class<? extends Event> typeEvent;

		public Subscription(Listener listener, Filter filter,
				Class<? extends Event> typeEvent) {
			this.listener = listener;
			this.filter = filter;
			this.typeEvent = typeEvent;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((filter == null) ? 0 : filter.hashCode());
			result = prime * result
					+ ((listener == null) ? 0 : listener.hashCode());
			result = prime * result
					+ ((typeEvent == null) ? 0 : typeEvent.hashCode());
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
			if (typeEvent == null) {
				if (other.typeEvent != null)
					return false;
			} else if (!typeEvent.equals(other.typeEvent))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Subscription [listener=" + listener + ", filter=" + filter
					+ ", typeEvent=" + typeEvent + "]";
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

		private NewsDispatcher getOuterType() {
			return NewsDispatcher.this;
		}
	}

	private final BlockingQueue<Subscription> subscriptions = new LinkedBlockingQueue<Subscription>();

	@Override
	public void subscribeListener(Class<? extends Event> eventType,
			Filter filter, Listener listener) {
		subscriptions.add(new Subscription(listener, filter, eventType));
	}

	@Override
	public void unsubscribeListener(Class<? extends Event> eventType,
			Filter filter, Listener listener) {
		subscriptions.remove(new Subscription(listener, filter, eventType));
	}

	@Override
	public void dispatch(Event event) {
		for (Subscription subscription : subscriptions) {
			String thisEventType = subscription.getEvent().getSimpleName();
			Listener thisListener = subscription.getListener();
			Filter thisFilter = subscription.getFilter();

			if (thisEventType.equals(event.getType())
					&& (thisFilter == null || thisFilter.verify(event))) {
				thisListener.dispatch(event);
			}
		}
	}
}
