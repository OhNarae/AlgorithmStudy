
public class BinarySearchTree<T> {

	class Node {
		T value;
		Node left;
		Node right;

		Node(T value) {
			this.value = value;
		}
	}

	Node root;

	public int compare(Object value1, Object value2) { 
		//value값이 int로 변환 가능하다고 가정하에. 
		// 이부분만 수정하면 다른 자료형도 가능함.
		return (int) value1 - (int) value2;
	}

	public Node add(Node node, T value) {
		if (node == null) {
			node = new Node(value);
		} else {
			int compareV = compare(node.value, value);
			if (compareV > 0) {
				node.left = add(node.left, value);
			} else { // compareV < 0
				node.right = add(node.right, value);
			}
		}
		return node;
	}

	public Node search(Node node, T value) {
		int compareV = compare(node.value, value);
		if (compareV == 0)
			return node; // 값이 중복됨으로 트리에 삽입 안함
		else if (compareV > 0) {
			return search(node.left, value);
		} else { // compareV < 0
			return search(node.right, value);
		}
	}

	public Node searchMinNode(Node node) {
		if (node.left == null) {
			return node;
		} else {
			return searchMinNode(node.left);
		}
	}

	public Node remove(Node node, T value) {
		if (compare(value, node.value) < 0) { // value가 node 값보다 작은 경우 left로 찾아 들어간다.
			node.left = remove(node.left, value);
		} else if (compare(value, node.value) > 0) { // value가 node 값보다 큰 경우 right로 찾아 들어간다.
			node.right = remove(node.right, value);
		} else { // 삭제할 노드를 찾음!
			if (node.left == null && node.right == null) { // 해당 node에 자식이 없는 경우
				node = null;
			} else if (node.left == null) { // node.right는 not null인 경우
				node = node.right;
			} else if (node.right == null) { // node.left는 not null인 경우
				node = node.left;
			} else { // 양쪽 자식이 다 있을 경우
				Node minNode = searchMinNode(node.right);
				node.value = minNode.value;
				remove(minNode, minNode.value);
			}			
		}
		return node;
	}

	public void printInOrder(Node node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(node.value + " ");
			printInOrder(node.right);
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.root = tree.add(tree.root, 20);
		tree.printInOrder(tree.root);
		System.out.println();

		tree.root = tree.add(tree.root, 12);
		tree.printInOrder(tree.root);
		System.out.println();

		tree.root = tree.add(tree.root, 31);
		tree.printInOrder(tree.root);
		System.out.println();
		
		BinarySearchTree.Node node = tree.search(tree.root, 12);
		System.out.print("잘 찾은 노드값 : " + node.value);
		System.out.println();
		
		tree.root = tree.remove(tree.root, 12);
		tree.printInOrder(tree.root);
	}

}
