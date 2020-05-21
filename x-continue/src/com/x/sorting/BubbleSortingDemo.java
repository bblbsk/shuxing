package com.x.sorting;

import java.util.Arrays;

public abstract class BubbleSortingDemo {


	static int[] nums = {3,5,6,2,1,7};
	
	public static void main(String[] args) {

		// 冒泡：比较相邻的两个数
		for (int i = 0; i < nums.length -1; i++) {
			for (int j = 0; j < nums.length - i - 1; j++) {
				if (nums[j] < nums[j+1]) {
					int t = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = t;
				}
			}
		}
		System.out.println(Arrays.toString(nums));
	}

}
