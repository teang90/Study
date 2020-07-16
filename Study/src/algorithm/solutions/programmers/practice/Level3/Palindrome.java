package algorithm.solutions.programmers.practice.Level3;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
앞뒤를 뒤집어도 똑같은 문자열을 팰린드롬(palindrome)이라고 합니다.
문자열 s가 주어질 때, s의 부분문자열(Substring)중 가장 긴 팰린드롬의 길이를 return 하는 solution 함수를 완성해 주세요.

예를들면, 문자열 s가 abcdcba이면 7을 return하고 abacde이면 3을 return합니다.

제한사항
문자열 s의 길이 : 2,500 이하의 자연수
문자열 s는 알파벳 소문자로만 구성

입출력 예
str			answer
abcdcba		7
abacde		3

입출력 예 #1
4번째자리 'd'를 기준으로 문자열 s 전체가 팰린드롬이 되므로 7을 return합니다.

입출력 예 #2
2번째자리 'b'를 기준으로 aba가 팰린드롬이 되므로 3을 return합니다.
 * @author User
 *
 */
public class Palindrome {

	public static void main(String[] args) {
		String str = "abacdeffe";
//		String str = "abada";
//		String str = "abcdcba";
		System.out.println(solution(str));
	}

	public static int solution(String s) {
		int answer = 0;
		// 전체 길이를 측정
		int sLeng = s.length();	
		char[] charArray = new char[sLeng];

		// 특정 문자와 동일한 문자가 있는지, 위치가 어디인지, 그 위치가 대칭적인지 chk
		for (int i = 0; i < s.length(); i++) {
			char oneChar = s.charAt(i);

			for (int j = i+1; j < s.length(); j++) {
				if (oneChar == s.charAt(j)) {
					charArray[i]=oneChar;
					charArray[j]=oneChar;
				}
				
				if (i!=0 && i!=s.length() && 65<=charArray[i-1] && charArray[i-1]<=122 && charArray[i-1]==charArray[i+1])
					charArray[i]=oneChar;
			}
		}
		
		String beResult = String.valueOf(charArray);
		String[] result = beResult.split(" ");
//		Stream<Integer> arrStream = Arrays.stream(result, 0,1);
//		for (int i = 0; i < result.length; i++) {
//			if (condition) {
//				
//			}
//		}
		
		System.out.println(String.valueOf(charArray));
		// 둘의 위치가 대칭적이라면 사이의 문자도 동일하게 적용

		return answer;
	}
	
}


// 메모리 입출력과 디바이스 입출력 성능차이?
