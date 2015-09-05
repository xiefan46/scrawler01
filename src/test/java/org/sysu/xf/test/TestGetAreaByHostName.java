package org.sysu.xf.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import org.sysu.xf.ip.IPLocation;
import org.sysu.xf.ip.IPSeeker;

/*
 * 根据域名得到ip地址和位置信息
 */
public class TestGetAreaByHostName {
	public static void main(String[] args) throws Exception
	{
		String hostname;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nHost name: ");
		hostname = input.readLine();
		try{
			InetAddress ipaddress = InetAddress.getByName(hostname);
			System.out.println("Ip address: "+ipaddress.getHostAddress());
			IPSeeker seeker = new IPSeeker("./ip数据库/qqwry.dat", "./store");
			IPLocation location = seeker.getIPLocation(ipaddress.getHostAddress());
			System.out.println(location.getArea()+":"+location.getCountry());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
