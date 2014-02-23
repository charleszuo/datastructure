package com.charles.linktable;

public class Main {
	public static void main(String[] args){
		singleLinkTable();
	}
	
	public static void singleLinkTable(){
		Node headPoint = new Node("Head Point");
		Node firstNode = new Node("First Node");
		Node secondNode = new Node("Second Node");
		Node thirdNode = new Node("Third Node");
		
		headPoint.setNext(firstNode);
		firstNode.setNext(secondNode);
		secondNode.setNext(thirdNode);
		thirdNode.setNext(null);
		
		LinkTable linkTable = new LinkTable(headPoint);
		System.out.println(linkTable.size());
		linkTable.print();
//		linkTable.clear();
//		System.out.println(linkTable.size());
//		linkTable.print();
		
		Node fourthNode = new Node("Fourth Node");
		linkTable.addNode(fourthNode, 4);
		linkTable.print();
		
		linkTable.deleteNode(1);
		linkTable.print();
		
		linkTable.updateNode(1, "I'm updated value");
		linkTable.print();
		
		Node findNode = linkTable.find("Third Node");
		if(findNode != null){
			System.out.println("Found");
		}else{
			System.out.println("Not Found");
		}
	}
}
