package com.sise.atest;

import java.util.Queue;
import java.util.LinkedList;
/**
 * @Author: DMY
 * @Date: 2019/3/14 13:58
 * @Description:  两个队列实现一个栈
 */
public class Queue2Stack {

    private Queue queue1;
    private Queue queue2;
    //定义最大容量
    private int maxlength;

    public Queue2Stack(int max){
        queue1 = new LinkedList();
        queue2 = new LinkedList();
        maxlength = max;
    }

    //stack的push操作
    public boolean push(Object item){
        if (queue1.size() >= maxlength){
            return false;
        } else {
            queue1.add(item);
            return true;
        }
    }

    //stack的pop操作
    public Object pop(){
        if(queue1.isEmpty() && queue2.isEmpty()){
            return null;
        } else if (queue2.isEmpty()){//单独判断哪个栈不为空
            while (queue1.size()>1){
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        } else {
            while (queue2.size()>1){
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }
    }

    public static void main(String[] args) {
        Queue2Stack queue2Stack = new Queue2Stack(5);
        queue2Stack.push(1);
        queue2Stack.push(2);
        queue2Stack.push(3);
        queue2Stack.push(4);
        queue2Stack.push(5);
        queue2Stack.push(6);
        System.out.println(queue2Stack.pop()+","+queue2Stack.pop()+","+queue2Stack.pop()+","+queue2Stack.pop()
                +","+queue2Stack.pop()+","+queue2Stack.pop()); // 六次pop,输出5,4,3,2,1,null
    }
}
