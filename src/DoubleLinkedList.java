public class DoubleLinkedList {
	class Node {
		int value;
		Node pre;
		Node next;

		Node(int value) {
			this.value = value;
			this.pre = null;
			this.next = null;
		}
	}

	Node head;
	Node tail;
	int size;

	public DoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	// 해당 값을 가진 노드 추가
	public void add(int value) {
		Node newNode = new Node(value); // 새로운 노드 생성
		
		if(head == null) { //처음 추가되는 노드인 경우
			head = tail = newNode;
		}else { 
			tail.next = newNode; 
			newNode.pre = tail;
			tail = newNode;
		}
		size++;
	}

	// 해당 인덱스의 노드 삭제 
	public void remove(int index) {
		if (index >= size)
			return; // index가 사이즈보다 작아야함

		if(size == 1) {
			head = tail = null;			
		}else if(index == 0) {
			head = head.next;
			head.pre = null;
		}else if(index == size-1) {
			tail = tail.pre;
			tail.next = null;
		}else {
			Node node = head;
			for (int i = 0; i < index - 1; i++) {
				node = node.next;
			}
			node.next = node.next.next;
			node.next.pre = node;		
		}
		
		size--;
	}
	
	//해당 인덱스가 가진 값을 가져옴
	public int get(int index) {
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.value;
	}

	//전체 리스트를 출력
	public void printList() {
		Node node = head;
		for (int i = 0; i < size; i++) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
		System.out.println("size : " + size);
		System.out.println();
	}

	//사이즈를 가져옴
	public int getSize() {
		return size;
	}

	public static void main(String[] args) {
		DoubleLinkedList list = new DoubleLinkedList();

		list.add(12);
		list.add(1);
		list.add(32);
		list.add(7);
		list.add(89);

		list.printList();

		System.out.println("1번째node's value: " + list.get(1));

		list.remove(0);		
		System.out.println("remove(0)");
		list.printList();	
		
		list.remove(2);		
		System.out.println("remove(2)");
		list.printList();
		
		
		list.remove(list.getSize()-1);		
		System.out.println("remove(list.getSize()-1)");
		list.printList();
		
		list.add(99);		
		System.out.println("list.add(99);");
		list.printList();
	}
}
