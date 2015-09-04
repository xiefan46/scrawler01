package org.sysu.xf.crawler02;



import java.util.Map.Entry;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;


/*
 * 使用BerkeleyDB实现TODO表
 */
public class BDBFrontier extends AbstractFrontier<String,CrawlUrl> implements Frontier{
	
	private StoredMap<String, CrawlUrl> pendingsUrlsDB = null; //等待爬取的Url队列
	
	public BDBFrontier(String homeDirectory) throws DatabaseException {
		super(homeDirectory);
		EntryBinding<String> keyBinding = new SerialBinding<String>(javaCatalog, String.class);
		EntryBinding<CrawlUrl> valueBinding = new SerialBinding<CrawlUrl>(javaCatalog, CrawlUrl.class);
		pendingsUrlsDB = new StoredMap<String, CrawlUrl>(database, keyBinding, 
					valueBinding, true);
	}
	

	@Override
	public CrawlUrl getNext() throws Exception {
		CrawlUrl result = null;
		if(!pendingsUrlsDB.isEmpty())
		{
			Entry<String,CrawlUrl> entry = pendingsUrlsDB.entrySet().iterator().next();
			result = entry.getValue();
			delete(entry.getKey());
		}
		return result;
	}

	@Override
	public boolean putUrl(CrawlUrl url) throws Exception {
		put(url.getOriUrl(),url);
		return true;
	}

	@Override
	protected void put(String key, CrawlUrl value) {
		pendingsUrlsDB.put(key, value);
	}

	@Override
	protected CrawlUrl get(String key) {
		return pendingsUrlsDB.get(key);
	}


	@Override
	protected CrawlUrl delete(String key) {
		return pendingsUrlsDB.remove(key);
	}
	
	//根据Url计算键值，可以使用各种压缩算法，包括MD5等算法
	private String caculateUrl(String url){
		return url;
	}
	
	public static void main(String[] args)
	{
		try
		{
			BDBFrontier frontier = new BDBFrontier("./store/bdb/");
			CrawlUrl url = new CrawlUrl();
			url.setOriUrl("http://www.163.com");
			frontier.putUrl(url);
			System.out.println(frontier.getNext().getOriUrl());
			frontier.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
