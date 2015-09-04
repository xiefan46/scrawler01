package org.sysu.xf.crawler02;

import java.io.Serializable;
import java.util.Date;

import com.sleepycat.je.utilint.Timestamp;

public class CrawlUrl implements Serializable{
	
	public CrawlUrl(){
		
	}
	
	private String oriUrl;  //原始的URL值，主机部分是域名
	
	private String url;  //URL值，主机部分是ip
	
	private int urlNo;
	
	private int statusCode;  //URL返回的结果码
	
	private int hitNum; //此URL被引用的次数
	
	private String charSet; //编码方式
	
	private String abstractText;  //文章摘要
	
	private String author;   //作者
	
	private int weight;  //文章权重
	
	private String description; //文章描述
	
	private int fileSize;  //文章大小
	
	private Timestamp lastUpdateTime; //最后修改时间
	
	private Date timeToLive;  //最后过期时间
	
	private String title;  //文章名称
	
	private String type;  //文章类型
	
	private String[] rulRefrences; //引用的链接
	
	private int layer;  //爬取的层次(当前深度)

	public String getOriUrl() {
		return oriUrl;
	}

	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUrlNo() {
		return urlNo;
	}

	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getHitNum() {
		return hitNum;
	}

	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getRulRefrences() {
		return rulRefrences;
	}

	public void setRulRefrences(String[] rulRefrences) {
		this.rulRefrences = rulRefrences;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	
}
