package com.charles.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * @author charleszuo@126.com
 * 赫夫曼树（最优二叉树）,权值高的结点在上面，权值低的结点在下面
 * 左孩子小于右孩子
 */
public class HuffmanTree {
	public void sortNode(List<Node> nodesQueue){
		Collections.sort(nodesQueue);
	}
	
	public Node buildHuffmanTree(List<Node> nodesQueue){
		while(nodesQueue.size() != 0){
			if(nodesQueue.size() == 1){
				break;
			}
			sortNode(nodesQueue);
			Node leftChild = nodesQueue.remove(0);
			Node rightChild = nodesQueue.remove(0);
			
			Node temp = new Node("Temp", leftChild.getWeight() + rightChild.getWeight());
			temp.setLeftChild(leftChild);
			temp.setRightChild(rightChild);
			
			nodesQueue.add(temp);
		}
		
		return nodesQueue.get(0);
	}
	
	public void preOrderTraverse(Node node){
		if(node == null){
			return;
		}
		System.out.println("Node " + node.getName() + " weight " + node.getWeight());
		preOrderTraverse(node.getLeftChild());
		preOrderTraverse(node.getRightChild());
	}
	
	public static void main(String[] args){
		Node A = new Node("A", 27);
		Node B = new Node("B", 8);
		Node C = new Node("C", 15);
		Node D = new Node("D", 15);
		Node E = new Node("E", 30);
		Node F = new Node("F", 5);
		Node G = new Node("G", 31);
		
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(A);
		nodes.add(B);
		nodes.add(C);
		nodes.add(D);
		nodes.add(E);
		nodes.add(F);
		nodes.add(G);
		
		HuffmanTree huffmanTree = new HuffmanTree();
		Node root = huffmanTree.buildHuffmanTree(nodes);
		huffmanTree.preOrderTraverse(root);
	}
}

class Node implements Comparable<Node>{
	private int weight;
	
	private String name;
	
	private Node leftChild;
	
	private Node rightChild;
	
	public Node(String name, int weight){
		this.name = name;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public int compareTo(Node o) {
		if(this.getWeight() > o.getWeight()){
			return 1;
		}
		
		return this.getWeight() == o.getWeight() ? 0 : -1;
	}
	
}