package designPattern.creational;

/* 
	상위 클래스와 하위 클래스가 있을때, 팩토리 클래스를 사용하여 하위 클래스 인스턴스를 생성하는 패턴
	-> 객체 생성을 대신 수행해주는 공장 클래스
	-> 객체 생성을 서브 클래스로 분리하여 처리하도록 캡슐화하는 패턴
	
	팩토리를 사용하면 새 도형 종류 추가 여부에 상관없이 
	다른 클래스를 수정할 필요가 없어져 단일 책임 원칙을 잘 지키는 코드가 된다.
	
	TY:팩토리 패턴은 특정 유형의 객체를 생성하는데 집중되어있다. 반면 전략 패턴은 특정 방식으로 로직&작업 등을 수행하는데 사용
	-> 팩토리는 개, 고양이, 말, 호랑이 등의 다른 유형의 동물을 생성할 수 있는 반면, 
	     전략 패턴은 달리기, 걷기, 수영하기 등의 서로 다른 방법을 수행할 수 있다.
 -> https://www.it-swarm-ko.tech/ko/java/%ed%8c%a9%ed%86%a0%eb%a6%ac-%ed%8c%a8%ed%84%b4%ea%b3%bc-%ec%a0%84%eb%9e%b5-%ed%8c%a8%ed%84%b4%ec%9d%98-%ec%b0%a8%ec%9d%b4%ec%a0%90%ec%9d%80-%eb%ac%b4%ec%97%87%ec%9e%85%eb%8b%88%ea%b9%8c/957773712/
  
	    
*/
public class Factory {

	public static void main(String[] args) {
		System.out.println("-------------------start-------------------");
		
		// 팩토리 클래스에서 객체를 생성 후 반환
		Factory f = new Factory();
		ShapeFactory shapeFactory = f.new ShapeFactory();
		
		// circle 메소드 호출
		Shape circle = shapeFactory.getShape("CIRCLE");
		circle.draw();
		
		// rectangle 메소드 호출
		Shape rectangle = shapeFactory.getShape("RECTANGLE");
		rectangle.draw();
		
		// square 메소드 호출
		Shape square = shapeFactory.getShape("SQUARE");
		square.draw();
		
		System.out.println("-------------------end-------------------");
	}
	
	public interface Shape{
		void draw();
	}
	
	class Circle implements Shape{
		@Override
		public void draw() {
			System.out.println("implement Shape to Circle Class draw() : ○○○○○");
		}
	}
	
	class RectangleF implements Shape{
		@Override
		public void draw() {
			System.out.println("implement Shape to Rectangle Class draw() : ◇◇◇◇◇");
		}
	}
	
	class Square implements Shape{
		@Override
		public void draw() {
			System.out.println("implement Shape to Square Class draw() : □□□□□");
		}
	}


	class ShapeFactory{
		// 팩토리 메소드 - 객체 성성 및 반환
		public Shape getShape(String shapeType) {
			if (shapeType == null) return null;
			
			if ("CIRCLE".equals(shapeType)) {
				return new Circle();
			} else if ("RECTANGLE".equals(shapeType)) {
				return new RectangleF();
			} else if ("SQUARE".equals(shapeType)) {
				return new Square();
			}
	
			return null;
		}
	}
	
}
