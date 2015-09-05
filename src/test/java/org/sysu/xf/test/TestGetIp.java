package org.sysu.xf.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class TestGetIp {
	public static void main(String[] args) throws Exception
	{
		String hostname;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nHost name: ");
		hostname = input.readLine();
		try{
			InetAddress ipaddress = InetAddress.getByName(hostname);
			System.out.println("Ip address: "+ipaddress.getHostAddress());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
