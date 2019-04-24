package com.sise.atest;

import org.apache.commons.lang3.StringUtils;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

import static com.sise.common.Assert.inArray;
import static com.sise.common.Assert.isNumber;
/**
 * @Author: DMY
 * @Date: 2019/1/31 19:04
 * @Description:
 */
public class test {

    /*查找二维数组最大*/
    public static int selectMax(int[][] arr){
        int max = arr[0][0];
        for (int i = 0;i < arr.length;i++){
            for (int j = 0; j<arr[i].length; j++){
                if (max<arr[i][j]){
                    max = arr[i][j];
                }
            }
        }
        return max;
    }




    public static void main(String[] args) {

        /*查找二维数组最大
        int[][] arr = {{3,5,7,9,23},{100,21,11,97,99}};
        int i = selectMax(arr);
        System.out.println(i);*/


//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        ListIterator<String> iterator = list.listIterator();
//        iterator.previous();
//        System.out.println(list);

        Map<Integer,Integer> map = new HashMap<>();

        Set<Integer> key = map.keySet();
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        //根据key查值
        if (key.contains(5)) {
//            System.out.println("包含4的情况："+map.get(5));
        }

        for (Integer i: map.keySet()){
            System.out.println(i + " " + map.get(i));
        }

        System.out.println("=>");
        System.out.println(10001&10002);








    }




}


