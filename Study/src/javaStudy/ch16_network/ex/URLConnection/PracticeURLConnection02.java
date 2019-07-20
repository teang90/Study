package javaStudy.ch16_network.ex.URLConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PracticeURLConnection02 {

	public static void main(String[] args) throws Exception {
		System.out.println("main method start");
		URL url = new URL("https://www.naver.com/");
		URLConnection urlConn = url.openConnection();
		String sf = readContent(url);
		boolean res = makeContent(sf);
		System.out.println("main method end");
	}

//			URL에 연결하여 그 내용을 읽어오는 예제이다. 
//			만일 URL이 유효하지 않으면 Malformed - URLException이 발생
//			openStream()을 호출하여 URL의 InputStream을 얻은 이후로는 
//			파일로 부터 데이터를 읽는 것과 다르지 않다.
//			openStream()은 openConnection()을 호출해서 URLConnection을 얻은 다음
//			여기에 다시 getInputStream()을 호출한 것과 같다.
//			즉 URL에 연결해서 InputStream을 얻어 온다.
	 
	public static String readContent(URL url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		System.out.println("start reading line");
		StringBuffer sbf = new StringBuffer();
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				sbf.append(line+"\n");
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			br.close();
		}
		System.out.println("end reading line");
		return sbf.toString();
	}
		
	
	public static boolean makeContent(String str) throws Exception {
		boolean res = false;
		File file = new File("C:/javaStudyEX/res01.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(str);
		bw.flush();
		bw.close();
		res = true;
		return res;
	}
	
}
