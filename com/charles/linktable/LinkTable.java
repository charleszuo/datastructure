package com.charles.linktable;

public class LinkTable {
	private Node headPoint;

	public LinkTable(Node headPoint) {
		this.headPoint = headPoint;
	}

	public int size() {
		if (headPoint == null || headPoint.getNext() == null) {
			return 0;
		}

		int size = 0;
		Node point = headPoint.getNext();
		while (point != null) {
			size++;
			point = point.getNext();
		}
		return size;
	}

	public boolean addNode(Node node, int index) {
		if (index < 0 || headPoint == null) {
			return false;
		}

		Node point = headPoint;
		int i = 0;
		while (point.getNext() != null && i < index) {
			i++;
			point = point.getNext();
		}
		
		if(point.getNext() == null && i < index){
			return false;
		}

		node.setNext(point.getNext());
		point.setNext(node);

		return true;
	}

	public boolean deleteNode(int index) {
		Node point = getPreviousPoint(index);
		if (point == null) {
			return false;
		}
		Node removeNode = point.getNext();
		point.setNext(removeNode.getNext());
		removeNode.setNext(null);
		return true;
	}

	public Node find(String value) {
		if (headPoint == null || headPoint.getNext() == null) {
			return null;
		}
		Node point = headPoint.getNext();
		while (point != null) {
			if (value.equalsIgnoreCase(point.getValue())) {
				return point;
			}
			point = point.getNext();
		}
		return null;
	}

	public boolean updateNode(int index, String value) {
		Node point = getPreviousPoint(index);
		if (point == null) {
			return false;
		}

		point.getNext().setValue(value);
		return true;
	}

	public void print() {
		Node point = headPoint;
		while (point != null) {
			System.out.println(point.getValue());
			point = point.getNext();
		}
	}

	public void clear() {
		Node point, nextPoint;
		point = headPoint;

		while (point != null) {
			nextPoint = point.getNext();
			point.setNext(null);
			point = nextPoint;
		}

	}

	private Node getPreviousPoint(int index) {
		if (index < 0 || headPoint == null
				|| headPoint.getNext() == null) {
			return null;
		}

		Node point = headPoint;
		int i = 0;
		while (point.getNext() != null && i < index) {
			point = point.getNext();
			i++;
		}

		if (point.getNext() == null) {
			return null;
		}
		return point;
	}
}
