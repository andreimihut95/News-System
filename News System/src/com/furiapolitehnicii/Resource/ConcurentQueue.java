package com.furiapolitehnicii.Resource;

import java.util.LinkedList;
import java.util.Queue;

public class ConcurentQueue<T> {
	private Queue<T> elements;
	private static final int MAX_NUMBER_OF_ELEMENTS = 1000000;

	public ConcurentQueue() {
		elements = new LinkedList<T>();
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

	public synchronized T remove() {
		if (elements.isEmpty()) {
			try {
				System.out.println(
						"The ConcurentQueue is empty. Waiting for a thread to add an element.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		T element = elements.remove();
		notifyAll();
		return element;
	}
}
