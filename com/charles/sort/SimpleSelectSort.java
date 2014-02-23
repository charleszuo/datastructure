package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 简单选择排序，稳定的排序，时间复杂度O(n^2)
 * 每次选择最左侧未排序的元素作为起点，找出剩下元素中最小的元素的位置，然后再交换。一趟排序只交换一次
 */
public class SimpleSelectSort {
	
	public static void sort(int[] data){
		int length = data.length;
		for(int i = 0; i < length -1; i++){
			int min = i;
			for(int j = i + 1; j < length; j++){
				if(data[j] < data[min]){
					min = j;
				}
			}
			swap(data, i, min);
		}
	}
	
	private static void swap(int[] data, int i, int j){
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
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
