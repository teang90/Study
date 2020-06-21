package algorithm.sorting;

public class Common {
	
	public int[] makeData(int size) {
		
		int[] data = new int[size];
		
		for (int i = 0; i < size; i++) {
			data[i] = (int) (Math.random()*100);
		}
		
		return data;
	}
	
}
