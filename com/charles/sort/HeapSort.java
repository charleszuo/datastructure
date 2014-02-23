package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 堆排序，不稳定的排序，时间复杂度O(n*logn)，是选择排序的一种，每次选择大顶堆的顶部元素
 * 算法分为两步，第一步是对非叶子结点构建堆。第二布是交换顶部和最后一个元素，然后再调整堆。
 * 调整堆的算法是把 i 结点加到原本是大顶堆的 i到m个结点中，调整结点的位置。调整一次后要继续从刚调整的位置开始继续调整它和左右孩子，直到是大顶堆为止
 * 堆排序模拟了完全二叉树的结构。
 */
public class HeapSort {
	public static void sort(int[] data){
		int length = data.length;
		//对所有非叶子结点，从后往前调整堆，构建初始堆
		for(int i = length / 2 - 1; i >= 0 ; i--){
			heapAdjust(data, i, length);
		}
		
		for(int j = length - 1; j > 0; j--){
			swap(data, 0, j);
			// 对剩下的j个元素调整堆
			heapAdjust(data, 0, j);
		}
	}
	
	public static void heapAdjust(int[] data, int s, int m){
		if(s >= m){
			return;
		}
		int temp = data[s];
		for(int i = 2 * s + 1; i < m; i = i * 2 + 1){
			// i + 1 < m表示如果有右孩子，就比较左右孩子
			if((i + 1 < m) && data[i] < data[i+1]){
				i ++;
			}
			if(data[i] <= data[s]){
				//已经是大顶堆
				break;
			}
			data[s] = data[i];
			data[i] = temp;
			// 把s指向刚交换的位置，再调整这个位置和它的左右孩子的位置
			s = i;
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
