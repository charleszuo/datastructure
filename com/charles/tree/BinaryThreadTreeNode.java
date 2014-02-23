package com.charles.tree;

public class BinaryThreadTreeNode{
	
	protected String value;
	
	protected BinaryThreadTreeNode leftChild;
	
	protected BinaryThreadTreeNode rightChild;
	
	public BinaryThreadTreeNode(String value) {
		this.value = value;
	}

	protected int lTag;
	
	protected int rTag;

	public int getlTag() {
		return lTag;
	}

	public void setlTag(int lTag) {
		this.lTag = lTag;
	}

	public int getrTag() {
		return rTag;
	}

	public void setrTag(int rTag) {
		this.rTag = rTag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BinaryThreadTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryThreadTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryThreadTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryThreadTreeNode rightChild) {
		this.rightChild = rightChild;
	}
	
}
