package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/28 3:07
 * @Description:
 */
public class StuBsChoiceStatus {
    private Long studentId;     //  学号
    private String studentName; //  姓名
    private Long bsChoiceId;    //  毕业设计题目选择表id
    private String isSure;      //  教师是否确认

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

    public Long getBsChoiceId() {
        return bsChoiceId;
    }

    public void setBsChoiceId(Long bsChoiceId) {
        this.bsChoiceId = bsChoiceId;
    }

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure;
    }

    @Override
    public String toString() {
        return "StuBsChoiceStatus{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", bsChoiceId=" + bsChoiceId +
                ", isSure='" + isSure + '\'' +
                '}';
    }
}
