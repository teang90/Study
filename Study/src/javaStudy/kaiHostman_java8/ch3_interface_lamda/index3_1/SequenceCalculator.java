package javaStudy.kaiHostman_java8.ch3_interface_lamda.index3_1;

/** squence calculator -> intSeq, SquareSeq, etc...*/ 
public class SequenceCalculator {
	
	public static void main(String[] args) {
		int n = 100; // 평균의 모수
		double avg = 0 ; // 평균값
		
		// 사각형 100개 평균 구하기
		SquareSequence squareSeq = new SquareSequence(103);
		avg = getAverage(squareSeq, n) ;
		System.out.println("AVG1 = "+avg);
		
		DigitSequence digitSeq = new DigitSequence(12345678);
		avg = getAverage(digitSeq, n) ;
		System.out.println("AVG2 = "+avg);
	}
	
	/**
	 * IntSequence의 n개에 대한 평균을 구한다.
	 */
	public static double getAverage(IntSequence seq, int n) {
		double avg = 0 ;
		int count  = 0;
		while (seq.hasNext() && count < n) {
			count++;
			avg += seq.next();
		}
		return count==0?0:avg/count ;
	}
	
}
