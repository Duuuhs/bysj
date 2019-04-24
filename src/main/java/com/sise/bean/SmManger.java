package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/1 23:42
 * @Description:
 */
public class SmManger {
    private int departmentCount;       //院系数目
    private int teaCount;              //教职工数目
    private int stuCount;              //毕业生人数
    private int stuChoiceCount;        //已确认选题人数

    public int getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(int departmentCount) {
        this.departmentCount = departmentCount;
    }

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
        return "SmManger{" +
                "departmentCount=" + departmentCount +
                ", teaCount=" + teaCount +
                ", stuCount=" + stuCount +
                ", stuChoiceCount=" + stuChoiceCount +
                '}';
    }
}
