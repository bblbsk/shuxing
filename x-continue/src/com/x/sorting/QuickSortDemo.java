package com.x.sorting;

/**
 * Created by csx on 2018-04-12.
 *
 * ÅÅÐòËã·¨·ÖÀà 
 * ²åÈëÅÅÐò£ºÖ±½Ó²åÈëÅÅÐò£¬Ï£¶ûÅÅÐò 
 * ½»»»ÅÅÐò£ºÃ°ÅÝÅÅÐò£¬¡¾¿ìËÙÅÅÐò¡¿ 
 * Ñ¡ÔñÅÅÐò£ºÖ±½ÓÑ¡ÔñÅÅÐò¡¢Ê÷ÐÎÑ¡ÔñÅÅÐò¡¢¶ÑÅÅÐòµÈ
 */
public class QuickSortDemo {

	static int[] data = { 10, 8, 6, 4, 3, 5, 7, 1, 2, 0, 9 };
	static int transCount = 0;

	public static void main(String[] args) {
		sort(0, data.length - 1);

		for (int value : data) {
			System.out.print(value + ", ");
			System.out.print("´ÎÊý:" + transCount);
		}
	}

	static void sort(int left, int right) {
		int i, j, temp;// note start index, end index ,
		i = left;
		j = right;
		if (left > right) {
			return;
		}
		temp = data[left];
		while (i != j) {
			while (data[j] <= temp && j > i) {
				j--;
			}
			while (data[i] >= temp && j > i) {
				i++;
			}
			if (i < j) {
				int t = data[i];
				data[i] = data[j];
				data[j] = t;
			}
		}
		data[left] = data[i];
		data[i] = temp;

		sort(0, i - 1);
		sort(i + 1, right);
	}

}
