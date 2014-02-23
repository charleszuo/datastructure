package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 希尔排序，不稳定的排序，时间复杂度在O(nlogn) - O(n^2)之间
 * 是直接插入排序的改进，采用一定的步长把一个序列切分成小的序列，使小的序列局部有序，这样就构造了一个局部有序的序列。
 * 插入排序在局部有序的情况下和数据量小的集合下性能较高
 * 循环终止条件是increment = 1。最后一次循环的步长必须等于1，相当做一次直接插入排序，这时候已经是基本有序了，所以效率高
 */
public class ShellSort {
	public static void sort(int[] data){
		int length = data.length;
		int increment = length;
		int j = 0;
		while(increment > 1){
			// 步长
			increment = increment / 3 + 1;
			// 插入排序
			for(int i = increment; i < length; i++){
				if(data[i] < data[i - increment]){
					int min = data[i];
					for(j = i; j >= increment && min < data[j-increment];j-= increment){
						data[j] = data[j-increment];
					}
					data[j] = min;
				}
			}
		}
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
		
		sort(data);
		
		for(int i : data){
			System.out.println(i);
		}
	}
}
