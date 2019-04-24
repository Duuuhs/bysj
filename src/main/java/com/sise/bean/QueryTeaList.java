package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/7 12:41
 * @Description:
 */
public class QueryTeaList {

    private Long teacherId;     //工号
    private String teacherName; //教师姓名

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

    @Override
    public String toString() {
        return "QueryTeaList{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
