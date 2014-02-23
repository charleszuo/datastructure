package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 直接插入排序，稳定的排序，时间复杂度O(n^2)
 * 把元素不断插入左边有序的序列的过程，如果元素比左边有序的元素小，就要移动位置，然后找到相应的位置插入
 */
public class InsertSort {
	public static void sort(int[] data){
		int length = data.length;
		int i, j;
		for(i = 1; i < length; i++){
			if(data[i] < data[i - 1]){
				// 哨兵
				int min = data[i];
				// 移动位置
				for(j = i; j > 0 && min < data[j-1]; j--){
					data[j] = data[j-1];
				}
				data[j] = min;
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
