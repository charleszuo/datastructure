package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 快速排序，不稳定的排序，时间复杂度O(nlogn)
 * 快速排序分为两步，第一步求枢轴，第二步对枢轴两边的序列递归排序
 * 快速排序也是构造二叉树的过程，平均情况下构造的是完全二叉树，当序列是有序时，效率最差，构造的是斜树，复杂度是O(n^2)
 */
public class QuickSort {
	public static void sort(int[] data, int low, int high){
		if(low < high){
			int pivot = partition_copy(data, low, high);
			sort(data, low, pivot - 1);
			sort(data, pivot + 1, high);
		}
	}
	
	public static int partition_swap(int[] data, int low, int high){
		int pivot = data[low];
		while(low < high){
			while(low < high && data[high] >= pivot){
				high --;
			}
			swap(data, low, high);
			while(low < high && data[low] < pivot){
				low ++;
			}
			swap(data, low, high);
		}
		return low;
	}
	
	public static int partition_copy(int[] data, int low, int high){
		int pivot = data[low];
		while(low < high){
			while(low < high && data[high] >= pivot){
				high --;
			}
			data[low] = data[high];
			while(low < high && data[low] < pivot){
				low ++;
			}
			data[high] = data[low];
		}
		data[low] = pivot;
		return low;
	}
	
	private static void swap(int[] data, int i, int j){
		if(i >= j){
			return;
		}
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
		
		sort(data, 0, 8);
		
		for(int i : data){
			System.out.println(i);
		}
	}
}
