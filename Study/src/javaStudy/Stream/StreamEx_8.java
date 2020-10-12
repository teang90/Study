package javaStudy.Stream;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx_8 {

//	public Set<Characteristics> characteristics(){
//		return Collections.unmodifiableSet(EnumSet.of(
//				Collector.Characteristics.CONCURRENT,
//				Collector.Characteristics.UNORDERED));
//	}
//	
//	Set<Characteristics> characteristics(){
//		return Collections.emptySet();
//	}
	
	public static void main(String[] args) {
		

		
		Student2[] stdArr = new Student2[10];
		
		for (int i = 0; i < 10; i++) {
			stdArr[i] = new Student2(
						"name"+i, 
						i%2==0?true:false, 
						(Math.random()*10)%2==0?2:1, 
						(int)(Math.random()*10), 
						(int)(Math.random()*300));
		} 

		// 학생 클래스를 반별로 그룹지어 Map에 저장하는 방법
		// -->> Map<Integer, List<Student2>> stuByBan = Stream.of(stdArr).collect(Collectors.groupingBy(s->s.getBan()));
		
		// groupingBy()로 그룹화를 하면 기본적으로 List<T>에 담는다. 그래서 위의 문장은 아래 문장의 생략된 형태이다. 
		// 만일 원한다면, toList() 대신 toSet(), toCollection(HashSet::new)를 사용할 수 있다.
		// 단, Map의 제네릭 타입도 적절히 변경해줘야한다.
		Map<Integer, List<Student2>> stuByBan = Stream.of(stdArr).collect(Collectors.groupingBy(Student2::getBan, Collectors.toList())); // toList 생략 가능
		System.out.println("1. 반으로 그룹핑 : "+stuByBan);
		Map<Integer, Set<Student2>> stuByHak = Stream.of(stdArr).collect(Collectors.groupingBy(Student2::getHak, Collectors.toCollection(HashSet::new))); 
		System.out.println("2. 학년으로 그룹핑 : "+stuByBan);
		
		
		// 학생 성적의 등급으로 그룹화 해보자
		Map<Student2.Level, Long> stuByLvl = Stream.of(stdArr)
											.collect(Collectors.groupingBy(s->{
												if (s.getScore() >= 200) 	  return  Student2.Level.HIGH;
												else if (s.getScore() >= 100) return  Student2.Level.MID; 
												else  						  return  Student2.Level.LOW;
											}, Collectors.counting()));

		// groupingBy()를 여러번 사용하면, 다수준 그룹화가 가능하다. 만일 학년별로 그룹화 한 후에 다시 반별로 그룹화하려면 다음과 같이 사용한다.
		Map<Integer, Map<Integer, List<Student2>>> stuByHakAndBan = Stream.of(stdArr).collect(Collectors.groupingBy(s->s.getHak(), Collectors.groupingBy(s->s.getBan())));
		System.out.println("학년, 반별 분류 : "+stuByHakAndBan);
		
		// 위의 예제에서 각 반 1등을 추출하고 싶다면, collectionAndThen(), maxBy()를 사용한다.
		Map<Integer, Map<Integer, Student2>> topStuByHakAndBan = Stream.of(stdArr).collect(
												Collectors.groupingBy(Student2::getHak,
													Collectors.groupingBy(Student2::getBan,Collectors.collectingAndThen(
														Collectors.maxBy(Comparator.comparingInt(Student2::getScore)),Optional::get)))); 
																																	
																
																								
				
		
				
		
		
		
		
		
	}
}
