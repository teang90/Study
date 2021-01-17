package javaStudy.kaiHostman_java8.ch10.index2;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Ex2_ThreadSafe {
	// 병행 프로그래밍에서 잘못되는 부분, 대비할 수 있는 내용을 소개
	
	
	public static void main(String[] args) {
//		가시성();
		경쟁();
		
	}
	
	/** ---------------------------------------
	 	-- 1. 가시성
	 	--------------------------------------- */
	private static volatile boolean done;
//	private static boolean done; 	// volatile 적용 전(문제...)
	public static void 가시성() {
		/* 변수 업데이트가 보이게 보장하는 방법
		 * 1. final 변수의 값은 초기화 후에 보인다.
		 * 2. static 변수의 초깃값은 정적 초기화 후에 보인다.
		 * 3. volatile 변수의 변경은 보인다.
		 * 4. 잠금을 해제하기 전에 일어나는 변경은 같은 잠금을 획득하는 쪽에 보인다.
		 */
		System.out.println("가시성 start");
		Runnable r1 = ()->{
			for (int i = 0; i < 1000; i++) {
				if (i%100==0) System.out.println(i); 
//				if (i%10==0) System.out.println(i); 
			}
			done = true;
		};
		
		Runnable r2 = ()->{
			int i = 1;
			while(!done) i++; 
			
			System.out.println("bye~"+i);
		};
		Executor exe = Executors.newCachedThreadPool();
		exe.execute(r1);
		exe.execute(r2);
		System.out.println("가시성 end");
	} 
	
	 
	/** ---------------------------------------
	 	-- 2. 경쟁(공유 자원)
	 	--------------------------------------- */
	// 병행 테스크 여러개가 공유하는 정수 카운터를 업데이트하는 예제
	// 100 * 1000 -> 결과 값이 100000 이 안나옴, 출력 순서도 순서가 뒤죽박죽임
	// -> 공유 변수가 변경될 때마다 일어나는 문제이다.
	// -> 해결) 잠금을 이용하여 임계적인 연산을 원자적으로 만드는 것 -> 주의사항> 퍼포먼스 저하 & 교착상태 야기 가능성있음	 
	private static volatile int shareInt = 0;
	public static void 경쟁() {
		int loopConst = 100;
		int threadConst = 1000;
		for (int i = 0; i < loopConst; i++) {
			int taskId = i;
			Runnable r = () -> {
				for (int j = 0; j < threadConst; j++) {
					shareInt++;
				}
				System.out.println(taskId+" : "+shareInt);
			};
			Executors.newCachedThreadPool().execute(r);
		}
	}
	
	/** ---------------------------------------
	 	-- 3. 안전한 병행성을 실현히는 전략
	 	--------------------------------------- */
	
	
	
	
	
}
