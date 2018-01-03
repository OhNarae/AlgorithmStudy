
public class InsertionSort {

	static void InsertionSort(int[] data) {
		
		for(int i = 1, j ; i < data.length ; i++) {
			for(j = 0 ; j < i ; j++) {
				if(data[j] > data[i])
					break;
			}
			
			int data_i = data[i];
			for(int k = i ; k > j ; k--) {
				data[k] = data[k-1]; 
			}
			data[j] = data_i;
		}
	}
	
	public static void main(String[] args) {
		int data[] = { 12, 2, 7, 89, 100, 19, 22, 67};
		SortedLinkedList list = new SortedLinkedList();
		
		for(int i = 0 ; i < data.length ; i++) { 
			list.add(data[i]);
			System.out.print(data[i] + " ");
		}
		System.out.println();
		
		InsertionSort(data);
		
		list.printList();
	}

}
