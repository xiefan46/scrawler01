package org.sysu.xf.test;

import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.visitors.HtmlPage;
import org.sysu.xf.crawler01.HtmlParserTool;
import org.sysu.xf.crawler01.LinkFilter;

public class TestHtmlParserTool {
	public static void main(String[] args) throws Exception
	{
		//豌豆荚捕鱼达人的页面
		String url = "http://www.wandoujia.com/search?key=%E6%8D%95%E9%B1%BC%E8%BE%BE%E4%BA%BA";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println("------------------");
		System.out.println(response.getStatusLine());
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
		Set<String> urls = HtmlParserTool.extractLinks(EntityUtils.toString(response.getEntity()), 
																					filter);
		for(String str : urls)
		{
			System.out.println(str);
		}
		response.close();
		client.close();
		
	}
}
