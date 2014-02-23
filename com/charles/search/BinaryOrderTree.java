package com.charles.search;

/**
 * 
 * @author charleszuo@126.com 二叉排序树
 * 二叉排序树是动态表查询的常用技术，便于增加，删除结点
 * 插入新结点时要先搜索，找到相应的插入位置。删除时要判断是否有左右孩子
 * 左孩子 < 父亲 < 右孩子
 */

public class BinaryOrderTree {
	
	// 如果没有找到，就返回最后找的那个结点（空结点）的父结点，如果找到就返回那个结点
	public static BinaryTreeNode search(BinaryTreeNode node, int key, BinaryTreeNode parent) {
		if(node == null){
			return parent;
		}
		
		if(key == node.getData()){
			return node;
		}else if(key < node.getData()){
			return search(node.getLeftChild(), key, node);
		}else{
			return search(node.getRightChild(), key, node);
		}
	}

	public static BinaryTreeNode insert(BinaryTreeNode node, int key) {
		BinaryTreeNode insertNode = new BinaryTreeNode(key);
		BinaryTreeNode point = search(node, key, node);
		// 没找到key, point指向最后一个找的结点
		if(point != null && point.getData() == key){
			return null;
		}
		
		if(point == null){
			node = insertNode;
		}else if(key < point.getData()){
			point.setLeftChild(insertNode);
		}else{
			point.setRightChild(insertNode);
		}
		
		return node;
	}
	
	public static void deleteBST(BinaryTreeNode node, BinaryTreeNode parent, int key){
		if(node == null){
			return;
		}
		if(key == node.getData()){
			delete(node, parent);
		}else if(key < node.getData()){
			deleteBST(node.getLeftChild(), node, key);
		}else{
			deleteBST(node.getRightChild(), node, key);
		}
	}
	
	public static void delete(BinaryTreeNode node, BinaryTreeNode parent){
		// 没有右孩子
		if(node.getRightChild() == null && node == parent.getLeftChild()){
			parent.setLeftChild(node.getLeftChild());
		}else if(node.getRightChild() == null && node == parent.getRightChild()){
			parent.setRightChild(node.getLeftChild());
		}
		// 没有左孩子
		else if(node.getLeftChild() == null && node == parent.getLeftChild()){
			parent.setLeftChild(node.getRightChild());
		}else if(node.getLeftChild() == null && node == parent.getRightChild()){
			parent.setRightChild(node.getRightChild());
		}
		// 既有左孩子，又有右孩子,就用它的中序遍历的前驱结点代替它，然后修改相应指针
		else if(node.getLeftChild() != null && node.getRightChild() != null){
			BinaryTreeNode q = node, s = node.getLeftChild();
			while(s.getRightChild() != null){
				q = s; 
				s = s.getRightChild();
			}
			node.setData(s.getData());
			if(q != node){
				q.setRightChild(s.getLeftChild());
			}else{
				q.setLeftChild(s.getLeftChild());
			}
		}
	}
	
	public static void inOrderTraverse(BinaryTreeNode node){
		if(node == null){
			return;
		}
		inOrderTraverse(node.getLeftChild());
		System.out.println(node.getData());
		inOrderTraverse(node.getRightChild());
	}
	
	public static void main(String[] args) {
	
		int[] data = new int[9];
		data[0] = 50;
		data[1] = 10;
		data[2] = 90;
		data[3] = 30;
		data[4] = 70;
		data[5] = 40;
		data[6] = 80;
		data[7] = 60;
		data[8] = 20;
		
		BinaryTreeNode root = null;
		for(int i = 0; i < data.length; i++){
			root = insert(root, data[i]);
		}
		inOrderTraverse(root);
		System.out.println("------------------");
		deleteBST(root, root, 50);
		inOrderTraverse(root);
	}
}

class BinaryTreeNode {
	private int data;
	
	private BinaryTreeNode leftChild;
	
	private BinaryTreeNode rightChild;

	public BinaryTreeNode(int data){
		this.data = data;
	}
	
	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BinaryTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode rightChild) {
		this.rightChild = rightChild;
	}

}
