package com.x.sorting;

import java.util.Arrays;

/**
 * @ClassName: StraightSelectSortingDemo
 * @Description: 直接选择排序
 * @author-csx
 * @date 2018年4月17日 下午4:03:43
 *
 */
public abstract class StraightSelectSortingDemo {

	static int[] sdata = { 3, 5, 6, 2, 1, 7 };

	public static void main(String[] args) {
		sdata = sort(sdata);
		System.out.println(Arrays.toString(sdata));
	}

	private static int[] sort(int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				// 取一个外层元素和后面的每一个内层的元素进行比较
				if (data[i] > data[j]) {
					int temp = data[i];
					data[i] = data[j];
					data[j] = temp;
				}
			}
		}
		return data;
	}
}
