package javaStudy.ch16_network.ex.URLConnection;

import java.net.URL;

public class PracticeURL01 {
	
	public static void main(String[] args) throws java.lang.Exception {
		String paramUrl = "http://www.codechobo.com:80/sample/";
		String file = "hello.html?referer=codechobo#index1";
		URL url = new URL(paramUrl+file);
		printMethod(url);
	}
	
	public static void printMethod(URL url) throws java.lang.Exception {
		System.out.println("--------- URL Class 메소드 ---------");	//  결과
		System.out.println("getAuthority " + url.getAuthority());	//  getAuthority www.codechobo.com:80
		System.out.println("getContent " + url.getContent());		//  sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@c14		
		System.out.println("getDefaultPort "+url.getDefaultPort());	//  80
		System.out.println("getPort "+url.getPort());				//  80
		System.out.println("getFile "+url.getFile());				//  /sample/hello.html?referer=codechobo
		System.out.println("getHost "+url.getHost());				//  www.codechobo.com
		System.out.println("getPath "+url.getPath());				//  /sample/hello.html
		System.out.println("getProtocol "+url.getProtocol());		//  http
		System.out.println("getQuery "+url.getQuery());				//  referer=codechobo
		System.out.println("getRef "+url.getRef());					//  index1
		System.out.println("getUserInfo "+url.getUserInfo());		//  null
		System.out.println("toExternalForm "+url.toExternalForm());	//  paramUrl+file 문자열 그대
		System.out.println("toString "+url.toString());				//  toString http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1
		System.out.println("toURI "+url.toURI());					//  toURL http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1	
		System.out.println();
	}
	
	/*
	 * ex)
	 * 
	 */
	
}
