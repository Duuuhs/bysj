package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/3 15:55
 * @Description:
 */
public class QueryTeaByMangerPage {
    private String message;                 //查询消息
    private int pageMax;                    //最大页数
    private int pageNum;                    //当前页数
    private int dataStart;                  //开始的第一条数据序号
    private int dataEnd;                    //结束的最后一条数据序号
    private int teaCount;                   //总数据条数
    private List<TeaMessageByManger> teaList;    //教师数据信息

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

    public int getTeaCount() {
        return teaCount;
    }

    public void setTeaCount(int teaCount) {
        this.teaCount = teaCount;
    }

    public List<TeaMessageByManger> getTeaList() {
        return teaList;
    }

    public void setTeaList(List<TeaMessageByManger> teaList) {
        this.teaList = teaList;
    }

    @Override
    public String toString() {
        return "QueryTeaByMangerPage{" +
                "message='" + message + '\'' +
                ", pageMax=" + pageMax +
                ", pageNum=" + pageNum +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", teaCount=" + teaCount +
                ", teaList=" + teaList +
                '}';
    }
}
