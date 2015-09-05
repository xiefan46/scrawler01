package org.sysu.xf.test;

import org.sysu.xf.crawler01.DownLoadFile;

/**
 * @author aris
 * 测试主题爬虫
 * 爬取携程中的内容
 */
public class TestThemeCrawler {
	public static void main(String[] args)
	{
		String url = "http://flights.ctrip.com";
		DownLoadFile downloader = new DownLoadFile();
		String content = downloader.downloadFile(url);
		System.out.println(content);
	}
	
	
}
