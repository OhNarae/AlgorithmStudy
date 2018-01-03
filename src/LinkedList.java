public class LinkedList {
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

	public LinkedList() {
		head = null;
		size = 0;
	}

	// 해당 값을 가진 노드 추가
	public void add(int value) {
		Node newNode = new Node(value); // 새로운 노드 생성

		if (head == null)
			head = newNode;
		else {
			Node iNode = head;
			for (int i = 0; i < size - 1; i++) {
				iNode = iNode.next;
			}
			// 새로운 노드의 next가 현재 Head를 가리키도록 함
			iNode.next = newNode;
		}

		size++;
	}

	// 해당 값의 노드 삭제 (해당 값을 가진 노드가 없으면 아무 것도 삭제되지 않음)
	public void remove(int index) {

		if (index >= size)
			return; // index가 사이즈보다 작아야함

		if (index == 0) {
			head = head.next;
		} else {
			Node node = head;
			for (int i = 0; i < index - 1; i++) {
				node = node.next;
			}
			node.next = node.next.next;
		}
		size--;
	}

	// 해당 인덱스의 값을 가져옴
	public int get(int index) {
		Node node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.value;
	}

	// 현재 값을 순서대로 출력하고 size도 덤으로 출력한다.
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

	public static void main(String[] args) {
		LinkedList list = new LinkedList();

		list.add(12);
		list.add(1);
		list.add(32);

		list.printList();

		System.out.println("1번째 node's value : " + list.get(1));

		list.remove(0);

		list.printList();

		list.add(7);

		list.printList();
	}
}