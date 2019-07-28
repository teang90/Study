package algorithm.solutions.programmers;

import java.util.ArrayList;
import java.util.List;

public class Level1_같은_숫자는_싫어 {
//	[Level 1] 같은 숫자는 싫어
//
//			문제
//				배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 
//				이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 
//				배열 arr에서 제거 되고 남은 수들을 return하는 solution함수를 완성해주세요. 
//				단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원서들의 순서를 유지해야 합니다.
//	
//				예를 들면
//				- arr = [1,1,3,3,0,1,1]이면 [1,3,0,1]을 return합니다.
//	
//				- arr = [4,4,4,3,3]이면 [4,3]을 return합니다.
//					배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return하는 solution함수를 완성해주세요.
//	
//			제한 사항
//				- 배열 arr의 크기: 1,000,000 이하의 자연수
//				- 배열 arr의 원소의 크기: 0보다 크거나 같고 9부더 작거나 같은 정수

	
	
	public static void main(String[] args) {
		int[] input = {1,1,3,3,0,1,1};
//		List<Integer> resList = solution(input);
//		int[] result = new int[resList.size()];
		int[] result = solution(input);
	}
	
	public static int[] solution(int[] input) {
		List<Integer> resList = new ArrayList<Integer>();
		for (int i = 0; i < input.length; i++) {
			// 해당 리스트의가장마지막 인덱스를 찾기 위한 코드, 원래 list.get(i-1)로 비교했을 했음,
			// 근데 값이 없는 인덱스와 비교하는 경우 발생 
			// ex) index[n-1] = input[n], but n-1번 인덱스는 아무것도 없는 요소임
			if (i != 0 && resList.get(resList.size()-1) == input[i]) continue; 
			resList.add(input[i]);
		}
		int[] result = new int[resList.size()];
		for (int i = 0; i < resList.size(); i++) {
			result[i] = resList.get(i);
		}
		return result;
	}
}

// 좋아요 가장많이 받은 코드
// public class Solution {
//    public int[] solution(int []arr) {
//        ArrayList<Integer> tempList = new ArrayList<Integer>();

//        ★중요부분★
//		int preNum = 10;
//        for(int num : arr) {
//            if(preNum != num)
//                tempList.add(num);
//        	  preNum = num;
//        }     
//		★중요부분★
//        int[] answer = new int[tempList.size()];
//        for(int i=0; i<answer.length; i++) {
//            answer[i] = tempList.get(i).intValue();
//        }
//        return answer;
//    }
//}

