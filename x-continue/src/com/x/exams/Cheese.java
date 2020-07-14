package com.x.exams;

import java.util.Arrays;

/**
* @Description
 *  Tom和Jerry都很喜欢吃奶酪，现在有n块奶酪散落在坐标轴上(1<=n<=100000)，
 *  他们分别在a1,a2,a3...an(1<=ai<=100000,一个点可以有多块奶酪)上，Tom和Jerry分别在1和100000两个点上，
 *  他们每走一步需要花费1s，问他们拿到所有的奶酪至少要花费多少时间
 * 输入奶酪数量n，和n个奶酪的坐标
 * 输出一个数，表示他们拿到所有奶酪所用的最短时间
 *
 * 输入：
 * 4
 * [350,2000,80000,99999]
 * 输出：
 * 20000
 *
* @ClassName ComplexString
* @Author caishixian
* @Date 2020/7/10 18:55
* @Version 1.0
**/
public class Cheese {

    static int[] sortData = new int[]{7000, 6000, 60000, 80000, 99999};

    public static void main(String[] args) {

        System.out.println(new Cheese().solution(6, sortData));

//        new Cheese().sort(0, sortData.length - 1);
//        System.out.println(Arrays.toString(sortData));

        System.out.println(new Cheese().solution1(6, sortData));
    }

    /**
    * @Description 循环判断
    * @Param [n, nums]
    * @Return int
    * @Author shuxing
    * @Date 2020/7/13 15:40
    **/
    private int solution(int n, int[] nums) {
        // 最小值
        Integer minimum = null;
        // 距离终点的距离
        int distanceEnd = 0;
        for (int num : nums) {
            distanceEnd = 100000 - num;
            int coordinateMinimum = 0;
            // 距离起点的距离 < 距离终点的距离，取坐标值
            if (num <= distanceEnd) {
                coordinateMinimum = num;
            } else {
                // 否则取
                coordinateMinimum = distanceEnd;
            }
            // 坐标之间取较大的那个
            if (minimum == null || minimum < coordinateMinimum) {
                minimum = coordinateMinimum;
            }
        }
        return minimum;
    }

    /**
    * @Description 使用快排的方式找到中间值
    * @Param [n, nums]
    * @Return int
    * @Author shuxing
    * @Date 2020/7/13 15:40
    **/
    private int solution1(int n, int[] nums) {
        // 数组个数
        int numLength = nums.length;
        // 排序
        sortData = nums;
        sort(0, numLength - 1);
        // 如果数组个数为奇数, 则通过中间那个数进行判断
        if (nums.length % 2 == 1) {
            int mid = sortData[numLength / 2];
            int distanceEnd = 100000 - mid;
            if (mid < distanceEnd) {
                return mid;
            } else {
                return distanceEnd;
            }
        }
        // 如果数组个数为偶数, 则比较中间的两个数字
        //先获取离50000.5较近的那个值
        int correctNum = 0;
        int midLeft = numLength / 2 - 1;
        int midRight = numLength / 2;
        if (Math.abs(50000.5 - sortData[midLeft]) <= Math.abs(50000.5 - sortData[midRight])) {
            correctNum = sortData[midLeft];
        } else {
            correctNum = sortData[midRight];
        }
        if (correctNum < 100000 - correctNum) {
            return correctNum;
        }
        return 100000 - correctNum;
    }

    private void sort(int left, int right) {
        int i, j, temp;
        i = left;
        j = right;
        if (left > right) {
            return;
        }
        temp = sortData[i];
        while (i != j) {
            while (sortData[j] <= temp && j > i) {
                j--;
            }
            while (sortData[i] >= temp && j > i) {
                i++;
            }
            if(i < j){
                int t = sortData[i];
                sortData[i] = sortData[j];
                sortData[j] = t;
            }
        }
        sortData[left] = sortData[i];
        sortData[i] = temp;

        sort(0, i - 1);
        sort(i + 1, right);
    }
}
