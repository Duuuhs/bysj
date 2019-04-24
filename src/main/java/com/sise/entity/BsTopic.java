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

public class BsTopic{

    private static final long serialVersionUID = 1L;

    /**
     * 毕设题目的id
     */
    @TableId(value = "bs_topic_id", type = IdType.AUTO)
    private Long bsTopicId;

    /**
     * 毕设题目
     */
    private String bsTopicName;

    /**
     * 毕设题目对应的老师
     */
    private Long bsTeacherId;

    private String isChoice;


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

    public Long getBsTeacherId() {
        return bsTeacherId;
    }

    public void setBsTeacherId(Long bsTeacherId) {
        this.bsTeacherId = bsTeacherId;
    }

    public String getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(String isChoice) {
        this.isChoice = isChoice;
    }

    @Override
    public String toString() {
        return "BsTopic{" +
                "bsTopicId=" + bsTopicId +
                ", bsTopicName='" + bsTopicName + '\'' +
                ", bsTeacherId=" + bsTeacherId +
                ", isChoice='" + isChoice + '\'' +
                '}';
    }
}
