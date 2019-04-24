package com.sise.atest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/14 16:45
 * @Description:  将一个int类型翻转,注意int溢出,负数表示问题
 */
public class reverseInt {


    public static int reverse(int x) {
        String x_s = x+"";
        String reverse_s = "";
        if (x < 0){
            x_s = x_s.substring(1,x_s.length());
            reverse_s = "-";
        }

        for (int i = x_s.length(); i >0 ; i--){
            String x_ss = x_s.substring(i-1, i);
            if (x_ss != "-"){
                reverse_s += x_ss;
            } else {
                reverse_s = x_ss + reverse_s;
            }
        }
        int result;

        try {
            result = Integer.parseInt(reverse_s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            result = 0;
        }

        return result;


    }

    public static void main(String[] args) {
//        int reverse = reverse(-123);
//        System.out.println(reverse);
        Integer e=129;
        Integer f=129;
        System.out.println(e==f);//输出 false
    }
}
