package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/17 22:49
 * @Description:
 */
public class StuBsChoice {

    private Long bsChoiceId;
    /**
     * 学生id
     */
    private Long studentId;

    /**
     * 毕设id
     */
    private Long bsTopicId;

    /**
     * 毕设题目
     */
    private String bsTopicName;

    /**
     * 确认选题 T,F
     */
    private String isSure;

    /**
     * 指导老师指导意见
     */
    private String guidance;

    /**
     * 评阅分数
     */
    private Double reviewPoint;

    /**
     * 答辩分数
     */
    private Double replyPoint;





    public Long getBsChoiceId() {
        return bsChoiceId;
    }

    public void setBsChoiceId(Long bsChoiceId) {
        this.bsChoiceId = bsChoiceId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBsTopicId() {
        return bsTopicId;
    }

    public void setBsTopicId(Long bsTopicId) {
        this.bsTopicId = bsTopicId;
    }

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure;
    }

    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    public Double getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(Double reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public Double getReplyPoint() {
        return replyPoint;
    }

    public void setReplyPoint(Double replyPoint) {
        this.replyPoint = replyPoint;
    }

    public String getBsTopicName() {
        return bsTopicName;
    }

    public void setBsTopicName(String bsTopicName) {
        this.bsTopicName = bsTopicName;
    }

    @Override
    public String toString() {
        return "StuBsChoice{" +
                "bsChoiceId=" + bsChoiceId +
                ", studentId=" + studentId +
                ", bsTopicId=" + bsTopicId +
                ", bsTopicName='" + bsTopicName + '\'' +
                ", isSure='" + isSure + '\'' +
                ", guidance='" + guidance + '\'' +
                ", reviewPoint='" + reviewPoint + '\'' +
                ", replyPoint='" + replyPoint + '\'' +
                '}';
    }
}
