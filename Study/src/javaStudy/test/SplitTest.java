package javaStudy.test;

public class SplitTest {
	public static void main(String[] args) {
		String input = "123";
		String[] arrs = input.split("@");
		String[] arrs2 = input.split("2");
		
		System.out.println("---------------------------arrs 예시");
		for (String string : arrs) {
			System.out.println(string);
		}
		System.out.println("---------------------------arrs 예시 끝");
		
		
		System.out.println("---------------------------arrs2 예시");
		for (String string : arrs2) {
			System.out.println(string);
		}
		System.out.println("---------------------------arrs2 예시 끝");
		
		// 결론 split("A") 구분자가 없으면 찢을 대상의 문자열 하나를 통째로 [0] 요소로 반환
	}
}
