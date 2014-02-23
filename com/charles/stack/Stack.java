package com.charles.stack;

public class Stack {
	private int elementCount;
	
	private Object[] elementData;
	
	public Stack(int capacity){
		elementData = new Object[capacity];
	}
	
	public void push(Object obj){
		addElement(obj);
	}
	
	public Object pop(){
		return removeElementAt(elementCount - 1);
	}
	
	public Object peek(){
		if(isEmpty()){
			return null;
		}
		return elementData[elementCount -1];
	}
	
	private void addElement(Object obj){
		elementData[elementCount ++] = obj;
	}
	
	public boolean isEmpty(){
		return elementCount == 0;
	}
	
	public Object removeElementAt(int index){
		if(index < 0 || index >= elementCount){
			return null;
		}
		Object element;
		element = elementData[index];
		int j = elementCount - 1 - index;
		System.arraycopy(elementData, index + 1, elementData, index, j);
		elementCount --;
		elementData[elementCount] = null;
		return element;
	}
	
	public void print(){
		for(int i = elementCount -1; i >=0; i--){
			System.out.println(elementData[i].toString());
		}
	}
}
