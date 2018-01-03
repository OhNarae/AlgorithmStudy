
class Queue<T>{
	class Unit{
		T data;
		Unit pre;
		Unit next;
		Unit(T data){
			this.data = data;
			this.pre = null;
			this.next = null;
		}
	}//데이터를 담는 데이터 유닛 (LinkedList로 구현하기 위해)
	
	Unit head; //가장 앞의 데이터 Unit을 가리킴
	Unit tail; //가장 뒤의 데이터 Unit을 가리킴
	int size; // 현재 가지고 있는 데이터 수

	//초기화
	Queue(){
		head = null;
		tail = null;
		size = 0;
	}
	
	//데이터 넣기
	void push(T t) {
		Unit newUnit = new Unit(t);
		if(head == null) {
			head = tail = newUnit;
		}else {
			tail.next = newUnit;
			newUnit.pre = tail;
			tail = newUnit;
		}		
		
		size++;
	}
	
	//데이터 빼내오기
	T pop() {
		if(size == 0) return null;
		
		Unit unit = head;
		if(head == tail) {
			head = null;
			tail = null;
		}else {
			head.next.pre = null;
			head = head.next;
		}
		
		size--;
		
		return unit.data;
	}
	
	//큐의 front에 있는 내용을 가져옴(삭제안됨)
	T front(){
		if(size == 0) return null;

		return head.data;
	}
	
	//큐가 비어있는지
	boolean empty() {
		return size == 0;
	}
	
	//큐의 사이즈
	int size() {
		return size;
	}
	
	//큐의 내용 출력
	void print() {
		Unit unit = head;
		System.out.print("queue안의 내용 : ");
		for(int i = 0; i < size; i++) {
			System.out.print(unit.data + " ");
			unit = unit.next;
		}
		System.out.println();
	}
}

public class QueueTest {

	public static void main(String[] args) {
		Queue<Integer> queue = new Queue<>();

		queue.push(2);
		queue.push(9);
		queue.push(1);

		queue.print();

		System.out.println("\nqueue.getTop() : " + queue.front());
		queue.print();

		System.out.println("\nqueue.pop() 첫번째 : " + queue.pop());
		queue.print();

		System.out.println("\nqueue.pop() 두번째 : " + queue.pop());
		queue.print();

		System.out.println("\nqueue.pop() 세번째 : " + queue.pop());
		queue.print();

		System.out.println("\nqueue.isEmpty() : " + queue.empty());
	}
}
