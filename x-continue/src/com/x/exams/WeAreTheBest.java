package com.x.exams;

/**
* @description
 * 有一个阵营，里面有n个小队(1<=n<=100)，每个小队都有他们的能力值ai(0<=i<n)。
 *
 * 现在有一个紧急情况，需要从这些小队中选出连续的几个小队，组成一个最强的团队。
 * 最强的团队的定义为这个团队的所有小队的平均能力值最高。如果有多个最强团队，则选包含小队最多的一个。
 *
 * 现在请你写个程序，输出这个最强的团队包含的小队的个数。
 *
 * 输入小队的数量n，和n个数，分别代表各小队的能力值ai
 *
 * 输出一个数表示这个最强团队包含的小队的个数
 *
 * 示例1
 *
 * 输入：
 * 6
 * [1,2,3,3,2,1]
 * 输出：
 * 2
* @className WeAreTheBest
* @author caishixian
* @date 2020/7/20 14:15
* @version 1.0
**/
public class WeAreTheBest {

    public static void main(String[] args) {
        new WeAreTheBest().solution(6, new int[]{1,2,3,3,2,1});
    }

    public int solution(int n,int[] a) {

        return 2;
    }
}
