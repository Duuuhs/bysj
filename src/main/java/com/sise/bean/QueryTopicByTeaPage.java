package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/2/26 17:32
 * @Description:
 */
public class QueryTopicByTeaPage {
    private String message;                 //查询消息
    private int pageMax;                    //最大页数
    private int pageNum;                    //当前页数
    private int dataStart;                  //开始的第一条数据序号
    private int dataEnd;                    //结束的最后一条数据序号
    private int topicCount;                   //总数据条数
    private List<TeaQueryBsTopic> topicList;    //毕业设计题目信息

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPageMax() {
        return pageMax;
    }

    public void setPageMax(int pageMax) {
        this.pageMax = pageMax;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getDataStart() {
        return dataStart;
    }

    public void setDataStart(int dataStart) {
        this.dataStart = dataStart;
    }

    public int getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(int dataEnd) {
        this.dataEnd = dataEnd;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public List<TeaQueryBsTopic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TeaQueryBsTopic> topicList) {
        this.topicList = topicList;
    }

    @Override
    public String toString() {
        return "QueryTopicByTeaPage{" +
                "message='" + message + '\'' +
                ", pageMax=" + pageMax +
                ", pageNum=" + pageNum +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", topicCount=" + topicCount +
                ", topicList=" + topicList +
                '}';
    }
}
