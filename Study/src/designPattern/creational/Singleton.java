package designPattern.creational;

/** 
 * 전역변수를 사용하지 않고 객체를 하나만 생성하여 생성된 객체를 어디서든지 참조할 수 있도록하는 패턴
 * -> 하나의 인스턴스만을 생성하는 책임이 있으며, getInstance()를 통해 모든 클라이언트(객체 생성요청)에게 단 하나(only one) 인스턴스를 반환한다.
 * 
 * 더 생각해볼것)
 * 다중 스레드에서 싱글턴 객체를 이용시 문제가 발생할 수 있다.
 * 
 * - 경합조건(race condition) 발생
 * 
 * - 
 */
public class Singleton {
	// 프린트 하나를 다수가 공유하는 상황
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			int r = i;
			Print print = Print.getInstance();
			NotSingleton notSingle = new NotSingleton();
			
			Runnable runJob = () -> {
				print.id +=r;
				notSingle.nId += r;
				print.print("single Id : "+print.id);
				notSingle.print("not single nId : "+notSingle.nId);
			};
			new Thread(runJob).start();
		}
	}
}

class Print {
	private static Print print = null;
	
	private Print() {}
	
	public int id = 0;
	
	public void print(String content) {
		System.out.println("singleton print : " + content);
	}
	
	public static Print getInstance() {
//	public Print getInstance() {
		if (print == null) print = new Print(); 
			
		return print;
	}
}

class NotSingleton{
	public int nId= 0 ;
	public void print(String content) {
		System.out.println("not singleton print : "+content);
	}
}