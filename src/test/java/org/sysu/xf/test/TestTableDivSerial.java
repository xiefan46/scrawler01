package org.sysu.xf.test;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TestTableDivSerial {
	
	//新行
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static final int NEWLINE_SIZE = NEWLINE.length();
	
	private String url;
	
	private final String oriEncode = "gb2312,utf-8,gbk,iso-8859-1";
	
	private ArrayList htmlContext = new ArrayList();
	
	private String urlEncode;
	
	private int tableNumber;
	
	private int channelNumber;
	
	private int totalNumber;
	
	//URL正则表达式
	private String domain;
	
	private String urlDomainPattern;
	
	private String urlPattern;
	
	private Pattern pattern;
	
	private Pattern patternPost;
	
	
	
}
