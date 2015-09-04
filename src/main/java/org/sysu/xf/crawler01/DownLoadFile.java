package org.sysu.xf.crawler01;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlparser.http.HttpHeader;

public class DownLoadFile {
	
	private static final int TIME_OUT = 5000;
	
	/*
	 * 根据URL和网页类型保存网页的文件名
	 */
	public String getFileNameByUrl(String url,String contentType)
	{
		url = url.substring(7);
		if(contentType.indexOf("html") != -1)
		{
			String filePath = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return filePath;
		}
		else
		{
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "." + contentType.substring(
					contentType.lastIndexOf("/") + 1);
		}
	}
	
	private void saveToLocal(byte[] data,String filePath)
	{
		try{
			System.out.println("filePath: "+filePath);
			DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath));
			out.write(data);
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * 将html文件下载下来，并且将内容返回回去
	 */
	public String downloadFile(String url)
	{	
		
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		String filePath = null;
		String content = null;
		try{
			client = HttpClients.createDefault();
			HttpGet request = new HttpGet(url);	
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(TIME_OUT).setConnectionRequestTimeout(TIME_OUT)
					.setConnectTimeout(TIME_OUT).build();
			request.setConfig(requestConfig);
			request.setHeader("User-Agent", 
						"Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");    
			response = client.execute(request);
			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
			{
				System.out.println("Method failed: "+response.getStatusLine());
				filePath = null;
			}
			else
			{
				String contentType = ContentType.getOrDefault(response.getEntity()).getMimeType();
				filePath = "./temp/" + getFileNameByUrl(url, contentType);
				content = EntityUtils.toString(response.getEntity(),"UTF-8");
				saveToLocal(content.getBytes(), filePath);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(client != null) client.close();
				if(response != null) response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
}
