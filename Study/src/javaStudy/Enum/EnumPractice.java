package javaStudy.Enum;

import java.lang.reflect.Array;
import java.util.Arrays;

public class EnumPractice {
	/* enum 주요 메소드
	 * - getDeclaringClass() : 열거형 class 객체를 리턴
	 * - String name()		 : 열거형 상수의 이름을 문자열로 리턴
	 * - int ordinal()		 : 열거형 상수가 정의된 순서를 리턴(0부터 시작)
	 * - T valueOf(Class<T> enumType, String name)	: [지정된 열거형에서] name과 일치하는 열거형 상수를 리턴
	 * - T valueOf(String name)						: [지정된 열거형에서] name과 일치하는 열거형 상수를 리턴
	 * - T[] values()		 : 열거형 상수들을 배열 형태로 리턴 
	 */
	public enum Season{
		SPRING, SUMMER, AUTUMN, WINTER
	}
	
	public enum SeasonWithField{
		SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
		private final int index ;
		private SeasonWithField(int index) {
			this.index = index;
		}
		public int getIndex() {
			return index;
		}
	}
	
	public static void main(String[] args) {
		Arrays.stream(Season.values()).forEach(s->{
			System.out.println("index : "+s.ordinal()+", enum : "+s.name());
		});
		
		// String을 enum으로 받기 
		Season spring = Season.valueOf("SPRING");
		Season winter = Season.valueOf("WINTER");
		System.out.println("str to enum1 : "+spring);
		System.out.println("str to enum2 : "+winter);
		
		// enum을 String으로 받기
		String autumn = Season.AUTUMN.toString();
		String summer = Season.SUMMER.name();
		if (summer instanceof String && autumn instanceof String) {
			System.out.println("enum to string success!");
		}
		System.out.println("enum to str1 : "+autumn);
		System.out.println("enum to str2 : "+summer);
		
		// result
//		index : 0, enum : SPRING
//		index : 1, enum : SUMMER
//		index : 2, enum : AUTUMN
//		index : 3, enum : WINTER
	}
}
