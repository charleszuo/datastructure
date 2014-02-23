package com.charles.stack;

public class Main {
	public static void main(String[] args){
		System.out.println(Fbi(0));
		System.out.println(Fbi(1));
		System.out.println(Fbi(2));
		System.out.println(Fbi(3));
		System.out.println(Fbi(4));
		System.out.println(Fbi(5));
		System.out.println(Fbi(6));
		System.out.println(Fbi(7));
		System.out.println(Fbi(8));
		System.out.println(Fbi(9));
		System.out.println(Fbi(10));
		System.out.println(Fbi(11));
		System.out.println(Fbi(12));
	}
	
	public static int Fbi(int i){
		if(i < 0){
			return 0;
		}
		return i < 2 ? i : Fbi(i - 1) + Fbi(i - 2);
	}
	
	public static void stack(){
		Stack stack = new Stack(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.print();
		System.out.println("--------");
		System.out.println(stack.pop());
		System.out.println("--------");
		stack.print();
	}
}
