package org.sysu.xf.crawler01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class URLQueue {
	private Queue<String> queue = new ConcurrentLinkedQueue<String>();
	
	public void enQueue(String url)
	{
		queue.add(url);
	}
	
	public String deQueue()
	{
		return queue.poll();
	}
	
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}
	
	public boolean contains(String url)
	{
		return queue.contains(url);
	}
	
}
