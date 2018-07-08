package com.x.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TreeDemo {
	
	private Node root = null;
	
	private String[] data;
	
	public TreeDemo(){
//		root =  new Node(1,"A");
	}
	
	/**
	 * ����������
	 *         A
	 *     B       C
	 * D      E        F
	 */
	public void createBinaryTree(){
		Node nodeB = new Node(2, "B");
		Node nodeC = new Node(3, "C");
		Node nodeD = new Node(4, "D");
		Node nodeE = new Node(5, "E");
		Node nodeF = new Node(6, "F");
		root.leftChild = nodeB;
		root.rightChild = nodeC;
		nodeB.leftChild = nodeD;
		nodeB.rightChild = nodeE;
		nodeC.rightChild = nodeF;
	}
	
	
	/**
	 * ��������ĸ߶�
	 * @author Administrator
	 *
	 */
	public int getHeight(){
		return getHeight(root);
	}

	private int getHeight(Node root) {
		if (root == null) {
			return 0;
		}
		int i = getHeight(root.leftChild);
		int j = getHeight(root.rightChild);
		return i >= j ? i+1 : j+1;
	}
	
	/**
	 * ��ȡ�������Ľ����
	 * @author Administrator
	 *
	 */
	public int getSize(){
		return getSize(root);
	}
	
	
	private int getSize(Node node) {
		if(node == null){
			return 0;
		}else{
			return 1+getSize(node.leftChild)+getSize(node.rightChild);
		}
	}
	
	/**
	 * 
	  * @ClassName: Node
	  * @Description: TODO
	  * @author-csx
	  * @date 2018��4��22�� ����7:20:03
	  *
	 */
	public void preOrder(Node node){
		if (node == null) {
			return;
		}
		System.out.println(node.data);
		preOrder(node.leftChild);
		preOrder(node.rightChild);
	}
	
	/**
	 * ǰ����������ǵ���
	 */
	
	public void nonRecOrder(Node node){
		if(node == null){
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);
		while(!stack.isEmpty()){
			//��ջ�ͽ�ջ
			Node n = stack.pop();//���������
			// ��ջʱ��ӡ
			System.out.println("nonRecOrder :"+n.getData());
			//ѹ���ӽ��
			if(n.rightChild!=null){
				stack.push(n.rightChild);
				
			}
			if(n.leftChild!=null){
				stack.push(n.leftChild);
			}
		}
	}
	
	/**
	 * 
	  * @ClassName: Node
	  * @Description: ͨ����׼���봴��������
	  * @author-csx
	  * @date 2018��4��23�� ����8:24:51
	  *
	 */
	public Node generateTree(){
		Scanner sb = new Scanner(System.in);
		String data = sb.nextLine();
		if (data.equals("#")) {
			return null;
		}
		Node node = new Node(0,data);
		node.leftChild = generateTree();
		node.rightChild = generateTree();
		return node;
	}
	
	/**
	 * 
	  * @ClassName: Node
	  * @Description: ͨ���ַ�������������
	  * @author-csx
	  * @date 2018��4��23�� ����8:24:51
	  *
	 */
	public void generateTreeByString(String[] data){
		data = Arrays.copyOfRange(data, 1, data.length);
		ArrayList<String> datas = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			datas.add(data[i]);
		}
		generateTreeByString(data.length, datas);
	}
	
	public Node generateTreeByString(int size, ArrayList<String> data){
		if (size == 0) {
			return null;
		}
		String item = data.get(0);
		Node node;
		int index = size - data.size();
		if (item.equals("#")) {
			node = null;
			data.remove(0);
			return node;
		}
		node = new Node(index, item);
		if (index == 0) {
			root = node;
		}
		data.remove(0);
		node.leftChild = generateTreeByString(size, data);
		node.rightChild = generateTreeByString(size, data);
		return node;
	}
	
	public void generateTreeByStringDirect(String[] data){
		this.data = Arrays.copyOfRange(this.data, 1, this.data.length);
		generateTreeByString(this.data.length);
	}
	
	public Node generateTreeByString(int size){
		if (size == 0) {
			return null;
		}
		String item = this.data[0];
		Node node;
		int index = size - this.data.length;
		if (item.equals("#")) {
			node = null;
			data = Arrays.copyOfRange(this.data, 1, this.data.length);
			return node;
		}
		node = new Node(index, item);
		if (index == 0) {
			root = node;
		}
		data = Arrays.copyOfRange(this.data, 1, this.data.length);
		node.leftChild = generateTreeByString(size);
		node.rightChild = generateTreeByString(size);
		return node;
	}
	

	public class Node{
		private int index;
		private String data;
		private Node leftChild;
		private Node rightChild;
		
		public Node(int index, String data) {
			super();
			this.index = index;
			this.data = data;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

	}
	
	public static void main(String[] args) {
		TreeDemo td = new TreeDemo();
//		td.createBinaryTree();
		System.out.println(td.getHeight());
		System.out.println(td.getSize());
//		td.preOrder(td.root);
//		td.nonRecOrder(td.root);
//		td.root = td.generateTree();
		String value = "ABD##E##C#F##";
		String[] strings = value.split("");
		td.generateTreeByStringDirect(strings);
		td.preOrder(td.root);
	}
}
