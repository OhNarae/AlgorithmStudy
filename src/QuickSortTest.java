
public class QuickSortTest {

	static void quickSort(int data[], int start, int end) {
		if(start < end) {
			int p = patition(data, start, end);
			quickSort(data, start, p);
			quickSort(data, p + 1, end);
		}
	}
	
	static int patition(int data[], int start, int end) {
		int pivot = data[start];
		int i = start - 1;
		int j = end + 1;
		while(true) {
			do { i++; }while(data[i] < pivot);
			do { j--; }while(data[j] > pivot);
			
			if(i >= j) {
				return j;
			}
			
			int tmp = data[i];
			data[i] = data[j];
			data[j] = tmp;
		}
	}
	
	public static void main(String[] args) {
		int data[] = { 12, 2, 7, 89, 100, 19, 22, 67};
		
		for(int i = 0 ; i < data.length ; i++) { 
			System.out.print(data[i] + " ");
		}
		System.out.println();
		
		quickSort(data, 0, data.length-1);
		
		for(int i = 0 ; i < data.length ; i++) { 
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

}
