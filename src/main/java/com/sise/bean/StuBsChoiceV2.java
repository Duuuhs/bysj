package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/24 17:10
 * @Description:
 */
public class StuBsChoiceV2 {
    private Long studentId;
    private String studentName;
    private Long bsTopicId;
    private String bsTopicName;
    private String isSure;

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

    public Long getBsTopicId() {
        return bsTopicId;
    }

    public void setBsTopicId(Long bsTopicId) {
        this.bsTopicId = bsTopicId;
    }

    public String getBsTopicName() {
        return bsTopicName;
    }

    public void setBsTopicName(String bsTopicName) {
        this.bsTopicName = bsTopicName;
    }

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure;
    }

    @Override
    public String toString() {
        return "StuBsChoiceV2{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", bsTopicId=" + bsTopicId +
                ", bsTopicName='" + bsTopicName + '\'' +
                ", isSure='" + isSure + '\'' +
                '}';
    }
}
