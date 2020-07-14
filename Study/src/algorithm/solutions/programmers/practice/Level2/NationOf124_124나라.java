package algorithm.solutions.programmers.practice.Level2;

public class NationOf124_124나라 {
	// https://programmers.co.kr/learn/courses/30/lessons/12899
	
	public static void main(String[] args) {
		int n = 1;
//		int[] inArrary = {15};
		int[] inArrary = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,20};
		for (int i : inArrary) {
			String answer = solution(i);
			System.out.println("answer : "+answer);
		}
	}

	//재귀 사용하지 않고...
	public static String solution(int n) {
		String answer = "";
		int q = n;
		while (true) {
			int r = q%3==0?4:q%3;
			answer=r+answer;
			q=(r==4?(q/3)-1:q/3);
			if (q<=3) {
				answer = (q==3?4:q)+answer;
				break; 
			} 
		}
		return answer.replace("0", "");
	}

//	/** 재귀 사용 -> 효율성 fail */
//	public static String solution(int n) {
//		return String.valueOf(recur(n));
//	}
//	
//	// 재귀로 뱉는 함수...
//	public static int recur(int n) {
//		String res = "";
//		res = (n%3==0)?res+"4":res+n%3;
//		if(n > 3) res=(n%3==0?recur(n/3 - 1):recur(n/3))+res;
//		return Integer.parseInt(res);
//	}
}

/** 다른사람 풀이 */
//class Solution {
//  public String solution(int n) {
//      String[] num = {"4","1","2"};
//      String answer = "";
//
//      while(n > 0){
//          answer = num[n % 3] + answer;
//          n = (n - 1) / 3;
//      }
//      return answer;
//  }
//}
