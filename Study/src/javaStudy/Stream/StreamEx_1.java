package javaStudy.Stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StreamEx_1 {

	// 반복에서 스트림으로...
	// 책에 긴 단어가 얼마나 나오는지 모두 세보자. 
	
	// 1. 긴 단어를 리스트에 담자.
	public void countWord() {
		try {
			// 파일을 문자열로 읽어온다.
			String contents = new String(Files.readAllBytes(Paths.get("example.txt")), StandardCharsets.UTF_8);
			
			// 단어로 분리(비문자를 문자간의 구분자로 사용)
			List<String> words = Arrays.asList(contents.split("\\PL+"));
			
			
			// 단어 길이 비교 및 긴 단어 카운트
			// 반복
			int countInt = 0;
			for (String w : words) if (w.length() > 12) countInt ++;
			
			// 스트림
			long countLong = words.stream().filter(w -> w.length()>12).count();
			
			/*
			 * 스트림과 컬렉션과의 차이
			 * 1. 스트림은 요소를 저장하지 않는다. 요소는 스트림을 지원하는 컬렉션에 저장되거나 필요할 때 생성됨
			 * 2. 스트림 연산은 원본을 변경하지 않는다. ex-filter 메소드는 
			 *    새로운 스트림에서 요소를 지우기보다 해당 요소가 없는 새로운 스트림을 돌려준다.
			 *    
			 * 3. 스트림 연산은 가능한 지연시킨다. 즉, 해당 연산 결과가 필요하기 전까지는 실행되지 않는다. 
			 *  
			 *  */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
	}
			
			
			
	
	
	
	
}
