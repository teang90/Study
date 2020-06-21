package javaStudy.kaiHostman_java8.ch3_interface_lamda.index3_1;

public class SquareSequence implements IntSequence{
	private int i;
	
	public SquareSequence() {
	}
	
	public SquareSequence(int n) {
		i = n;
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}
	
	@Override
	public int next() {
		i++;
		return i*i;
	}
	
}

class DigitSequence implements IntSequence {
	private int number = 0;
	private int resetNum = 0;

	{	resetNum = number;	}

	public DigitSequence() {}
	
	public DigitSequence(int number) {
		this.number = number;
	}
	
	@Override
	public boolean hasNext() {
		return number!=0;
	}
	
	@Override
	public int next() {
		int result = number%10; 
		number /= 10;
		return result;
	}
	
	public int reset() {
		return resetNum;
	}
	
	public int remainNum() {
		return number;
	}
	
}