package algorithm.sorting.select;

import java.util.Arrays;

import algorithm.sorting.Common;

public class SelectSort {
	
	public static void main(String[] args) {
		Common comm = new Common();
		int[] data = comm.makeData(100);
		System.out.println("before sorting : "+Arrays.toString(data));
		
		selectSort(data);
		
		System.out.println("after  sorting : "+Arrays.toString(data));
	}
	
	// 선택 정렬
	public static void selectSort(int[] data) {
		
		long start = System.currentTimeMillis();
		
		//필요 변수
		int min ;
		int index = 0 ;
		
		for (int i = 0; i < data.length -1 ; i++) {
			int temp;
			min = data[i];
//			index = i;

			for (int j = i + 1 ; j < data.length; j++) {
				if (min > data[j]) {
					min = data[j];
					index = j;
				}
				
			}
			temp = data[i];
			data[i] = data[index];
			data[index] = temp;
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Running Time "+(end-start)/1000.0+"sec");
		
	}
		
	
}
