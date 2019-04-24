package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/3 14:43
 * @Description:
 */
public class TeaMessageByManger {
    private Long teacherId;             //教师工号
    private String teacherName;         //教师姓名
    private String baseDepartmentName;  //系别
    private int stuCount;               //毕业设计学生人数

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getBaseDepartmentName() {
        return baseDepartmentName;
    }

    public void setBaseDepartmentName(String baseDepartmentName) {
        this.baseDepartmentName = baseDepartmentName;
    }

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }


    @Override
    public String toString() {
        return "TeaMessageByManger{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", baseDepartmentName='" + baseDepartmentName + '\'' +
                ", stuCount=" + stuCount +
                '}';
    }
}
