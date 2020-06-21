package algorithm.sorting.quickSort;

import java.util.Arrays;

public class QuickSort {

	// 퀵 정렬 수행하는 메소드
	public static void quickSort(int[] data, int x, int y) {

//	
//		int pivot = (start+end)/2;
//		int left = start;
//		int right = end;
//		
//		while (left <= right){
//			
//			while(input[left] < input[pivot]){
//				left++;
//			} 
//			
//			while(input[right] > input[pivot]){
//				right--;
//			}
//			
//			// 엇갈리기 전 패스과정
//			if(left <= right){    
//				int temp = input[right];
//				input[right] = input[left];
//				input[left] = temp;
//				left++;
//				right--;
//			}
//		}
		if (x >= y) { // 원소가 1개인 경우
			return;
		}
		int pivot = x;
		int left = pivot + 1;
		int right = y;
		int temp;
		while (left < right) {
			while (left <= y && data[left] < data[pivot]) {
				left++;
			}
			while (right >= pivot && data[pivot] < data[right]) {
				right--;
			}
			if (left < right) {
				temp = data[left];
				data[left] = data[right];
				data[right] = temp;
			} else {
				temp = data[pivot];
				data[pivot] = data[right];
				data[right] = temp;
			}
			quickSort(data, x, right - 1);
			quickSort(data, right + 1, y);
//			quick_sort(data, x, right - 1);
//			quick_sort(data, right + 1, y);
		}


	}

	public static void main(String[] args) {

//		int input[] = {1, 5, 6, 3, 2, 4};
		int[] input = { 14, 1, 10, 5, 8, 7, 16, 13, 6, 4, 3, 12, 2, 9, 11, 15 };
//		int[] input = {10, 3, 5, 2, 4, 7, 9, 8, 1, 6};
		System.out.println("quick sort start : " + Arrays.toString(input));

		int number = input.length;
		quickSort(input, 0, number - 1);

		System.out.println("quick sort end : " + Arrays.toString(input));

	}

}
