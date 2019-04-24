package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/26 17:14
 * @Description:
 */
public class TeaQueryBsTopic {
    private Long bsTopicId;             //毕设题目id
    private String bsTopicName;         //毕设题目名称
    private Long studentId;             //学生id
    private String studentName;         //学生姓名
    private String isSure;               //教师是否确认选题

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

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure;
    }

    @Override
    public String toString() {
        return "TeaQueryBsTopic{" +
                "bsTopicId=" + bsTopicId +
                ", bsTopicName='" + bsTopicName + '\'' +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", isSure='" + isSure + '\'' +
                '}';
    }
}
