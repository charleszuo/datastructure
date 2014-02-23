package com.charles.search;

/**
 * 
 * @author charleszuo@126.com
 * 二分查找，输入必须是有序序列
 */

import com.charles.sort.QuickSort;

public class BinarySearch {
	public static int search(int[] data, int key) {
		QuickSort.sort(data, 0, data.length - 1);

		int low = 0, high = data.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (key == data[middle]) {
				return middle;
			}else if (key < data[middle]) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
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
