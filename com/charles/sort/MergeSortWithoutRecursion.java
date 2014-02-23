package com.charles.sort;

/**
 * 
 * @author charleszuo@126.com
 * 非递归实现的归并排序，采用循环来代替递归，稳定的排序，时间复杂度是O(nlogn)
 * 归并排序的过程是将元素全部拆分，再构建完全二叉树的过程
 * 算法分为两步，第一步拆分，第二步合并。借助了一个数组来辅助存储归并的结果
 */
public class MergeSortWithoutRecursion {
	public static void sort(int[] data, int[] target){
		int length = data.length;
		// mergeSize用来指定归并的个数，最开始是1个和1个归并，然后是2个和2个，然后是4个和4个，直到大于要排序的个数
		int mergeSize = 1;
		while(mergeSize < length){
			mergePro(data, target, mergeSize, length);
			mergeSize = 2 * mergeSize;
		}
	}
	
	public static void mergePro(int[] data, int[] target, int mergeSize, int length){
		int k = 2 * mergeSize;
		// i用来记录上一次归并后的下一个位置
		int i = 0;
		// mergesize = 1时，归并元素的下标是(xk, (x+1)*k -1). 如果(x+1)*k -1 超过了数组长度，证明这次归并不能进行
		// 如果(xk, (x+1)*k -1) 可以归并，那么 middle元素的位置是 (2*x + 1) *k /2
		for(int x = 0; ((x+1)*k - 1) < length; x++){
			merge(data, target, x*k, (2*x + 1) * k / 2, (x+1)*k - 1);
			i = (x+1)*k;
		}
		if(i < length - mergeSize){
			merge(data, target, i, i + mergeSize, length - 1);
		}else{
			while(i < length){
				target[i] = data[i];
				i++;
			}
		}
			
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
		sort(data, target);
		
		for(int i : data){
			System.out.println(i);
		}
	}
}
