package com.x.tree;


/**
  * @ClassName: SearchBinaryTree
  * @Description: �������Ҷ���������ӡ������
  * @author-csx
  * @date 2018��5��5�� ����3:31:10
  *
  */
public class SearchBinaryTree {
	
	private Node root;
	
	public SearchBinaryTree(){
		
	}
	
	public static void main(String[] args) {
		SearchBinaryTree bt = new SearchBinaryTree();
		int[] data = {50,22,80,53,32,68,70};
		for (int i : data) {
			bt.put(i);
		}
		bt.midOrderPrint(bt.root);
	}
	
	/**
	  *
	  * @Title: midPrint
	  * @Description: ʹ�����������ӡ�²��Ҷ�����������С�����ӡ
	  * @return void    ��������
	  * @throws
	  */
	public void midOrderPrint(Node node){
		if (node == null) {
			return;
		}
		midOrderPrint(node.leftChild);
		System.out.println(node.data);
		midOrderPrint(node.rightChild);
	}

	
	public Node put(int data){
		Node node = null;
		Node parent = null;
		if (root == null) {
			node = new Node(0, data);
			root = node;
			return root;
		}
		node = root;
		while (node != null) {
			parent = node;
			if (data > node.data) {
				node = node.rightChild;
			} else if (data < node.data) {
				node = node.leftChild;
			} else {
				return node;
			}
		}
		
		// nodeΪ�գ�˵�������û��������ӽ��
		node = new Node(0, data);
		if (data > parent.data) {
			parent.rightChild = node;
		}else {
			parent.leftChild = node;
		}
		return node;
	}
	
	
	public class Node{
		private int key;
		private int data;
		private Node leftChild;
		private Node rightChild;
		private Node parent;
		
		public Node(int key, int data) {
			super();
			this.key = key;
			this.data = data;
		}

		public int getIndex() {
			return key;
		}

		public void setIndex(int index) {
			this.key = index;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
		

	}

}
