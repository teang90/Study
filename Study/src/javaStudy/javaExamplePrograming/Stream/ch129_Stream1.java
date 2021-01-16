package javaStudy.javaExamplePrograming.Stream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class ch129_Stream1 {

	// 자바 8의 컬렉션 프레임워크에 스트림 인터페이스가 새롭게 추가되었다.
	// List, Map과 같은 컬렉션에 대해서 stream()메소드를 통해서 쉽게 구할 수 있다.

	// 1) Stream 구하기
	public static void main(String[] args) {
		getStreamFromArr().forEach(System.out::println);
		getStreamFromArray().forEach(System.out::println);
		getStreamFromList().forEach(System.out::println);
		getStreamFromSet().forEach(System.out::println);
		getStreamFromMap().forEach(System.out::println);	// Map<String, String>
		getStreamFromMap2().forEach(System.out::println);   // Map<String, Object>
	}
	
	// of()는 고정요소에서 Stream을 구할 수 있다.
	public static Stream<String> getStreamFromArr() {
		Stream<String> s = Stream.of("a1","a2","a3");
		return s;
	}
	public static Stream<String> getStreamFromArray() {
		String[] array = new String[] {"A","B","C"};
		Stream<String> s0 = Stream.of(array);
		return s0;
	}
	
	// stream()은 list, set, map과 같은 컬렉션에서 Stream을 구한다.
	public static Stream<String> getStreamFromList() {
		List<String> list = Arrays.asList("1", "2", "3");
		Stream<String> s1 = list.stream();
		return s1;
	}
	public static Stream<String> getStreamFromSet() {
		Set<String> set = new HashSet<>();
		set.add("a");
		set.add("b");
		set.add("c");
		Stream<String> s2 = set.stream();
		return s2;
	}
	public static Stream<Map.Entry<String, String>> getStreamFromMap() {
		Map<String, String> map = new HashMap<>();
		map.put("ㄱ", "ㄱ");
		map.put("ㄴ", "ㄴ");
		map.put("ㄷ", "ㄷ");
		Stream<Entry<String, String>> s3 = map.entrySet().stream();
		return s3;
	}
	public static Stream<Map.Entry<String, Object>> getStreamFromMap2() {
		Map<String, Object> map = new HashMap<>();
		map.put("ㄱ", "ㄱ");
		map.put("ㄴ", "ㄴ");
		map.put("ㄷ", "ㄷ");
		Stream<Entry<String, Object>> s3 = map.entrySet().stream();
		return s3;
	}
	
}
