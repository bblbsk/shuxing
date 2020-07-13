package com.x.exams;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
* @Description
 * Tom在期末考完试以后学习了Python语言，他发现Python语言确实是简洁又强大，在他学完字符串以后，
 * 他写了一个随机生成密码的python文件，原理是这样的，输入一个字符串s，然后系统会随机将这个s重新任意排序，
 * 然后又生成两个字符串s1和s2，并拼接起来最终生成随机密码str = s1 + s + s2。现在Tom有一个问题要问你，
 * 每次给你两个字符串，一个是s(表示输入的字符串)，一个是str(表示产生的随机字符串)，问这两个字符串是否符合上述要求的原理，
 * 如果符合输出YES，否则输出NO。 1<=s,str<=100
 *
 * 输入两个字符串，一个是s，表示输入的字符串，一个是str，表示产生的随机字符串（1<=s,str<=100）
 * 输出内容为一行字符串，如果符合题意中的原理输出"YES"，否则输出"NO"。
 *
 * 输入：
 * "abc"
 * "xxxcabyyy"
 * 输出：
 * "YES"
* @ClassName Password
* @Author caishixian
* @Date 2020/7/13 9:47
* @Version 1.0
**/
public class Password {

    public static void main(String[] args) {
        String s = "abcd";
        String str = "dcab";

        System.out.println(new Password().password(s, str));
    }

    private String password(String s, String str) {
        int sLength = s.length();
        int strLength = str.length();
        if (strLength < sLength) {
            return "NO";
        }
        // 存储原始字符串每个字符出现的次数
        Map<Character, AtomicInteger> sCharMap = strToCharCountMap(new HashMap<>(), null, s);
        // 存储随机字符串每个字符出现的次数
        Map<Character, AtomicInteger> strCharMap = strToCharCountMap(new HashMap<>(), null, str);
        // 如果此时有不存在的字符，则直接返回不匹配
        Set<Map.Entry<Character, AtomicInteger>> entries = sCharMap.entrySet();
        for (Map.Entry<Character, AtomicInteger> entry : entries) {
            // 随机字符串中有不存在的字符 || 字符数量小于原始字符串，表示不匹配，直接返回
            if (!strCharMap.containsKey(entry.getKey()) || strCharMap.get(entry.getKey()).intValue() < entry.getValue().intValue()) {
                return "NO";
            }
        }
        // str和s的长度差
        int diffSAndStr = strLength - sLength;
        String correctStr = null;
        // 已经匹配的字符集合
        Set<Character> matchedCharSet = new HashSet<>();
        // 待校验的字符串字符集合
        Map<Character, AtomicInteger> correctCharMap = null;
        for (int i = 0; i <= diffSAndStr; i++) {
            // 从str开始位置截取和s长度相等的字符串，并将char情况存入Map中
            correctStr = str.substring(i, s.length() + i);
            // 获取char集合
            if (i == 0) {
                correctCharMap = strToCharCountMap(new HashMap<>(), null, correctStr);
            } else {
                // 移除前一个，增加后一个
                char beforeChar = str.charAt(i - 1);
                char nextChar = str.charAt(s.length() + i - 1);
                // 如果移除的字符和添加的字符一致，则继续下一轮验证
                if (beforeChar == nextChar) {
                    continue;
                }
                correctCharMap = strToCharCountMap(correctCharMap, beforeChar, new String(new char[]{nextChar}));
                // 如果移除的是已经匹配的字符，则从已匹配的字符集合中移除该字符，并继续下一轮验证
                if (matchedCharSet.contains(beforeChar)) {
                    matchedCharSet.remove(beforeChar);
                    continue;
                }
            }
            // 校验每一个字符是否匹配
            for (Map.Entry<Character, AtomicInteger> entry : entries) {
                // char存在 && 出现的次数一致
                if (correctCharMap.containsKey(entry.getKey()) && correctCharMap.get(entry.getKey()).intValue() == entry.getValue().intValue()) {
                    // 该字符匹配
                    matchedCharSet.add(entry.getKey());
                }
            }
            if (matchedCharSet.size() == entries.size()) {
                return "YES";
            }
        }
        return "NO";
    }

    private Map<Character, AtomicInteger> strToCharCountMap(Map<Character, AtomicInteger> charMap, Character deleteChar, String addContent) {
        // 移除的数据(当前情况下，移除的字符串已经被加到集合中，即被移除的字符串可定存在~)
        if (deleteChar != null) {
            AtomicInteger atomicInteger = charMap.get(deleteChar);
            atomicInteger.getAndDecrement();
        }
        // 添加的数据
        if (addContent != null && addContent.length() > 0) {
            for (char aChar : addContent.toCharArray()) {
                // 包含则出现次数+1
                if (charMap.containsKey(aChar)) {
                    charMap.get(aChar).getAndIncrement();
                } else {
                    // 不包含则初始化出现次数
                    charMap.put(aChar, new AtomicInteger(1));
                }
            }
        }
        return charMap;
    }

}
