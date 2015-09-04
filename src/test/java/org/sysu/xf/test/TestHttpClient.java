package org.sysu.xf.test;

import java.io.IOException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class TestHttpClient {
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
		if(entity != null){
			System.out.println("response content length:  "+entity.getContentLength());
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		}
		response.close();
		client.close();
		
	}
}
