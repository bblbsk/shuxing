package com.x.exams.dedup;

import com.google.common.collect.Sets;

import java.util.Set;

/**
* @Description 排除重复的字符
* @ClassName DedupChar
* @Author caishixian
* @Date 2020/7/9 15:42
* @Version 1.0
**/
public class DedupChar {

    static String content = "ajfeioacvzndbakjfaeoijfaoiew";

    public static void main(String[] args) {
        Set<Character> dedup = Sets.newHashSet();
        for (int i = 0; i < content.length(); i++) {
            dedup.add(content.charAt(i));
        }
        System.out.println(dedup);
    }
}
