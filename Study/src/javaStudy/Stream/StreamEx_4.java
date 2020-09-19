package javaStudy.Stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class StreamEx_4 {

	public static void main(String[] args) {
		Stream<String[]> strArrStrm = Stream.of(
					new String[] {"abc", "def", "jkl"},
					new String[] {"ABC", "GHI", "JKL"}
				);
		
//		Stream<Stream<String>> _strStrm = strArrStrm.map(Arrays::stream);
		Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);
		strStrm.map(s->s.toLowerCase()).distinct().sorted().forEach(s->System.out.println("one : "+s));
		
		System.out.println("\n---------------------------\n");
		String[] lineArr = {"Belive or not It is true",
							"Do or not There is no try"};
		Stream<String> lineStrm = Arrays.stream(lineArr);
		lineStrm.flatMap(s->Stream.of(s.split(" "))).map(s->s.toLowerCase())
//		lineStrm.flatMap(s->Stream.of(s.split(" +"))).map(s->s.toLowerCase())
				.distinct().sorted().forEach(s->System.out.println("two : "+s));
		
		System.out.println("\n---------------------------\n");
		Stream<String> strStrm1 = Stream.of("AAA", "ABC","bBb", "Dd");
		Stream<String> strStrm2 = Stream.of("bbb", "aaa","ccc", "dd");
		
		Stream<Stream<String>> strStrmStrm = Stream.of(strStrm1, strStrm2);
		Stream<String> strStream = strStrmStrm.map(s->s.toArray(String[]::new)).flatMap(Arrays::stream);
		strStream.map(String::toLowerCase).distinct().sorted().forEach(System.out::println);
		
		System.out.println("---------------------------");
		System.out.println("------ Optional 예제 ------");
		System.out.println("---------------------------\n");
		
		System.out.print("예1)");
		Optional<String> optStr = Optional.of("abcde");
		Optional<Integer> optInt = optStr.map(s->s.length());
		System.out.println("optStr : "+optStr.get());
		System.out.println("optInt : "+optInt.get());
		
		
		System.out.println("\n---------------------------\n");
		System.out.print("예2)");
		int res1 = Optional.of("123").filter(s->s.length() > 0)
						   .map(s->Integer.parseInt(s)).get();
		
		int res2 = Optional.of("").filter(s->s.length() > 0)
		  				  .map(s->Integer.parseInt(s)).orElse(-1);
		System.out.println("res1 : "+res1);
		System.out.println("res2 : "+res2);
		Optional.of("456").map(Integer::parseInt)
		.ifPresent(s->System.out.printf("res : %d%n", s));
				
		System.out.println("\n---------------------------\n");
		System.out.println("ex4)");
		
		OptionalInt opt1 = OptionalInt.of(0); 	// OptionalInt 객체에 0 저장
		OptionalInt opt2 = OptionalInt.empty(); // 빈 객체를 생성
		
		System.out.println(opt1.isPresent());	// true
		System.out.println(opt2.isPresent());	// false
		
		System.out.println(opt1.getAsInt());	// 0
//		System.out.println(opt2.getAsInt());	// NoSuchElementException
		
		System.out.println("opt1 : "+opt1);	// 0
		System.out.println("opt2 : "+opt2);	// 0
		System.out.println("opt1.equals(opt2)?:"+opt1.equals(opt2));
		
		System.out.println("\n---------------------------\n");
		System.out.println("ex5)");
		
		Optional<String> optS = Optional.ofNullable(null); 	// null 저장
		Optional<String> optS2 = Optional.empty(); 			// 빈 객체 생성
		System.out.println("opt : "+optS);
		System.out.println("opt2 : "+optS2);
		System.out.println("opt1.equals(opt2)?:"+optS.equals(optS2));
		
		System.out.println("\n---------------------------\n");
		System.out.println("ex6)");
		int res3 = optStrToInt(Optional.of("123"), 0);
		int res4 = optStrToInt(Optional.of(""), 0);
		System.out.println("res3"+res3);
		System.out.println("res4"+res4);
		
	}
	
	public static int optStrToInt(Optional<String> optStr, int defaultVal) {
		try {
			return optStr.map(s->Integer.parseInt(s)).get();
		} catch (Exception e) {
			return defaultVal;
		}
	}
	
	
}


