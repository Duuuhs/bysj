package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/2 16:08
 * @Description:
 */
public class QueryStuMessageByMangerPage {
    private String message;                 //查询消息
    private int pageMax;                    //最大页数
    private int pageNum;                    //当前页数
    private int dataStart;                  //开始的第一条数据序号
    private int dataEnd;                    //结束的最后一条数据序号
    private int stuCount;                   //总数据条数
    List<StuMessageByManger> stuMessageByMangers;       //学生的基本信息(DM)
    List<StuMessageByMangerV2> stuMessageByMangerV2s;   //学生的基本信息(SM)

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

    public List<StuMessageByManger> getStuMessageByMangers() {
        return stuMessageByMangers;
    }

    public void setStuMessageByMangers(List<StuMessageByManger> stuMessageByMangers) {
        this.stuMessageByMangers = stuMessageByMangers;
    }

    public List<StuMessageByMangerV2> getStuMessageByMangerV2s() {
        return stuMessageByMangerV2s;
    }

    public void setStuMessageByMangerV2s(List<StuMessageByMangerV2> stuMessageByMangerV2s) {
        this.stuMessageByMangerV2s = stuMessageByMangerV2s;
    }

    @Override
    public String toString() {
        return "QueryStuMessageByMangerPage{" +
                "message='" + message + '\'' +
                ", pageMax=" + pageMax +
                ", pageNum=" + pageNum +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", stuCount=" + stuCount +
                ", stuMessageByMangers=" + stuMessageByMangers +
                ", stuMessageByMangerV2s=" + stuMessageByMangerV2s +
                '}';
    }
}
