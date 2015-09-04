package org.sysu.xf.crawler02;

import java.util.BitSet;

public class SimpleBloomFilter implements VisitedFrontier{

	private static final int DEFAULT_SIZE = 2 << 24;
	
	private static final int[] seeds = new int[] {7,11,13,31,37,61};
	
	private BitSet bits = new BitSet(DEFAULT_SIZE); //二进制向量
	
	private SimpleHash[] func = new SimpleHash[seeds.length]; //随机映射函数
	
	public SimpleBloomFilter() {
		for(int i=0;i<seeds.length;i++){
			func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
		}
	}
	
	@Override
	public void add(CrawlUrl value) {
		if(value != null){
			add(value.getOriUrl());
		}
	}

	@Override
	public void add(String value) {
		//被随机映射函数映射到的bit设置为1
		for(SimpleHash f : func){
			bits.set(f.hash(value), true);
		}
	}

	@Override
	public boolean contains(CrawlUrl value) {
		return contains(value.getOriUrl());
	}

	@Override
	public boolean contains(String value) {
		if(value == null) {
			return false;
		}
		boolean ret = true;
		for(SimpleHash f : func){
			ret = ret && bits.get(f.hash(value)); //其中一个函数判断为false即为false
		}
		return ret;
	}
	
	public static class SimpleHash
	{
		private int cap; //二进制向量的长度
		
		private int seed;
		
		public SimpleHash(int cap,int seed){
			this.cap = cap;
			this.seed = seed;
		}
		
		public int hash(String value){
			int result = 0;
			int len = value.length();
			for(int i=0;i<len;i++){
				result = seed * result + value.charAt(i);
			}
			return (cap - 1) & result;
		}
	}
	
	public static void main(String[] args){
		String value = "www.qq.com";
		SimpleBloomFilter filter = new SimpleBloomFilter();
		System.out.println(filter.contains(value));
		filter.add(value);
		System.out.println(filter.contains(value));
	}
}
