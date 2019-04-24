package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/1 23:38
 * @Description:
 */
public class DmMessage {
    private int teaCount;      //本系教职工人数
    private int stuCount;      //本系毕业生人数
    private int stuChoiceCount;//本系已确认选题学生人数

    public int getTeaCount() {
        return teaCount;
    }

    public void setTeaCount(int teaCount) {
        this.teaCount = teaCount;
    }

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public int getStuChoiceCount() {
        return stuChoiceCount;
    }

    public void setStuChoiceCount(int stuChoiceCount) {
        this.stuChoiceCount = stuChoiceCount;
    }

    @Override
    public String toString() {
        return "DmMessage{" +
                "teaCount=" + teaCount +
                ", stuCount=" + stuCount +
                ", stuChoiceCount=" + stuChoiceCount +
                '}';
    }
}
