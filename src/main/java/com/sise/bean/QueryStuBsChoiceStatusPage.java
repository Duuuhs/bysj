package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/2/28 19:38
 * @Description:
 */
public class QueryStuBsChoiceStatusPage {
    private String message;                 //查询消息
    private int pageMax;                    //最大页数
    private int pageNum;                    //当前页数
    private int dataStart;                  //开始的第一条数据序号
    private int dataEnd;                    //结束的最后一条数据序号
    private int stuCount;                   //总数据条数
    private List<StuBsChoiceStatus> stuList;    //学生数据信息

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

    public int getStuCount() {
        return stuCount;
    }

    public void setStuCount(int stuCount) {
        this.stuCount = stuCount;
    }

    public List<StuBsChoiceStatus> getStuList() {
        return stuList;
    }

    public void setStuList(List<StuBsChoiceStatus> stuList) {
        this.stuList = stuList;
    }

    @Override
    public String toString() {
        return "QueryStuBsChoiceStatusPage{" +
                "message='" + message + '\'' +
                ", pageMax=" + pageMax +
                ", pageNum=" + pageNum +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", stuCount=" + stuCount +
                ", stuList=" + stuList +
                '}';
    }
}
