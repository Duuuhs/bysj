package com.sise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
/**
 * <p>
 * 
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */

public class BsChoice {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bs_choice_id", type = IdType.AUTO)
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

    @Override
    public String toString() {
        return "BsChoice{" +
                "bsChoiceId=" + bsChoiceId +
                ", studentId=" + studentId +
                ", bsTopicId=" + bsTopicId +
                ", isSure='" + isSure + '\'' +
                ", guidance='" + guidance + '\'' +
                ", reviewPoint=" + reviewPoint +
                ", replyPoint=" + replyPoint +
                '}';
    }
}
