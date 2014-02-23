package com.charles.search;

/**
 * 
 * @author charleszuo@126.com
 * 线性查找
 */
public class SequentialSearch {

	public static int search(int[] data, int key){
		for(int i = 0; i < data.length; i++){
			if(key == data[i]){
				return i;
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args){
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
		
		System.out.println(search(data, 70));
		System.out.println(search(data, 7));
	}
}
