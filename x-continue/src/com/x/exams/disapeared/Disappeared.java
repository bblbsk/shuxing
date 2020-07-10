package com.x.exams.disapeared;

import java.util.Stack;

/**
* @Description
 * 在书架上摆着一些书，这些书只有两种颜色，要么是黄色，要么是蓝色，突然某一天这些书被施了魔法，如果一本黄色和一本蓝色的书挨着，这两本书就会消失不见，
 * 然后右边的书会往左边移动，直到和左边的书挨着，如果这两本颜色不同，这两本书又会神秘消失。
 * 现在给你一个只包含A和B的字符串s(1<=|s|<=100000)，其中A表示黄色的书，B表示蓝色的书，问这n本书中最多会消失多少本书。
 * 输入一个字符串s，s中A表示黄色的书，B表示蓝色的书
 * 输出最多会消失多少本书
 *
 * 输入：
 * "AABB"
 * 输出：
 * 4
* @ClassName Disappeared
* @Author caishixian
* @Date 2020/7/10 17:04
* @Version 1.0
**/
public class Disappeared {

    public static void main(String[] args) {
        String content = "AAABBB";
        int disappeared = new Disappeared().disappeared(content);
        System.out.println("消失的书个数：" + disappeared);
    }

    private int disappeared(String content) {
        int disappeardCount = 0;
        Stack<Character> stack = new Stack();
        Character curr = null;
        Character top = null;
        for (int i = 0; i < content.length(); i++) {
            curr = content.charAt(i);
            if (stack.empty()) {
                stack.push(curr);
                continue;
            }
            top = stack.peek();
            if (!top.equals(curr)) {
                stack.pop();
                disappeardCount++;
                continue;
            }
            // 添加进去
            stack.push(curr);
        }
        return disappeardCount << 1;
    }
}
