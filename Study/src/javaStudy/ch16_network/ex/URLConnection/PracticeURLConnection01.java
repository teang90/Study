package javaStudy.ch16_network.ex.URLConnection;

import java.net.URL;
import java.net.URLConnection;

public class PracticeURLConnection01 {

	public static void main(String[] args) throws Exception {
		URL url = new URL("https://www.naver.com/");
		URLConnection urlConn = url.openConnection();
		
		System.out.println("conn.toString : "+urlConn);
		System.out.println("getAllowUserInteraction : "+urlConn.getAllowUserInteraction());
		System.out.println("getConnectTimeout() : "+urlConn.getConnectTimeout());
		System.out.println("urlConn.getContent() : "+urlConn.getContent());
		System.out.println("urlConn.getContentEncoding() : "+urlConn.getContentEncoding());
		System.out.println("getContentLength() : "+urlConn.getContentLength());
		System.out.println("getDate() : "+urlConn.getDate());
		System.out.println("getDefaultAllowUserInteraction() : "+urlConn.getDefaultAllowUserInteraction());
		System.out.println("getDefaultUseCaches() : "+urlConn.getDefaultUseCaches());
		System.out.println("getDoInput : "+urlConn.getDoInput());
		System.out.println("getDoOutput : "+urlConn.getDoOutput());
		System.out.println("getExpiration() : "+urlConn.getExpiration());
		System.out.println("url HeaderFeilds : "+urlConn.getHeaderFields());
		System.out.println("getIfModifiedSince : " + urlConn.getIfModifiedSince());
		System.out.println("getLastModified : "+urlConn.getLastModified());
		System.out.println("getReadTimeout() : "+urlConn.getReadTimeout());
		System.out.println("getURL : "+urlConn.getURL());
		System.out.println("getUse caches : "+urlConn.getUseCaches());
		
	}
	
}
