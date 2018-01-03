
public class BTree {

	class Node {
		int[] values;
		Node[] nodes;
		int count;
		boolean isLeaf;

		Node(int n, boolean isLeaf) {
			this.values = new int[n + 1];// 한칸을 더 만들어준다.
			this.nodes = new Node[n + 2];
			this.isLeaf = isLeaf;
			this.count = 0;
		}

		Node(int n, int value0, Node node0, Node node1) { // 자식 노드가 들어오는 것 자체로 leaf 노드가 아니다.
			this.values = new int[n + 1];// 한칸을 더 만들어준다.
			this.nodes = new Node[n + 2];

			this.values[0] = value0;
			this.nodes[0] = node0;
			this.nodes[1] = node1;

			this.isLeaf = false;
			this.count = 1;
		}
	}

	class PNode {
		int value;
		Node[] nodes;

		PNode(int value, Node left_node, Node right_node) {
			this.value = value;
			this.nodes = new Node[2];
			this.nodes[0] = left_node;
			this.nodes[1] = right_node;
		}

		PNode(int value) {
			this.value = value;
		}
	}

	Node root;
	int nodeCount;

	BTree(int n) {
		root = new Node(n, true);
		nodeCount = n;
	}

	void add(int value) {
		PNode pNode = add(root, value);
		if (pNode != null) {
			Node newNode = new Node(nodeCount, pNode.value, pNode.nodes[0], pNode.nodes[1]);
			root = newNode;
		}
	}

	PNode Divide(Node node) {
		Node newNode = new Node(nodeCount, node.isLeaf); // 새로운 노드 만들어서 뒤의 값을 복사
		System.arraycopy(node.values, nodeCount / 2 + 1, newNode.values, 0, nodeCount / 2);
		if (!node.isLeaf)
			System.arraycopy(node.nodes, nodeCount / 2 + 1, newNode.nodes, 0, nodeCount / 2 + 1);
		newNode.count = nodeCount / 2;
		PNode pNode = new PNode(node.values[nodeCount / 2], node, newNode); // 위로 올려보낼 값을 구성함
		node.count = nodeCount / 2; // 원래 노드의 다른 노드 들로 가는 값들을 비워줌

		return pNode;
	}

	PNode addToNode(Node node, int i, PNode addNode) {
		System.arraycopy(node.values, i, node.values, i + 1, node.count - i); // 추가될 value를 넣기 위해 뒤에 들어갈 값들을 한칸씩 미뤄줌
		if (!node.isLeaf)
			System.arraycopy(node.nodes, i + 1, node.nodes, i + 2, node.count - i); // 추가될 node를 넣기 위해 뒤에 들어갈 값들을 한칸씩
																					// 미뤄줌
		node.values[i] = addNode.value; // 값 추가
		if (!node.isLeaf)
			node.nodes[i] = addNode.nodes[0];
		if (!node.isLeaf)
			node.nodes[i + 1] = addNode.nodes[1];
		node.count++;
		if (node.count > nodeCount) {// Node가 넘친 경우
			return Divide(node);
		}

		return null;
	}

	PNode add(Node node, int value) {
		for (int i = 0; i <= node.count; i++) {
			if (value < node.values[i] || i == node.count) {
				if (node.isLeaf) { // leaf라면 해당 Node에 추가 작업을 한다.
					PNode pNode = new PNode(value);
					return addToNode(node, i, pNode);
				} else { // leaf가 아니라면 i번 째 자식노드로 내려간다.
					PNode pNode = add(node.nodes[i], value); 
					if (pNode != null) { //pNode에 현재 노드에 추가할 노드가 들어온다.
						return addToNode(node, i, pNode);
					}
				}
				return null;
			}
		}
		return null;
	}
	
	void remove(int value) {
		remove(root, value);
	}
	
	
	int searchMinValueInRight(Node node) {
		if(node.isLeaf == true) return node.values[0];
		else 
			return searchMinValueInRight(node.nodes[0]);
	}
	
	void remove(Node node, int value) {
		int node_count = node.count;
		for (int i = 0; i <= node_count; i++) {
			if(value == node.values[i]) { //값을 찾은 경우
				if(node.isLeaf) {
					//지운다.
					System.arraycopy(node.values, i+1, node.values, i, node.count - (i+1));
					node.count--;
					if(node.count < nodeCount/2) { //underflow 상황인 경우
						
					}
				}
				else { //대체 가능한 값을 찾아서 
					node.values[i] = searchMinValueInRight(node.nodes[i+1]);
					remove(node.nodes[i+1], node.values[i]);
				}
			}else if (value < node.values[i] || i == node.count) {
				remove(node.nodes[i], value);
			}
		}
	}

	void print() {
		System.out.println("\n** tree print**");
		print(root);
	}

	void print(Node node) {
		for (int i = 0; i < node.count; i++) {
			System.out.print(node.values[i] + " ");
		}

		if (!node.isLeaf) {
			for (int i = 0; i <= node.count; i++) {
				System.out.println();
				print(node.nodes[i]);
			}
		}
	}

	public static void main(String[] args) {
		BTree tree = new BTree(4);

		tree.add(1);
		tree.add(2);
		tree.add(3);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.add(10);
		tree.add(21);
		tree.add(12);
		tree.add(13);
		tree.add(14);
		tree.add(11);
		tree.add(15);
		tree.add(16);
		tree.add(17);
//		** tree print**
//		9 
//		3 6 
//		1 2 
//		4 5 
//		7 8 
//		13 16 
//		10 11 12 
//		14 15 
//		17 21 

		tree.print();
	}

}
