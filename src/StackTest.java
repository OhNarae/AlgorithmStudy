//
//class Stack<T>{
//	T[] data; //데이터
//	int cnt; //현재 가지고 있는 데이터 수
//	int max; // 최대로 가질 수 있는 데이터 수
//	
//	//초기화
//	Stack(int max){ 
//		this.data = (T[])new Object[max];
//		this.cnt = 0;
//		this.max = max;
//	}
//	
//	//데이터 넣기
//	void push(T t) {	
//		if(cnt == max) return;
//		
//		data[cnt] = t;
//		cnt++;
//	}
//	
//	//데이터 빼내오기
//	T pop() {
//		if(cnt == 0) return null;
//		
//		T pop = data[cnt-1];
//		data[cnt - 1] = null;
//		cnt--;
//		return  pop;
//	}
//	
//	//스택이 비어있는지
//	boolean isEmpty() {
//		return cnt == 0;
//	}
//	
//	//스택의top에 있는 내용을 가져옴(삭제안됨)
//	T getTop() {
//		if(cnt == 0) return null;
//		
//		return  data[cnt-1];
//	}
//	
//	//스택의 내용 출력
//	void print(){
//		System.out.println("현재 스텍의 모습");
//		for(int i = 0 ; i < cnt ; i++) {
//			System.out.print(data[i] + " ");			
//		}
//		System.out.println();
//	}
//}

class Stack<T> {
	class Unit {
		T data;
		Unit pre;
		Unit next;

		Unit(T t) {
			data = t;
			pre = null;
			next = null;
		}
	}

	Unit head; // 데이터
	Unit tail;
	int cnt; // 현재 가지고 있는 데이터 수

	// 초기화
	Stack(int max) {
		this.head = null;
		this.tail = null;
		this.cnt = 0;
	}

	// 데이터 넣기
	void push(T t) {
		Unit newUnit = new Unit(t);
		if (head == null) {
			head = tail = newUnit;
		} else {
			newUnit.pre = tail;
			tail.next = newUnit;
			tail = newUnit;
		}		
		cnt++;
	}

	// 데이터 빼내오기
	T pop() {
		if (cnt == 0)
			return null;

		Unit pop = tail;
		if (cnt == 1) {
			head = null;
			tail = null;
		} else {
			tail.pre.next = null;
			tail = tail.pre;
		}
		cnt--;
		return pop.data;
	}

	// 스택이 비어있는지
	boolean isEmpty() {
		return cnt == 0;
	}

	// 스택의top에 있는 내용을 가져옴(삭제안됨)
	T getTop() {
		if (cnt == 0)
			return null;

		return tail.data;
	}

	// 스택의 내용 출력
	void print() {
		System.out.println("현재 스텍의 모습");
		Unit unit = head;
		while(unit != null) {
			System.out.print(unit.data + " ");
			unit = unit.next;
		}
		System.out.println();
	}
}

public class StackTest {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>(10);

		stack.push(2);
		stack.push(9);
		stack.push(1);

		stack.print();

		System.out.println("\nstack.getTop() : " + stack.getTop());
		stack.print();

		System.out.println("\nstack.pop() 첫번째 : " + stack.pop());
		stack.print();

		System.out.println("\nstack.pop() 두번째 : " + stack.pop());
		stack.print();

		System.out.println("\nstack.pop() 세번째 : " + stack.pop());
		stack.print();

		System.out.println("\nstack.isEmpty() : " + stack.isEmpty());
	}

}
