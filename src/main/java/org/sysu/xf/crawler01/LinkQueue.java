package org.sysu.xf.crawler01;

import java.util.HashSet;
import java.util.Set;

public class LinkQueue {
	
	//已经访问过的URL集合
	private static Set visitedUrl = new HashSet();
	//待访问的URL集合
	private static URLQueue unVisitedUrl = new URLQueue();
	
	public static URLQueue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	
	public static void addVisitedUrl(String url){
		visitedUrl.add(url);
	}
	
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);
	}
	
	public static String unVisitedUrlDeQueue(){
		return unVisitedUrl.deQueue();
	}
	
	public static void addUnVisitedUrl(String url)
	{
		if(url != null && !url.trim().equals("") && !visitedUrl.contains(url) 
					&& !unVisitedUrl.contains(url))
		{
			unVisitedUrl.enQueue(url);
		}
	}
	
	public static int getVisitedNum(){
		return visitedUrl.size();
	}
	
	public static boolean isUnVisitedUrlsEmpty(){
		return unVisitedUrl.isEmpty();
	}
}
