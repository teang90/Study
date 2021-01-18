package javaStudy.kaiHostman_java8.ch10.index1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/** parallerTask */
public class Ex1_Parallel_Task {
	/* TASK 실행하기
	 * 자바에서는 보통 다른 테스크와 동시에 Runnable 인터페이스에 실행할 테스크를 작성한다.
	 * 
	 */
	
	public static void main(String[] args) {
//		taskExecute();
		taskCallable();
	}
	
	/** TITLE : 테스크 실행하기
	 
	Executor exc = null;
	
	// newCachedThreadPool() - 각 태스크는 가능한 유휴 스레드에서 실행되며, 
	// 모든 스레드가 실행주이면 새로운 스레드가 할당된다. 장시간 유휴 상태의 스레드는 종료된다. 
	exc = Executors.newCachedThreadPool();
	
	// newFixedThreadPool() - 고정 개수 스레드 풀을 결과로 준다. 태스크를 제출하면(?) 해당 테스크는 스레드를 이용할수 있게 될 때까지 순서를 기ㅏ린다.
	// 따라서 해당 펙토리 메소드는 강도 높은 계산을 수행하는 테스크에 적합하다.
	// 가용 프로세스 개수로부터 스래드 개수를 알아내려면 Runtime.getRuntime().availableProcessors()를 사용한다. 
	int processors = Runtime.getRuntime().availableProcessors();
	exc = Executors.newFixedThreadPool(processors);
	*/
	public static void taskExecute() {
		System.out.println("start exe code" + "taskExecute");
		
		// task 실행시 작업 대상 코드 run 메소드의 body 부분
		Runnable hellos = ()->{
			for (int i = 0; i < 100; i++) {
				System.out.println("hello "+i);
			}
		};

		Runnable bye = ()->{
			for (int i = 0; i < 100; i++) {
				System.out.println("bye "+i);
			}
		};
		
		Executor exe = Executors.newCachedThreadPool();
		exe.execute(hellos);
		exe.execute(bye);
		
		System.out.println("end exe code"+"taskExecute");
	}
	
	/** TITLE: 퓨처와 실행자 서비스

	 * 서브테스크(하위작업)가 여러개로 나뉘는 계산의 경우 with 각 모든 테스크의 결과를 종합해야하는 경우
	 * 
	 * 서브태스크는 각각 부분 결과를 계산한다. 그리고 모든 테스크가 완료되면 결과들을 결합하려고 한다.
	 * 이러한 서브테스크에 Callable 인터페이스를 사용할 수 있다.
	 * Callable의 call 메소드는 Runnable 인터페이스의 run 메호드와 달리 값을 반환한다.
	 *
	 *  call 메소드는 추가로 임의의 예외를 던질 수 있다.
	 *  Callable을 실행하려면 Executor의 서브 인터페이스인 ExecutorService 인터페이스의 인스턴스가 필요하다.
	 *  Executors 클래스의 newCachedThreadPool과 newFixedThreadPool 메소드가 ExecutorService 객체를 돌려준다.
	 *  
	 *  public interface Callable<V>{
	 *  	V call() throws Exception;
	 *  }
	 * @param <V>
	 *  
	 */
	public static void taskCallable() {
		int availThreadCnt = Runtime.getRuntime().availableProcessors();
		ExecutorService exec = Executors.newFixedThreadPool(availThreadCnt);
		
		Callable<Object> task = ()->{
			List<Object> list = new ArrayList<>();
			for (int i = 0; i < 100; i++) {
				list.add(i);
			}
			return list; 
		};
		
		Future<Object> future = exec.submit(task);
		
		/*  task를 제출하면 퓨처객체를 얻는다. 퓨처 객체는 언젠가 결과를 얻게 되는 계산을 표현한다.
			Future 인터페이스는 다음의 메소드가 있다.
			
			V get() throws InterruptedException, ExecutionException
			V get(long timout, TimeUnit unit) throws InterruptedException, ExecutionException
		 	boolean cancel(boolean mayInterruptIfRunning)
		 	boolean inCanceled()
		 	boolean isDone()
		 	
		 	get() 메소드는 결과를 얻게 되거나 타임아웃에 이를때까지 블럭한다.
		 	그런 다음 계산된 값을 반환하거나, call 메소드에서 예외를 던졌을때 해당 예외를 감싸고 있는 ExecutionException를 던진다.
		 	
		 	cancel() 메소드는 태스크 취소를 시도한다. 테스크가 이미 실행 중인 상태가 아니면 해당 테스크는 스케쥴링되지않는다.
		 	테스크가 이미 실행 중이고, mayInterruptIfRunning이 true면 해당 테스크를 실행하는 스레드가 인터럽트된다.
		 	
		 	테스크를 인터럽트될 수 있게 하려면 다음과 같이 인터럽션 요청을 주기적으로 확인해야한다.
		 
		 	Callable<V> task = () -> {
		 		while(남은 작업이 있음){
		 			if(Thread.currentThread().isInterrupted()) return null;
		 			
		 			//남은 작업을 수행
		 		}
		 		return result;
		 	};
		 	
		 	
		 	----------------------------------------------------------------------------------------------------
		 	----------------------------------------------------------------------------------------------------
		 	
		 	invokeAll() 함수 (출처: https://tourspace.tistory.com/137 [투덜이의 리얼 블로그])
		 	
		 	ExecutorService에서 제공하는 함수로 callable list를 넘겨서 한꺼번에 수행하는 함수 입니다.
		 	(ExecutorService -> 테스크를 스레드에 매핑, 그 결과, 예외, 취소까지 처리해준다.)
		 	
		 	
		 	-> <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			-> <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			
			future list를 반환하고 전부 끝날떄까지 holding 된다.
			넘겨준 callable list가 정상 처리되든, exception 발생하던 완료된 것으로 본다.
			동작 중에 전달받은 list가 변경되면 결과를 보장하지 않는다.
			넘겨준 list 순서대로 결과 future를 담아서 넘겨준다.
			
			Future를 바로 반환하는 submit(), execute()와의 차이...
			-> invokeAll은 invokeAll()를 호출하는 line에서 blocking이 된다.
			-> 즉, 가지고있는 list의 작업이 전부 완료/timeout되야 blocking이 풀린다. 
			
			발생 할 수 있는 exception
			- InterruptedException : 동작이 종료되지 않은(동작중인) task가 취소 되었을때
			- NullPointerException : 함수의 param중 null이 있을때
			- RejectedExecutionException : task를 pool에 넣을수 없을때.
			
			->> 특이사항은 task 작업중 문제가 발생하는 경우 invokeAll에서 exception 발생 X,
			invokeAll의 결과로받는 Future를 get()할때 exception 발생한다....
			따라서 Futurue를 받는 get()부분에서 try-catch 처리를 해야한다.
			
			// invokeAny -> 제출한 테스크중 하나가 예외를 던지지 않고 완료하면 즉시 반환한다.
			   그리고 Future의 값을 반환하고, 다른 테스크는 취소한다.
		 */
		ExecutorService exec2 = Executors.newCachedThreadPool();
		String[] arr = {"abc", "def"};
		List<Callable<Object>> tasks = new ArrayList<>();
		Stream.of(arr).forEach(s->{
			tasks.add(()->{return s;});
		});
		
		try {
			List<Future<Object>> result = exec2.invokeAll(tasks);
			
			result.forEach(s->{
				try {
					System.out.println("res > "+s.get()); // get -> 스레드에서 처리한 결과를 가져온다.
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		} catch (CancellationException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
