package com.x.sorting;

/**
 * Created by csx on 2018-04-12.
 *
 * 排序算法分类 插入排序：直接插入排序，希尔排序 交换排序：冒泡排序，【快速排序】 选择排序：直接选择排序、树形选择排序、堆排序等
 */
public class QuickSortDemo2 {

	static int[] data = { 10, 8, 6, 4, 3, 5, 7, 1, 2, 0, 9 };

	public static void main(String[] args) {
		sort(0, data.length - 1);

		for (int value : data) {
			System.out.print(value + ", ");
		}
	}

	private static void sort(int left, int right) {
		int i, j, temp;
		i = left;
		j = right;
		if (i > j) {
			return;
		}
		temp = data[left];
		// 10, 8, 6, 4, 3, 5, 7, 1, 2, 0, 9
		while(i<j){
			while(i<j && data[j] >= temp){
				j--;
			}
			while(i<j && data[i] <= temp){
				i++;
			}
			
//			if (i<j) {
				int t = data[i];
				data[i] = data[j];
				data[j] = t;
//			}
		}
		
		data[left] = data[i];
		data[i] = temp;
		
		sort(0, i -1);
		sort(i+1, right);
		
	}


}
