package com.x.exams;

/**
* @Description
 * 现在有两个字符串s1和s2（长度不超过200000），Tom是一个有强迫症的人，他想要把这两个字符串变的相同。
 *
 * 但是每次只能删除其中一个字符串的最左端的字符，问最少需要经过多少次操作才能使这两个字符串变的相同？
 *
 * 输入内容为两个，第一个为字符串s1，第二个为字符串s2
 *
 * 输出一个数字，表示最小的操作次数
 *
 * 输入：
 * "dadc"
 * "dddc"
 * 输出：
 * 4
 *
* @ClassName ComplexString
* @Author caishixian
* @Date 2020/7/10 18:55
* @Version 1.0
**/
public class ComplexString {

    public static void main(String[] args) {

        System.out.println(new ComplexString().complexString("vadadc", "dddc"));
    }

    private int complexString(String s1, String s2) {
        int s1Lenght = s1.length();
        int s2Lenght = s2.length();
        // 移动的总次数
        int moveCount = 0;
        // 两个字符串的长度差
        int stringsLengthDiff = 0;
        // 使两个字符串一样长
        stringsLengthDiff = s1Lenght - s2Lenght;
        // s2Lenght > s1Lenght
        if (stringsLengthDiff < 0) {
            s2 = s2.substring(stringsLengthDiff);
            moveCount += stringsLengthDiff;
        } else if (stringsLengthDiff > 0) {
            // s1Lenght > s2Lenght
            s1 = s1.substring(stringsLengthDiff);
            moveCount += stringsLengthDiff;
        }
        // 修正后的字符串长度
        int correctStringLength = s1.length();
        // 从后往前判断
        Character s1Char = null;
        Character s2Char = null;
        int surplusCharCount = 0;
        for (int i = correctStringLength - 1; i >= 0; i--) {
            s1Char = s1.charAt(i);
            s2Char = s2.charAt(i);
            if (s1Char.equals(s2Char)) {
                continue;
            }
            // 发现不一致的字符，计算还剩几个没有比较
            surplusCharCount = i + 1;
            break;
        }
        // 移动的总次数
        moveCount += surplusCharCount << 1;
        return moveCount;
    }
}
