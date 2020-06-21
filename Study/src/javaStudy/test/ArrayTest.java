package javaStudy.test;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayTest {
	
	static int[] pocket  = new int[4];
	static String input ;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		pocket = test(repeatGetInput(scan));
		
		System.out.println("RESULT : "+Arrays.toString(pocket));
		
		System.out.println("re? Y or N");
		input = scan.nextLine();
		
		if (input.equalsIgnoreCase("y")) {
			pocket = test(repeatGetInput(scan));
			
			System.out.println("RESULT : "+Arrays.toString(pocket));
		}
	}
	
	public static String[] repeatGetInput(Scanner scan) {

		System.out.println("*** 숫자 입력");
		
		input = scan.nextLine();
		String[] inputArray  = input.split(" ");
		
		return inputArray;
	}
	
	
	public static int[] test(String[] input){
		try {

//			pocket = new int[input.length];
			
			
			for (int i = 0; i < pocket.length; i++) {
				pocket[i] = Integer.parseInt(input[i]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pocket;
		
	}
	
}
