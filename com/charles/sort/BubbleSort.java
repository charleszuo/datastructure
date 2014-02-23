package com.charles.sort;


/**
 * 
 * @author charleszuo@126.com
 * 冒泡排序，稳定排序，时间复杂度O(n^2), 采用交换排序
 * 关键是每次从最底下往上冒泡，把小的元素冒上来，每次找到最小的一个
 * 优化: 增加一个标志位，如果一趟里面一次都没有交换，证明已经有序，可以直接退出
 */
public class BubbleSort {

	public static void sort(int[] data){
		int length = data.length;
		for(int i = 0; i < length; i++){
			// 从最后一个元素开始冒泡
			for(int j = length - 1; j > 0; j--){
				if(data[j] < data[j-1]){
					swap(data, j-1, j);
				}
			}
		}
	}
	
	// 增加一个标志位，如果一趟里面一次都没有交换，证明已经有序，可以直接退出
	public static void sortPro(int[] data){
		int length = data.length;
		boolean swaped = true;
		for(int i = 0; i < length && swaped; i++){
			swaped = false;
			// 从最后一个元素开始冒泡
			for(int j = length - 1; j > 0; j--){
				if(data[j] < data[j-1]){
					swap(data, j-1, j);
					swaped = true;
				}
			}
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
		
		sortPro(data);
		
		for(int i : data){
			System.out.println(i);
		}
	}
}
