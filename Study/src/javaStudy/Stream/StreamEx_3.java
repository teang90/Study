package javaStudy.Stream;

import java.io.File;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx_3 {
	
	public static void main(String[] args) {
		File[] fileAddr = {new File("Ex1.java"), 
						 new File("Ex1.bak"), 
						 new File("Ex2.java"), 
						 new File("Ex1"), 
						 new File("Ex1.txt") 
						 };
		
		Stream<File> fileStream = Stream.of(fileAddr);
		Stream<String> fileNames = fileStream.map(File::getName);
		fileNames.forEach(s->System.out.print(s));
		
		System.out.println("\n=============== 1 ===============\n");
		Stream<String> fileNames2 = Stream.of(fileAddr).map(s->s.getName());
		fileNames2.forEach(System.out::print);
		
		System.out.println("\n=============== 2 ===============\n");
		Stream.of(fileAddr)
			  .map(s->s.getName())
			  .filter(s->s.indexOf(".") != -1)
			  .map(s->s.substring(s.indexOf(".")+1))
			  .map(s->s.toUpperCase())
			  .distinct()
			  .forEach(s->System.out.print(s));
		
		
		
		System.out.println("\n--------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("---------------mapToInt, mapToDouble, IntStream, Stream<Integer>등 예제---------------");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------\n");
		
		Student[] stdArr = {
							new Student("Lee", 	3, 300),
							new Student("Kim", 	1, 200),
							new Student("Park", 2, 100),
							new Student("Cho", 	2, 150),
							new Student("Jeong",1, 200),
							new Student("Choi", 3, 290),
							new Student("Jang", 3, 180)
							};
		int cnt = 0 ;
		Stream<Student> stdStream = Stream.of(stdArr);
		stdStream.sorted(Comparator.comparing(Student::getBan)
						 .thenComparing(Comparator.naturalOrder()))
						 .forEach(s->System.out.print(s.getName()+"\n"));
		
		stdStream = Stream.of(stdArr); // 스트림 재생성
		IntStream stdScrStream = stdStream.mapToInt(Student::getScore);
		
		IntSummaryStatistics stat = stdScrStream.summaryStatistics();
		System.out.println("count : "+stat.getCount());
		System.out.println("sum   : "+stat.getSum());
		System.out.println("avg   : "+stat.getAverage());
		System.out.println("min   : "+stat.getMin());
		System.out.println("max   : "+stat.getMax());
						 
		
		
		
		
	}
	
}


class Student implements Comparable<Student>{
	String name = "";
	int ban = 0 ;
	int score = 0;
	public Student() {}
	public Student(String name, int ban, int score) {
		this.name = name;
		this.ban = ban;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBan() {
		return ban;
	}
	public void setBan(int ban) {
		this.ban = ban;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(Student std) {
		return std.getScore() - this.score;
	}
	
}