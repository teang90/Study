package javaStudy.ch16_network.ex;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class PracticeURLConnection03 {

	public static void main(String[] args) {
		URL url = null;
		InputStream is = null;
		FileOutputStream out = null;
		String addr = "https://www.naver.com/";
		int ch = 0;

		try {
			url = new URL(addr);
			is = url.openStream();
			out = new FileOutputStream("javajungsuk3_src.zip");
			
			while ((ch = is.read()) != -1) {
				out.write(ch);
			}
			is.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
