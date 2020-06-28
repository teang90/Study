package algorithm.solutions.programmers.practice.Level2;

import java.util.Iterator;

/*
 * 수평 직선에 탑 N대를 세웠습니다. 모든 탑의 꼭대기에는 신호를 송/수신하는 장치를 설치했습니다. 
 * 발사한 신호는 신호를 보낸 탑보다 높은 탑에서만 수신합니다. 
 * 또한, 한 번 수신된 신호는 다른 탑으로 송신되지 않습니다.

	예를 들어 높이가 6, 9, 5, 7, 4인 다섯 탑이 왼쪽으로 동시에 레이저 신호를 발사합니다. 
	그러면, 탑은 다음과 같이 신호를 주고받습니다. 
	높이가 4인 다섯 번째 탑에서 발사한 신호는 높이가 7인 네 번째 탑이 수신하고, 
	높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 
	높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신합니다. 
	높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신할 수 없습니다.
 */
public class 탑_stack_Queue {
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		
//		int[] heights =  { 5, 4, 3, 2, 1}; 
		int[] heights = {1,5,3,6,7,6,5}; 
//		int[] heights = {3,9,9,3,5,7,2}; 
//		int[] heights = {6,9,5,7,4}; 
		int[] answer = sol.solution(heights);
		
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]);
		}
	}
	
}

class Solution {
	/**
	 * @param heights : 좌측부터 탑의 높이를 담은 배열 heights
	 * @return answer : 각 탑이 쏜 신호를 어느 탑에서 받았는지 기록한 배열
	 * 
	 * [제약사항]
	 * heights는 길이 2 이상 100 이하인 정수 배열입니다.
	 * 모든 탑의 높이는 1 이상 100 이하입니다.
	 * 신호를 수신하는 탑이 없으면 0으로 표시합니다.
	 * 
	 * TEST CASE)
	 * [6,9,5,7,4]		[0,0,2,2,4]
	 * [3,9,9,3,5,7,2]	[0,0,0,3,3,3,6]
	 * [1,5,3,6,7,6,5]	[0,0,2,0,0,5,6] 
	 * [5,4,3,2,1] 		[0,1,2,3,4]
	 */
    public int[] solution(int[] height) {
    	if (height.length < 2 || height.length > 100) return null; 
    	
        int[] answer = new int[height.length];
        for (int i = height.length - 1; i >= 0 ; i--) {
        	for (int j=i-1; j>=0 ; j--) {
        		if (height[j] > height[i]) {
					answer[i] = j+1;
					break;
				}
				
//				if (j==0) answer[i] = 0; 
			}
		}
        
        return answer;
    }
}

/* // 다른 사람풀이) 스택 사용해서 푼 문제
import java.util.*;

class Solution {
    class Tower {
        int idx;
        int height;

        public Tower(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        @Override
        public String toString() {
            return "idx : " + idx + " height : " + height;
        }
    }

    public int[] solution(int[] heights) {
        Stack<Tower> st = new Stack<>();
        int[] answer = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            Tower tower = new Tower(i + 1, heights[i]);
            int receiveIdx = 0;

            while (!st.isEmpty()) {
                Tower top = st.peek();

                if (top.height > tower.height) {
                    receiveIdx = top.idx;
                    break;
                }

                st.pop();
            }

            st.push(tower);
            answer[i] = receiveIdx;
        }

        return answer;
    }
}
*/