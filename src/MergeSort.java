import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {

	public static int[] Merge_hard(int data[], int startIndex, int endIndex) {
		int length = endIndex - startIndex + 1;
		int sortedData[] = new int[length]; 
		
		if(length == 1) {
			sortedData[0] = data[startIndex];
		}else {
			int a_array[] = Merge(data, startIndex, startIndex + (length/2) - 1);
			int b_array[] = Merge(data, startIndex + (length/2), endIndex);
			
			int s_index = 0;
			int a_index = 0;
			for(int i = 0; i < b_array.length ; i++) {
				boolean j_over_a_array = true;
				for(int j = a_index; j < a_array.length ; j++) {
					if(b_array[i] < a_array[j]) {
						System.arraycopy(a_array, a_index, sortedData, s_index, j - a_index);
						s_index += j - a_index;
						a_index = j;
						sortedData[s_index++] = b_array[i];
						
						j_over_a_array = false;
						break;
					}
				}
				
				if(j_over_a_array) {
					System.arraycopy(a_array, a_index, sortedData, s_index, a_array.length - a_index);
					s_index += a_array.length - a_index;
					a_index = a_array.length;
					System.arraycopy(b_array, i, sortedData, s_index, b_array.length - i);
					break;
				}
			}
			
			if(a_index < a_array.length) {
				System.arraycopy(a_array, a_index, sortedData, s_index, a_array.length - a_index);
			}				
		}		

		return sortedData;
	}
	
	public static int[] Merge(int data[], int startIndex, int endIndex) {
		int length = endIndex - startIndex + 1;
		int sortedData[] = new int[length]; 
		
		if(length == 1) {
			sortedData[0] = data[startIndex];
		}else {
			//반씩 나눠서 정렬된 결과를 받아온다.
			int a_array[] = Merge(data, startIndex, startIndex + (length/2) - 1); 
			int b_array[] = Merge(data, startIndex + (length/2), endIndex);

			int i = 0; //a_array의 인덱스
			int j = 0; //b_array의 인덱스
			int k = 0; //sortedData의 인덱스
			while(true) {
				//더 작은 값을 sortedData 배열에 넣어준다. 
				if(a_array[i] > b_array[j]) sortedData[k++] = b_array[j++]; 
				else sortedData[k++] = a_array[i++];
				
				//한개의 배열이라도 다 쓰면 빠져나온다. 
				if(i == a_array.length || j == b_array.length) break;
			}
			
			//나머지 남은 배열을 뒤에 붙인다.
			System.arraycopy(a_array, i, sortedData, k, a_array.length - i);
			System.arraycopy(b_array, j, sortedData, k, b_array.length - j);
		}
		
		return sortedData;
	}
	
	public static void main(String[] args) {
		int data[] = { 12, 2, 7, 89, 100, 19, 22, 67};
		
		for(int i = 0 ; i < data.length ; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
		
		int result[] = Merge(data, 0, data.length-1);
		
		for(int i = 0 ; i < data.length ; i++) {
			System.out.print(result[i] + " ");
		}
	}

}
