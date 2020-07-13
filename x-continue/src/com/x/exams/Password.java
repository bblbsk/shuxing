package com.x.exams;

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
        String s = "abc";
        String str = "xxxcabyyy";

        System.out.println(new Password().password(s, str));
    }

    private String password(String s, String str) {



        return "YES";
    }


}
