package com.x.sorting;

import java.util.Arrays;

/**
  * @ClassName: StraightInsertionSortDemo
  * @Description: 直接插入排序
  * @author-csx
  * @date 2018年5月7日 上午10:49:22
  *
  */
public class StraightInsertionSortDemo {

	static int[] nums = {5,3,2,6,4,1,7};
	
	public static void main(String[] args) {

		int i,j;
		
		for (i = 1; i < nums.length; i++) {
			int tmp = nums[i];
			for (j = i - 1; (j >= 0 && nums[j] > tmp); j--) {
				nums[j + 1] = nums[j];
			}
			nums[j + 1] = tmp;
		}
		
		System.out.println(Arrays.toString(nums));
	}

}
