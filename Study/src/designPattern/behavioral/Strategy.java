package designPattern.behavioral;

/*
 * 각각의 알고리즘 군을 교환이 가능하도록 별도로 정의하고 각각을 캡슐화 한 후 서로 교환하여 사용
 * -> 프로젝트 전체에서 변경이 일어나지 않는 부분에서 변경이 일어나는 부분을 찾아서 따로 캡슐화한다.
 * 
 * 장점> 코드 중복 방지, 런타임 시에 타겟 메소드 변경, 확장성(신규) 및 알고리즘 변경 용이
 * 
 * https://www.tutorialspoint.com/design_pattern/design_pattern_quick_guide.htm
 * https://niceman.tistory.com/133
 * 
 * 팩토리 vs 전략:팩토리 패턴은 특정 유형의 객체를 생성하는데 집중되어있다. 반면 전략 패턴은 특정 방식으로 로직&작업 등을 수행하는데 사용
	-> 팩토리는 개, 고양이, 말, 호랑이 등의 다른 유형의 동물을 생성할 수 있는 반면, 
	     전략 패턴은 달리기, 걷기, 수영하기 등의 서로 다른 방법을 수행할 수 있다.
 -> https://www.it-swarm-ko.tech/ko/java/%ed%8c%a9%ed%86%a0%eb%a6%ac-%ed%8c%a8%ed%84%b4%ea%b3%bc-%ec%a0%84%eb%9e%b5-%ed%8c%a8%ed%84%b4%ec%9d%98-%ec%b0%a8%ec%9d%b4%ec%a0%90%ec%9d%80-%eb%ac%b4%ec%97%87%ec%9e%85%eb%8b%88%ea%b9%8c/957773712/
  
 * 
 */
public class Strategy {
	/**
	 * main --req--> context --strategy, executeStrategy and use starategy ---
	 * 						  ---> starategy Interface -->  each implements Class
	 */

	//  전략패턴 - 필요한 전략(알고리즘, 조건, 상태, 상황)에 따라, 그에 맞는 로직을 타게해준다.
	public static void main(String[] args) {
		Strategy s = new Strategy();
		
		Context context = s.new Context(s.new OperationAdd());
		System.out.println("+ : "+context.executeOperation(10, 5));
		
		context = s.new Context(s.new OperationMultiply());
		System.out.println("* : "+context.executeOperation(10, 5));
		
		context = s.new Context(s.new OperationSubstract());
		System.out.println("- : "+context.executeOperation(10, 5));
		
	}
	
	// create Interface
	public interface StrategyInterface{
		public int doOperation(int a, int b);
	} 
	
	class OperationAdd implements StrategyInterface{
		@Override
		public int doOperation(int a, int b) {
			return a+b;
		}
	}
	
	class OperationSubstract implements StrategyInterface{
		@Override
		public int doOperation(int a, int b) {
			return a-b;
		}
	} 
	
	class OperationMultiply implements StrategyInterface{
		@Override
		public int doOperation(int a, int b) {
			return a*b;
		}
	} 

	class Context{
		private StrategyInterface strategy;
		
		public Context() {
		}
		
		public Context(StrategyInterface strategyInterface) {
			this.strategy = strategyInterface;
		}
		
		public int executeOperation(int a, int b) {
			return strategy.doOperation(a, b);
		}
	}
	
	
}
