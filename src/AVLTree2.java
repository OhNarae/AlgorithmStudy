
public class AVLTree2 {

	class Node{
		int value;
		int height;
		int leftHeight;
		int rightHeight;
		
		Node left;
		Node right;
		
		Node(int height, int value){
			this.value = value;
			this.height = height; 
			this.leftHeight = height;
			this.rightHeight = height;
			
			this.left = null;
			this.right = null;
		}
	}
	
	Node root; 
	
	public Node LL(Node node) {
		Node tmp = node.left; 		
		node.left = tmp.right; 
		tmp.right = node;
		
		System.out.println("LL");
		return tmp;
	}
	
	public Node RR(Node node) {
		Node tmp = node.right; 		
		node.right = tmp.left; 
		tmp.left = node;
		
		System.out.println("RR");
		return tmp;
	}
	
	public Node LR(Node node) {
		Node tmp = RR(node.left);
		node.left = tmp.right;
		tmp.right = node;
				
		System.out.println("LR");
		return tmp;
	}
	
	public Node RL(Node node) {
		Node tmp = LL(node.right);
		node.right = tmp.left; 
		tmp.left = node;
		
		System.out.println("RL");
		return tmp;
	}
	
	public void add(int value) {
		root = add(1, root, value);
	}
	
	public void resetHeight(Node node) {
		if(node == null) return;
		
		if(node.left == null && node.right == null) ; //rightHeight = leftHeight = height  
		else if(node.left == null) {
			 node.rightHeight = (node.right.leftHeight > node.right.rightHeight ? node.right.leftHeight : node.right.rightHeight);
		}else if(node.right == null) {
			node.leftHeight = (node.left.leftHeight > node.left.rightHeight ? node.left.leftHeight : node.left.rightHeight);
		}else {
			 node.rightHeight = (node.right.leftHeight > node.right.rightHeight ? node.right.leftHeight : node.right.rightHeight);
			 node.leftHeight = (node.left.leftHeight > node.left.rightHeight ? node.left.leftHeight : node.left.rightHeight);
		}
	}
	
	public Node add(int height, Node node, int value) {
		if(node == null) {
			node = new Node(height, value);
			return node;
		}else if(value < node.value){
			node.left = add(height + 1, node.left, value); //왼쪽 자식쪽으로 가기
		}else if(value > node.value) {
			node.right = add(height + 1, node.right, value); //오른쪽 자식쪽으로 가기			
		}		

		resetHeight(node);
		
		int heightDiff =  node.leftHeight - node.rightHeight;
		if(heightDiff > 1) { //left가 더 큰 경우
			int left_heightDiff =  node.left.leftHeight - node.left.rightHeight;
			if(left_heightDiff > 0)
				node = LL(node); //LL
			else
				node = LR(node); //LR
		}else if(heightDiff < -1 ) { //right가 더 큰 경우
			int right_heightDiff =  node.right.leftHeight - node.right.rightHeight;
			if(right_heightDiff > 0)
				node = RL(node); //RR
			else
				node = RR(node); //RL
		}
		
		return node;
	}
	
	public void print() {
		System.out.println("**tree print**");
		print(root);
	}
	
	public void print(Node node) {		
		System.out.println(node.value + " lh:" + node.leftHeight + " h:" + node.height + " rh:" + node.rightHeight);
		if(node.left != null) print(node.left);
		if(node.right != null)print(node.right);
	}
	
	public static void main(String[] args) {
		AVLTree2 tree = new AVLTree2();
		
		System.out.println("tree.add(15);");
		tree.add(15);
		tree.print();
		
		System.out.println();
		System.out.println("tree.add(7);");
		tree.add(7);
		tree.print();
		
		System.out.println();
		System.out.println("tree.add(67);");
		tree.add(67);
		tree.print();
		
		System.out.println();
		System.out.println("tree.add(35);");
		tree.add(35);
		tree.print();
		
		System.out.println();
		System.out.println("tree.add(99);");
		tree.add(99);
		tree.print();
		
		System.out.println();
		System.out.println("tree.add(50);");
		tree.add(50);
		tree.print();
	}

}
