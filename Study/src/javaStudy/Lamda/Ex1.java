package javaStudy.Lamda;

public class Ex1 {

	public static void main(String[] args) {
		
		//  두 숫자 더하기
		// 메소드에 함수형 인터페이스를 구현한 구현체(?)를 넣음 = 구현한 객체를 넣는것과 동일
		exec((a,b)->{return a+b;}); 

		
		// 두 수중 큰 수 구하기
		int x = (int)(Math.random()*100)/10;
		int y = (int)(Math.random()*100)/10;
		System.out.println("x : "+x+", y : " +y);
		
		DeterminBiggerNumber getBig = (n, m) -> (n>=m)?n:m;
		System.out.println(getBig.getMax(x, y));
		
		
		// 스레드 생성 1
		Runnable runnable = ()->{
			for (int i = 0; i < 5; i++) {
				System.out.println("firstly make threadrun : "+i);
			}
		};
		new Thread(runnable).start();
		
		// 스레드 생성 2
		Thread thread = new Thread(()-> {
			for (int i = 0; i < 5; i++) {
				System.out.println("secondly make threadrun : "+i);
			};
		});
		
		thread.start();
		
	}
	
	public static void exec(Compare com) {
		int a = 10;
		int b = 20;
		int result = com.compare(a, b);
		System.out.println("result1 = " + result);
	}
	
	interface Compare{
		public int compare(int a, int b);
	}
	
	@FunctionalInterface
	interface DeterminBiggerNumber{
		public int getMax(int a, int b);
	}
	
}
