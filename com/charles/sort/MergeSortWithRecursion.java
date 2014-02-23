package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 递归实现的归并排序，稳定的排序，时间复杂度是O(nlogn)
 * 归并排序的过程是将元素全部拆分，再构建完全二叉树的过程
 * 算法分为两步，第一步拆分，第二步合并。借助了一个数组来辅助存储归并的结果
 */
public class MergeSortWithRecursion {
	public static void sort(int[] data, int[] target, int begin, int end){
		if(begin == end){
			return;
		}
		int middle = (end + 1 + begin) / 2;
		sort(data, target, begin, middle - 1);
		sort(data, target, middle, end);
		merge(data, target, begin, middle, end);
	}
	
	// 归并函数，上半部分的下标是从begin到middle-1,下边部分是从middle到end，包含end
	public static void merge(int[] data, int[] target, int begin, int middle, int end){
		if(begin >= end){
			return;
		}
		int i = begin, j = middle, count = begin;
		while(i < middle && j <= end){
			if(data[i] < data[j]){
				target[count++] = data[i];
				i++;
			}else{
				target[count++] = data[j];
				j++;		
			}
		}
		if(i < middle){
			for(int k = 0; k < middle - i; k++){
				target[count + k] = data[i+k];
			}
		}else if(j <= end){
			for(int k = 0; k < end - j; k++){
				target[count + k] = data[j+k];
			}
		}
		for(int m = begin; m <= end; m++){
			data[m] = target[m];
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
		
		int[] target = new int[9];
		sort(data, target, 0, 8);
		
		for(int i : data){
			System.out.println(i);
		}
	}
}
