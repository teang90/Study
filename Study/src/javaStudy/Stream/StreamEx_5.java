package javaStudy.Stream;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx_5 {

	public static void main(String[] args) {
		System.out.println("------------- reduce -------------");
		String[] strArr = {"Inheritance","Java","Lamda","stream",
							"OptionalDouble","IntStream", "count","sum"};
		Stream.of(strArr).forEach(s->System.out.print("->"+s));
	
		boolean noEmptyStr = Stream.of(strArr).noneMatch(s->s.length()==0);
		Optional<String> sWord = Stream.of(strArr).filter(s->s.charAt(0)=='s')
										.findFirst();
		
		Optional<String> sWord2 = Stream.of(strArr).filter(s->s.charAt(0)=='s')
									.findAny();
		Stream<String> sWord3 = Stream.of(strArr).filter(s->s.charAt(0)=='s');
		
		System.out.println("noEmpty : "+noEmptyStr);
		System.out.println("sWord 	: "+sWord.get());
		sWord3.forEach(s-> System.out.println("\tsWord3's factor : \t"+s));
		//Stream<String[]>을 IntStream으로 변환
		IntStream intStrm1 = Stream.of(strArr).mapToInt(String::length);
		IntStream intStrm2 = Stream.of(strArr).mapToInt(String::length);
		IntStream intStrm3 = Stream.of(strArr).mapToInt(String::length);
		IntStream intStrm4 = Stream.of(strArr).mapToInt(String::length);
		
		int count = intStrm1.reduce(0, (a,b)->a+1);
		int sum   = intStrm2.reduce(0, (a,b)->a+b);

		OptionalInt max = intStrm3.reduce(Integer::max);
		OptionalInt min = intStrm4.reduce(Integer::min);
		System.out.println("count : "+count);
		System.out.println("sum   : "+sum);
		System.out.println("count : "+count);
		System.out.println("max   : "+max.getAsInt());
		System.out.println("min   : "+min.getAsInt());
		
		System.out.println("------------- collect -------------");
		
	}
}