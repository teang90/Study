package javaStudy.Stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx_7 {

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
		
		Stream<Student2> stuStream = Stream.of(stdArr);
		
		// 1) 기본 분할 -> 성별에 의한 분할
		Map<Boolean,List<Student2>> stuByGender = Stream.of(stdArr).collect(Collectors.partitioningBy(s->s.isMale()));
		List<Student2> maleList = stuByGender.get(true); 		// map 에서 남자만
		List<Student2> femaleList = stuByGender.get(false);		// map 에서 여자만
		maleList.stream().forEach(s->System.out.println(s.getName()+s.isMale()+s.getScore()));
		femaleList.stream().forEach(s->System.out.println(s.getName()+s.isMale()+s.getScore()));
		
		// 2.1) 기본분할 + 통계 -> counting으로 남학생의 수와 여학생의 수 구하기 
		Map<Boolean, Long> countList = Stream.of(stdArr).collect(Collectors.partitioningBy(Student2::isMale, Collectors.counting()));
		System.out.println("남학생 수 : "+countList.get(true));
		System.out.println("여학생 수 : "+countList.get(false));

		// 2.2) 기본분할 + 총점 -> counting 대신 summingLong()을 사용하면 남학생과 여학생 각각의 총점
		Map<Boolean, Long> sumList = Stream.of(stdArr).collect(Collectors.partitioningBy(Student2::isMale, Collectors.summingLong(Student2::getScore)));
		System.out.println("남학생 총점 : "+sumList.get(true));
		System.out.println("여학생 총점 : "+sumList.get(false));

		// 2.3) 기본분할 + 남학생, 여학생 각 1등 구하기
		Map<Boolean, Optional<Student2>> topScoreByGender = Stream.of(stdArr).collect(Collectors.partitioningBy(Student2::isMale, Collectors.maxBy(Comparator.comparing(Student2::getScore))));
		System.out.println("남학생 1등 : "+topScoreByGender.get(true));
		System.out.println("여학생 1등 : "+topScoreByGender.get(false));
		
		// 2.4) mapBy는 반환타입이 Optional<Student2>이기 때문에 위의 결과와 같이 나왔다. 
		// 만약, Student2를 반환결과로 얻으로면, 아래와 같이 collectingAndThen()과 Optional::get을 함께사용한다.
		Map<Boolean, Student2> topScore2 = Stream.of(stdArr).collect(
				Collectors.partitioningBy(s->s.isMale(),Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Student2::getScore)), Optional::get))); 
		System.out.println("남 1등 : "+topScore2.get(true));
		System.out.println("여 1등 : "+topScore2.get(false));
		
		// 2.5) 조건 아래의 요소 필터링하기(150점 아래의 학생 성별로 분류하여 얻어내기)
		Map<Boolean, Map<Boolean, List<Student2>>> failStuByGender = Stream.of(stdArr).collect(Collectors.partitioningBy(Student2::isMale, Collectors.partitioningBy(s->s.getScore() < 150)));
		List<Student2> failedMaleList = failStuByGender.get(true).get(true);
		List<Student2> failedFemaleList = failStuByGender.get(false).get(true);
		List<Student2> passMaleList = failStuByGender.get(true).get(false);
		List<Student2> passFemaleList = failStuByGender.get(false).get(false);

		StringBuilder sb = new StringBuilder();
		failedMaleList.forEach(s->sb.append(s.toString()+"|"));  
		sb.append("\n");
		
		failedFemaleList.forEach(s->sb.append(s.toString()+"|"));
		sb.append("\n");

		passMaleList.forEach(s->sb.append(s.toString()+"|"));
		sb.append("\n");
		
		passFemaleList.forEach(s->sb.append(s.toString()+"|"));
		System.out.println("요소 필터링 : "+sb.toString());
		
		
	}
}
