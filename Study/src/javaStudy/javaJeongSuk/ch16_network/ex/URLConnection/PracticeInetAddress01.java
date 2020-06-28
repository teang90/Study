package javaStudy.javaJeongSuk.ch16_network.ex.URLConnection;

import java.net.InetAddress;
import java.util.Arrays;

public class PracticeInetAddress01 {
	
	public static void main(String[] args) {
		InetAddress ip 		= null;
		InetAddress[] ipArr	= null;
		String goalUrl = "";
		
		try {
			goalUrl = "www.naver.com";
//			goalUrl = "http://teang90.cafe24.com/";
			System.out.println("------ " + goalUrl + " ------");

			
			// getByName(domain) - domain명을 이용해서 IP주소를 얻는다 	
			ip = InetAddress.getByName(goalUrl);
			
				// ip.toString() 과 동일
				System.out.println("ip : "+ip); 

				// getHostName() - 호스트의 이름을 반환
				System.out.println("getHostName 	: "+ip.getHostName()); 

				// getHostAddress() - 호스트의 IP주소 반환
				System.out.println("getHostAddress 	: "+ip.getHostAddress()); 
				
				// domain/ipAddress 형식으로 뜸
				System.out.println("toString 		: "+ip.toString());	
			
			//getAddress() - IP주소를 byte[]로 저장
			byte[] ipAddr = ip.getAddress();	
				System.out.println("getAddress : "+Arrays.toString(ipAddr));	
			
			String res = "";
			for (int i = 0; i < ipAddr.length; i++) {
				res += (ipAddr[i] < 0)? ipAddr[i] + 256 : ipAddr[i]; // ip를 byte[] 받아서 -인 경우에 256 더함, 256의 의미는? 
				res += ".";
			}
				System.out.println("getAddress + 256 : "+res); 
				System.out.println();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			ip = InetAddress.getLocalHost(); // 지역 호스트의 IP 주소 반환
				System.out.println("------ LocalHost ------");

				// 해당 호스트의 이름 반환
				System.out.println("getHostName 	: " + ip.getHostName()); 
				
				// 해당 호스트의 IP반환
				System.out.println("getHostAddress 	: " + ip.getHostAddress()); 
				System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// domain에 지정된 모든 IP주소(InetAddress[]) 가져옴
			ipArr = InetAddress.getAllByName(goalUrl); 
			
			for (int i = 0; i < ipArr.length; i++) {
				System.out.println("ipArr[" + i + "]"+ipArr[i]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
