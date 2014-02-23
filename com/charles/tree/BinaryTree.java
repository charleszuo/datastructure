package com.charles.tree;

/** 
 * @author charleszuo@126.com
 * 二叉树
 */
public class BinaryTree {
	
	public BinaryTree(){
	}
	
	public static void preOrderTraverse(BinaryTreeNode root){
		if(root == null){
			return;
		}
		System.out.println(root.getValue());
		preOrderTraverse(root.getLeftChild());
		preOrderTraverse(root.getRightChild());
	}
	
	public static void inOrderTraverse(BinaryTreeNode root){
		if(root == null){
			return;
		}
		inOrderTraverse(root.getLeftChild());
		System.out.println(root.getValue());
		inOrderTraverse(root.getRightChild());
	}
	
	public static void postOrderTraverse(BinaryTreeNode root){
		if(root == null){
			return;
		}
		postOrderTraverse(root.getLeftChild());
		postOrderTraverse(root.getRightChild());
		System.out.println(root.getValue());
	}
	
	public static BinaryTreeNode preOrderCreateTree(char[] nodes){
		if(nodes.length == 0){
			return null;
		}
		
		BinaryTreeNode root = null;
		java.util.Stack<BinaryTreeNode> stack = new java.util.Stack<BinaryTreeNode>();
		int left = 1, right = 0;
		for(int i = 0; i< nodes.length; i++){
			char node = nodes[i];
			if(i == 0){
				root = new BinaryTreeNode(String.valueOf(node));
				stack.push(root);
			}else{
				BinaryTreeNode top = stack.peek();
				if(left == 1 && node != '#'){
					BinaryTreeNode leftChild = new BinaryTreeNode(String.valueOf(node));
					top.setLeftChild(leftChild);
					stack.push(leftChild);
				}else if(left == 1 && node == '#'){
					top.setLeftChild(null);
					left = 0;
					right = 1;
				}else if(right == 1 && node != '#'){
					BinaryTreeNode rightChild = new BinaryTreeNode(String.valueOf(node));
					top.setRightChild(rightChild);
					stack.push(rightChild);
					left = 1;
					right = 0;
				}else if(right == 1 && node == '#'){
					// 关键在这，如果右孩子是空，那么栈回退，直到下一个等待设置右孩子的结点。右孩子模式不变
					top.setRightChild(null);
					stack.pop();
					while(stack.size() > 0 && stack.peek().getRightChild() != null){
						stack.pop();
					}
				}
			}
			
		}
		
		return root;
	}
	
	public static void main(String[] args){
		BinaryTreeNode A = new BinaryTreeNode("A");
		BinaryTreeNode B = new BinaryTreeNode("B");
		BinaryTreeNode C = new BinaryTreeNode("C");
		BinaryTreeNode D = new BinaryTreeNode("D");
		BinaryTreeNode E = new BinaryTreeNode("E");
		BinaryTreeNode F = new BinaryTreeNode("F");
		BinaryTreeNode G = new BinaryTreeNode("G");
		BinaryTreeNode H = new BinaryTreeNode("H");
		BinaryTreeNode I = new BinaryTreeNode("I");
		
		A.setLeftChild(B);
		A.setRightChild(C);
		B.setLeftChild(D);
		D.setLeftChild(G);
		D.setRightChild(H);
		C.setLeftChild(E);
		C.setRightChild(F);
		E.setRightChild(I);
		
		System.out.println("------PreOrderTraverse--------------");
		preOrderTraverse(A);
		
		System.out.println("------InOrderTraverse--------------");
		inOrderTraverse(A);
		
		System.out.println("------PostOrderTraverse--------------");
		postOrderTraverse(A);
		
		System.out.println("------PreOrder Create--------------");
		BinaryTreeNode newRoot = preOrderCreateTree("AB#CD##E##F#G##".toCharArray());
		System.out.println("------PreOrderTraverse--------------");
		preOrderTraverse(newRoot);
		
		
	}
}
