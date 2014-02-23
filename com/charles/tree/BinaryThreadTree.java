package com.charles.tree;

/** 
 * @author charleszuo@126.com
 * 线索二叉树，充分利用了空间，把空的左子针指向前驱，空的右指针指向后继
 */
public class BinaryThreadTree {
	
	private BinaryThreadTreeNode preNode;
	
	public void threadBinaryTree(BinaryThreadTreeNode node){
		if(node == null){
			return;
		}
		
		threadBinaryTree(node.getLeftChild());
		
		if(node.getLeftChild() != null){
			node.setlTag(0);
		}else if(node.getLeftChild() == null){
			node.setLeftChild(preNode);
			node.setlTag(1);
		}
		
		if(preNode != null){
			if(preNode.getRightChild() == null){
				preNode.setRightChild(node);
				preNode.setrTag(1);
			}else{
				preNode.setrTag(0);
			}
		}
		
		preNode = node;
			
		threadBinaryTree(node.getRightChild());
	}
	
	public void inOrderTraverse(BinaryThreadTreeNode root){
		if(root == null){
			return;
		}
		inOrderTraverse(root.getLeftChild());
		System.out.println(root.getValue());
		inOrderTraverse(root.getRightChild());
	}
	
	public BinaryThreadTreeNode addHeadPoint(BinaryThreadTreeNode root){
		BinaryThreadTreeNode headPoint = new BinaryThreadTreeNode("HeadPoint");
		headPoint.setLeftChild(root);
		headPoint.setlTag(0);
		headPoint.setrTag(1);
		BinaryThreadTreeNode point = root;
		while(point.getLeftChild() != null){
			point = point.getLeftChild();
		}
		point.setLeftChild(headPoint);
		point.setlTag(1);
		
		point = root;
		while(point.getRightChild() != null){
			point = point.getRightChild();
		}
		point.setRightChild(headPoint);
		point.setrTag(1);
		headPoint.setRightChild(point);
		return headPoint;
	}
	
	private void printNode(BinaryThreadTreeNode node){
		System.out.println("ltag = " + node.getlTag() + " rtag = " + node.getrTag() + " Node = " + node.getValue());
	}
	
	public void inOrderThreadTraverse(BinaryThreadTreeNode root){
		BinaryThreadTreeNode headPoint = addHeadPoint(root);
		BinaryThreadTreeNode point = headPoint.getLeftChild();
		while(point != headPoint){
			while(point.getlTag() != 1){
				point = point.getLeftChild();
			}
			printNode(point);
			while(point.getrTag() == 1 && point.getRightChild() != headPoint){
				point = point.getRightChild();
				printNode(point);
			}
			point = point.getRightChild();
		}
	}
	
	public static void main(String[] args){
		BinaryThreadTreeNode A = new BinaryThreadTreeNode("A");
		BinaryThreadTreeNode B = new BinaryThreadTreeNode("B");
		BinaryThreadTreeNode C = new BinaryThreadTreeNode("C");
		BinaryThreadTreeNode D = new BinaryThreadTreeNode("D");
		BinaryThreadTreeNode E = new BinaryThreadTreeNode("E");
		BinaryThreadTreeNode F = new BinaryThreadTreeNode("F");
		BinaryThreadTreeNode G = new BinaryThreadTreeNode("G");
		BinaryThreadTreeNode H = new BinaryThreadTreeNode("H");
		BinaryThreadTreeNode I = new BinaryThreadTreeNode("I");
		
		A.setLeftChild(B);
		A.setRightChild(C);
		B.setLeftChild(D);
		D.setLeftChild(G);
		D.setRightChild(H);
		C.setLeftChild(E);
		C.setRightChild(F);
		E.setRightChild(I);
		
		BinaryThreadTree tree = new BinaryThreadTree();
		tree.threadBinaryTree(A);
		tree.inOrderThreadTraverse(A);
		
	}
}
