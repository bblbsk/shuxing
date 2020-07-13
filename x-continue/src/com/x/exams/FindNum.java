package com.x.exams;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
* @Description 从数组中获取两个元素，满足两个元素之和为指定值
* @ClassName FindNum
* @Date 2020/7/9 10:00
* @Version 1.0
**/
public class FindNum {

    static int result = 10;
    static Integer[] items = {1, 3, 5, 7};

    public static void main(String[] args) {
        System.out.println(new FindNum().find(result, items));
    }

    /**
    * @Description 查询
    * @Param [result, items]
    * @Return java.util.List<java.lang.Integer>
    * @Date 2020/7/9 9:59
    **/
    private List<Integer> find(int result, Integer[] items) {
        Set hup = Sets.newHashSet(items);
        for (Integer item : items) {
            int another = result - item;
            if (hup.contains(another)) {
                return Arrays.asList(item, another);
            }
        }
        return Arrays.asList();
    }
}
