package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/5 14:25
 * @Description:
 */
public class QueryStuUpLoadPage {

    private String message;                 //查询消息
    private int pageMax;                    //最大页数
    private int pageNum;                    //当前页数
    private int dataStart;                  //开始的第一条数据序号
    private int dataEnd;                    //结束的最后一条数据序号
    private int fileCount;                  //总数据条数
    private List<QueryStuUpLoadV2>fileContent;//文件信息

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

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public List<QueryStuUpLoadV2> getFileContent() {
        return fileContent;
    }

    public void setFileContent(List<QueryStuUpLoadV2> fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "QueryStuUpLoadPage{" +
                "message='" + message + '\'' +
                ", pageMax=" + pageMax +
                ", pageNum=" + pageNum +
                ", dataStart=" + dataStart +
                ", dataEnd=" + dataEnd +
                ", fileCount=" + fileCount +
                ", fileContent=" + fileContent +
                '}';
    }
}
