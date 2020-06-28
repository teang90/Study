package algorithm.solutions.programmers.practice.Level2;

public class NationOf124_124나라 {
	// https://programmers.co.kr/learn/courses/30/lessons/12899
	
	public static void main(String[] args) {
		int n = 0;
		solution(n);
	}

	public static String solution(int n) {
		String answer = "";
		int r = n%3;
		r = (r==0)?4:(r==1?1:2);
		
		// TODO 1) 몫 구해서 124로 분기처리 & 수에 따라서 어떻게 처리할지...
		// 		2) 가능하면 재귀적 형태로 처리할 수 있도록...
		int q = n/3;
		q = (q==0)?4:(q==1?1:2);
		
		return answer;
	}

}
