package com.x.sorting;

import java.util.Arrays;

/**
 * @ClassName: StraightSelectSortingDemo
 * @Description: ֱ��ѡ������
 * @author-csx
 * @date 2018��4��17�� ����4:03:43
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
				// ȡһ�����Ԫ�غͺ����ÿһ���ڲ��Ԫ�ؽ��бȽ�
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
