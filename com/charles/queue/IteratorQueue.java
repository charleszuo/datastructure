package com.charles.queue;

public class IteratorQueue {
	private final int MAX_SIZE = 6;
	
	private int[] elements;
	
	private int front;
	
	private int rear;
	
	public IteratorQueue(){
		elements = new int[MAX_SIZE];
		front = rear = 0;
	}
	
	public boolean enQueue(int element){
		if(isFull()){
			return false;
		}
		
		elements[rear] = element;
		rear = (rear + 1) % MAX_SIZE;
		return true;
	}
	
	public int deQueue(){
		if(isEmpty()){
			return -1;
		}
		int element = elements[front];
		front = (front + 1) % MAX_SIZE;
		return element;
	}
	
	public void print(){
		int index = front;
		while(index != rear){
			System.out.println(elements[index]);
			index = (index + 1) % MAX_SIZE;
		}
	}
	
	public boolean isEmpty(){
		return front == rear;
	}
	
	public boolean isFull(){
		return (rear + 1 - front) % MAX_SIZE == 0;
	}
	
	public int size(){
		return (rear - front + MAX_SIZE) % MAX_SIZE;
	}
}
