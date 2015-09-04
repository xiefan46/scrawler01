package org.sysu.xf.crawler02;

public interface VisitedFrontier {
	
	public void add(CrawlUrl value);
	
	public void add(String value);
	
	public boolean contains(CrawlUrl value);
	
	public boolean contains(String value);
}
