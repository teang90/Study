package javaStudy.Youtube.lamda;

import org.omg.PortableInterceptor.Interceptor;

public class Lamda1 {
	/* 
	 * (a) 	 -> {body;}
	 * a 	 -> {body;} 		// 하나의 매개변수 - 파라미터 괄호 생략
	 * a 	 -> body; 			// 하나의 실행문 - 중괄호 생략 
	 * () 	 -> {body;}			// 매개변수 없다면 파라미터 괄호 생략 불가
	 * (a,b) -> {return x+y;} 	// 리턴 값이 있는경우 return 사용 
	 * (x,y) -> x+y; 			1//return 만 있는 경우 중괄호 생략 
	 */
	
	// ex1 as-is method convert into lamda expression
	public void ex1() {
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Anonymous function");
			}
		}; 

		Runnable r2 = () -> {
			// TODO : customizing function body
			System.out.println("lamda expression");
		};
	}
	
	/*
	 타겟 타입 : 람다식이 대입되는 인터페이스 
	 		-> 함수형 인터페이스의 구현부가 람다식의 바디가 된다.
	 		-> 익명구현객체를 만들때 사용할 인터페이스임

	 ex) 인터페이스 변수 = 람다식;(익명 구현 객체)
	
	 * 타겟타입은 하나의 추상메소드만 가지고 있는 함수형 인터페이스만이 타겟타입이 될 수 있다.
	 * 	@FunctionalInterface (명시적 표현일 뿐, 해당 어노테이션이 필수는 아님)
	 * 
	 * 
	 * */
	public static void ex3() {
		
		// 익명 구현 객체 구현
		Ex_FunctionalInterface funcItf = ()->{
			// Ex_FunctionalInterface 인터페이스의 구현부의 body 구체화
			System.out.println("================= Lamda function =================");
			System.out.println("구현 부분 실행합니다.");
		};
		funcItf.exMethod(); // funcItf 구현 객체(추상 함수의 구현 부분) 실행...
		
		funcItf = ()->{
			// Ex_FunctionalInterface 인터페이스의 구현부의 body 구체화
			System.out.println("구현 부분 변경 1");
			System.out.println("구현 부분 변경 2");
			System.out.println("구현 부분 변경 3");
		};
		funcItf.exMethod();
		
		// 위의 람다식은 아래의 익명 객체와 동일
		funcItf = new Ex_FunctionalInterface() {
			@Override
			public void exMethod() {
				System.out.println("\n================= Anonymous function =================");
				System.out.println("익명객체 생성하여 진행");
			}
		};
		funcItf.exMethod();
		
		int a = 3;
		int b = 5;
	
		Ex2_FunctionalInterface intf2 = (x, y) -> {
			System.out.println("--------- implements interface2 ---------");
			return x*y;
		};
		System.out.println("return 람다 : "+intf2.ex2Method(a, b));
		
		
	}

	@FunctionalInterface
	interface Ex_FunctionalInterface {
		public void exMethod();
	}
	
	interface Ex2_FunctionalInterface {
		public int ex2Method(int x, int y);
	}
	
	public static void main(String[] args) {
		ex3();
	}
}
