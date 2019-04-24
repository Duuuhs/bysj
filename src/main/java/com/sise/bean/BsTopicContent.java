package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/7 16:17
 * @Description:
 */
public class BsTopicContent {
    private Long bsTopicId;     //毕设编号
    private String bsTopicName; //毕设名字

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

    @Override
    public String toString() {
        return "BsTopicContent{" +
                "bsTopicId=" + bsTopicId +
                ", bsTopicName='" + bsTopicName + '\'' +
                '}';
    }
}
