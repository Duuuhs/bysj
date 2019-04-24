package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/8 14:22
 * @Description:
 */
public class TJbs {
    private int notTea;             //无毕设老师，未选题
    private int hasTeaNoChoice;     //有毕设老师，未选题
    private int hasTeaHasChoice;    //有毕设老师，已选题

    public int getNotTea() {
        return notTea;
    }

    public void setNotTea(int notTea) {
        this.notTea = notTea;
    }

    public int getHasTeaNoChoice() {
        return hasTeaNoChoice;
    }

    public void setHasTeaNoChoice(int hasTeaNoChoice) {
        this.hasTeaNoChoice = hasTeaNoChoice;
    }

    public int getHasTeaHasChoice() {
        return hasTeaHasChoice;
    }

    public void setHasTeaHasChoice(int hasTeaHasChoice) {
        this.hasTeaHasChoice = hasTeaHasChoice;
    }

    @Override
    public String toString() {
        return "TJbs{" +
                "notTea=" + notTea +
                ", hasTeaNoChoice=" + hasTeaNoChoice +
                ", hasTeaHasChoice=" + hasTeaHasChoice +
                '}';
    }
}
