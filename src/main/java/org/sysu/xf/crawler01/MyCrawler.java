package org.sysu.xf.crawler01;

import java.util.Set;

public class MyCrawler {
	
	private static final String[] URL_SEEDS = new String[]{"http://www.wandoujia.com/search?key=%E6%8D%95%E9%B1%BC%E8%BE%BE%E4%BA%BA"};
	
	private static final int MAX_VISITED_SIZE = 100;
	
	/*
	 * 使用种子初始化URL队列
	 */
	private void initCrawlerWithSeeds(String[] seeds)
	{
		for(int i=0;i<seeds.length;i++)
			LinkQueue.addUnVisitedUrl(seeds[i]);
	}
	
	public void crawling(String[] seeds)
	{
		LinkFilter filter = new LinkFilter() {
			@Override
			public boolean accept(String url) {
				if(url.startsWith("http://www.wandoujia.com")){
					return true;
				}else{
					return false;
				}
			}
		};
		initCrawlerWithSeeds(seeds);
		while(!LinkQueue.isUnVisitedUrlsEmpty() 
				&& LinkQueue.getVisitedNum() <= MAX_VISITED_SIZE)
		{
			try
			{
				String url = LinkQueue.unVisitedUrlDeQueue();
				System.out.println("visiting: "+url);
				if(url == null)
					continue;
				DownLoadFile downLoader = new DownLoadFile();
				String content = downLoader.downloadFile(url);
				LinkQueue.addVisitedUrl(url);
				if(content != null){
					Set<String> links = HtmlParserTool.extractLinks(content, filter);
					for(String link : links)
					{
						LinkQueue.addUnVisitedUrl(link);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
	}
	
	public static void main(String[] args)
	{
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(URL_SEEDS);
	}
}
