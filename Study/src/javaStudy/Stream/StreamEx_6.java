package javaStudy.Stream;

import javaStudy.Stream.*;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class StreamEx_6 {

	public static void main(String[] args) {
	// Student 클래스는 StreamEx_3에 내부 클래스로 정의되었음
		
		Student[] stuArr = {
				new Student("Lee", 3, 300),
				new Student("Kim", 1, 200),
				new Student("Ahn", 2, 100),
				new Student("Park", 2, 150),
				new Student("Jeong", 3, 290)
		};
		
		// 학생 이름만 뽑아서 list에 저장
		List<String> names = Stream.of(stuArr).map(s->s.getName()).collect(Collectors.toList());
		System.out.println("학생 이름 : "+names);
		
		
		// 스트림을 배열로 변환
		Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);
		for (Student st2 : stuArr2) {
			System.out.println("학생 이름 2 : " + st2.getName());
		}
		
		// 스트림을 Map<String, Student>로 변환, 학생 이름이 key
		Map<String, Student> stuMap = Stream.of(stuArr2).collect(Collectors.toMap(s->s.name, v->v));
		System.out.println("학생 map : "+stuMap);
		
		/* ********************************************** */
		/* ******************** 통계 ******************** */
		/* ********************************************** */
		System.out.println("\n/* ******************** 통계 ******************** */");
		long count = Stream.of(stuArr).collect(Collectors.counting());
		System.out.println("collect() > count : " + count);
		long count2 = Stream.of(stuArr).count();
		System.out.println("not collect() > count : " + count2);
		long totScore = Stream.of(stuArr).collect(Collectors.summingInt(Student::getScore));
		System.out.println("totScore : "+totScore);
		long totScore2 = Stream.of(stuArr).collect(Collectors.reducing(0, Student::getScore, Integer::sum));
		System.out.println("totScore2 : "+totScore2);
		
		/* ********************************************** */
		/* ******************** 리듀싱 ******************** */
		/* ********************************************** */
		System.out.println("\n/* ******************** 리듀싱 ******************** */");
		Optional<Student> topStudent = Stream.of(stuArr).collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore)));
		System.out.println("topStudent : "+topStudent.get().getName()); // topStudent : Lee
		
		IntSummaryStatistics stat = Stream.of(stuArr).collect(Collectors.summarizingInt(Student::getScore));
		System.out.println("stat  :"+stat); // stat  :IntSummaryStatistics{count=5, sum=1040, min=100, average=208.000000, max=300}
		
		String stuName = Stream.of(stuArr).map(Student::getName).collect(Collectors.joining(",","{","}"));
		System.out.println(stuName);
		
		
				
	}
}
