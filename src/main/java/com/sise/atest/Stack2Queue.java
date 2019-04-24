package com.sise.atest;

import java.util.Stack;

/**
 * @Author: DMY
 * @Date: 2019/3/14 12:49
 * @Description: 两个栈实现一个队列
 */
public class Stack2Queue {

    private Stack stack1;
    private Stack stack2;
    //定义最大容量
    private int maxlength;

    public Stack2Queue(int length){
        stack1 = new Stack();
        stack2 = new Stack();
        maxlength = length;
    }

    //queue的put操作
    public boolean put(Object item){
        if (stack1.size() >= maxlength){
            return false;
        } else {
            stack1.push(item);
            return true;
        }
    }

    //queue的poll操作
    public Object poll(){
        if (!stack2.isEmpty()){
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        Stack2Queue stack2Queue = new Stack2Queue(5);
        stack2Queue.put(2);
        stack2Queue.put(3);
        stack2Queue.put(4);
        stack2Queue.put(5);
        stack2Queue.put(6);
        stack2Queue.put(7);

        System.out.println(stack2Queue.poll()+","+stack2Queue.poll()+","+stack2Queue.poll()+","+stack2Queue.poll()+","+stack2Queue.poll());
    }
}
