package com.sise.bean;

import com.sise.entity.BsTopic;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/2/13 23:16
 * @Description:
 */
public class TeaBsTopics {
    private String message;             //用于存放信息
    private List<BsTopic> bsTopics;     //用于存放毕设题目的列表
    private int coutBsTopic;            //用于存放毕业设计题目的条数


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BsTopic> getBsTopics() {
        return bsTopics;
    }

    public void setBsTopics(List<BsTopic> bsTopics) {
        this.bsTopics = bsTopics;
    }

    public int getCoutBsTopic() {
        return coutBsTopic;
    }

    public void setCoutBsTopic(int coutBsTopic) {
        this.coutBsTopic = coutBsTopic;
    }


    @Override
    public String toString() {
        return "TeaBsTopics{" +
                "message='" + message + '\'' +
                ", bsTopics=" + bsTopics +
                ", coutBsTopic=" + coutBsTopic +
                '}';
    }
}
