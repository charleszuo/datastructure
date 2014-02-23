package com.charles.queue;

public class Main {
	public static void main(String[] args){
		IteratorQueue queue = new IteratorQueue();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		if(queue.enQueue(5)){
			System.out.println("Enqueue");
		}else{
			System.out.println("Queue is full!");
		}
		
		queue.print();
		System.out.println("-----------");
		queue.deQueue();
		queue.print();
		System.out.println("-----------");
		queue.deQueue();
		queue.print();
	}
}
