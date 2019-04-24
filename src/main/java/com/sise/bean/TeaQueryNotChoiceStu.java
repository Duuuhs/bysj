package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/27 15:56
 * @Description:
 */
public class TeaQueryNotChoiceStu {
    private Long studentId;        //学生学号
    private String studentName;    //学生姓名

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "TeaQueryNotChoiceStu{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
