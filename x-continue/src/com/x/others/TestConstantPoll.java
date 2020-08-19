package com.x.others;

public class TestConstantPoll {


    public static void main(String[] args) {
//        String s1 = "1";
//        String s2 = "2";
//        String s3 = "1" + "2";
//        String s2 = new String("1");
//        s4.intern();
//        String s4 = "1";
//
//        System.out.println(s4 == s2);
//        System.out.println(s4 == s3);
//        System.out.println(i == i1);
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);

    }
}
