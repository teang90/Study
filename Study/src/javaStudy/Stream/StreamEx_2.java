package javaStudy.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx_2 {
	
	public static void main(String[] args) {
		List<VO> list = new ArrayList<VO>();
		list.add(new VO());	
		list.add(new VO());	
		list.add(new VO());	
		list.add(new VO());
		
		// Stream practice 1
		list.stream().map(vo -> vo.age+vo.height).forEach(x->System.out.println(x));
						
		// Stream practice 2
		
				
				
												 
		
		
		
	}
	
	
}

class VO{
	String name = "";
	String id   = "";
	int age     = 0 ;
	double height = 0;
	public VO() {
		this.name=UUID.randomUUID().toString();
		this.id=UUID.randomUUID().toString();
		this.age = (int)(Math.random()*10);
		this.height = Math.random()*100;
	}
}
