package com.furiapolitehnicii.Resource;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConcurentSet<T> implements Iterable<T> {
	private Set<T> elements;
	private static final int MAX_NUMBER_OF_ELEMENTS = 1000000;

	public ConcurentSet() {
		elements = new HashSet<T>();
	}

	public synchronized void add(T element) {
		if (elements.size() == MAX_NUMBER_OF_ELEMENTS) {
			try {
				System.out.println(
						"The ConcurentQueue is full. Waiting another thread to remove an element.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		elements.add(element);
		notifyAll();
	}

	public synchronized void remove(T element) {
		if (elements.isEmpty()) {
			try {
				System.out.println(
						"The ConcurentQueue is empty. Waiting for a thread to add an element.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		elements.remove(element);
		notifyAll();
	}

	@Override
	public synchronized Iterator<T> iterator() {
		return elements.iterator();
	}
}
