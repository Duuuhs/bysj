package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/7 22:18
 * @Description:
 */
public class TJclasses {
    private String baseClassName;   //班级名称
    private int studentNum;         //班级人数

    public String getBaseClassName() {
        return baseClassName;
    }

    public void setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "TJclasses{" +
                "baseClassName='" + baseClassName + '\'' +
                ", studentNum=" + studentNum +
                '}';
    }
}
