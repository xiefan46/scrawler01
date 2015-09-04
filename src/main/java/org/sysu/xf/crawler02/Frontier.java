package org.sysu.xf.crawler02;


public interface Frontier {
	
	public CrawlUrl getNext() throws Exception;
	
	public boolean putUrl(CrawlUrl url) throws Exception;
}
