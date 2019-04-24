package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/8 1:05
 * @Description:
 */
public class TJsex {

    private int studentSex;    //性别
    private int stuCount;   //人数

    public int getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(int studentSex) {
        this.studentSex = studentSex;
    }

    @Override
    public String toString() {
        return "TJsex{" +
                "studentSex=" + studentSex +
                ", stuCount=" + stuCount +
                '}';
    }

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }
}
