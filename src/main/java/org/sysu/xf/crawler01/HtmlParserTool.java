package org.sysu.xf.crawler01;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;





public class HtmlParserTool {
	/*
	 * 提取网页上的所有链接
	 */
	public static Set<String> extractLinks(String htmlPage,LinkFilter filter)
	{
		Set<String> links = new HashSet<String>();
		try
		{
			Parser parser = new Parser(htmlPage);
			parser.setEncoding("utf-8");
			NodeFilter frameFilter = new NodeFilter(){
				@Override
				public boolean accept(Node node) {
					if(node.getText().startsWith("frame src=")){
						return true;
					}else{
						return false;
					}
				}
				
			};
			/*
			 * 将<frame>标签或者<a>标签过滤出来
			 */
			OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class),frameFilter);
			NodeList list = parser.extractAllNodesThatMatch(linkFilter);
			for(int i = 0; i < list.size(); i++)
			{
				Node tag = list.elementAt(i);
				if(tag instanceof LinkTag) //<a>标签
				{
					LinkTag link = (LinkTag)tag;
					String linkUrl = link.getLink();
					if(filter.accept(linkUrl))
						links.add(linkUrl);
				}
				else //frarm标签
				{
					String frame = tag.getText();
					int start = frame.indexOf("src=");
					frame = frame.substring(start);
					int end = frame.indexOf(" ");
					if(end == -1)
						end = frame.indexOf(">");
					String frameUrl = frame.substring(5,end - 1);
					if(filter.accept(frameUrl))
						links.add(frameUrl);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return links;
	}
}
