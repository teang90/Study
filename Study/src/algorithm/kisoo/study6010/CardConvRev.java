package algorithm.kisoo.study6010;

/** 기수 변환을 수행하는 프로그램  */
public class CardConvRev {
	/** 정숫값 x를 r진수로 변환하여 배열 d의 아랫자리부터 넣어두고 자릿수를 반환한다. */
	static int cardConvR(int x, int r, char[] d) {
		int digits = 0 ; // 변환 후 자릿수
		String dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

//		do {
//			d[digits++] = dchar.charAt(x % r);
//			x /= r;
//		}while(x != 0);
		
		while (x != 0) {
			d[digits++] = dchar.charAt(x % r);
			x = x/r;
		}
		
		return digits; // r진수로 변환했을때 x의 자리수
	}
	
	public static void main(String[] args) {
		char[] d = new char[100];
		int result = cardConvR(0, 3, d);
		System.out.println(result);
	}
	
}
