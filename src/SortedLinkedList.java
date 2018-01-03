
public class SortedLinkedList {
	class Node {
		int value;
		Node next;

		Node(int value) {
			this.value = value;
			this.next = null;
		}
	}

	Node head;
	int size;

	public SortedLinkedList() {
		head = null;
		size = 0;
	}

	// 해당 값을 가진 노드 추가
	public void add(int value) {
		Node newNode = new Node(value); // 새로운 노드 생성
		
		if(head == null)
			head = newNode;
		else {
			Node preNode = head;
			Node node = head;
			for(int i = 0 ; i < size ; i++) {
				if(value < node.value) { //새로 들어가는 값이 현재 노드에 비해 값이 작은 경우
					newNode.next = node; //새로운 노드의 next = 현재 노드
					if(node == head) {
						head = newNode;
					}else {
						preNode.next = newNode;
					}
					break;
				}else if(node.next == null) {
					node.next = newNode;
					break;
				}
				
				if(node != head) preNode = preNode.next; 
				node = node.next;				
			}			
		}

		size++;
	}

	// 해당 값의 노드 삭제 (해당 값을 가진 노드가 없으면 아무 것도 삭제되지 않음)
	public void remove(int value) {
		Node preNode = head;
		Node node = head;
		int pre_size = size;
		for (int i = 0; i < pre_size; i++) {
			if (node.value == value) {
				if (node == head) { // 첫노드
					head = node.next;
				} else {
					preNode.next = node.next;
				}
				size--;
			}
			if (head != node)
				preNode = preNode.next;
			node = node.next;
		}
	}

	// 해당 인덱스의 값을 가져옴
	public int get(int index) {
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.value;
	}

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

	public int getSize() {
		return size;
	}
}
