package com.sise.bean;

import java.util.Date;

/**
 * @Author: DMY
 * @Date: 2019/3/6 0:28
 * @Description:
 */
public class FileV2 {

    private Long fileId;

    private String fileName;

    private Long fileUserId;

    private String fileTime;

    private Long fileDownNum;




    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileUserId() {
        return fileUserId;
    }

    public void setFileUserId(Long fileUserId) {
        this.fileUserId = fileUserId;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public Long getFileDownNum() {
        return fileDownNum;
    }

    public void setFileDownNum(Long fileDownNum) {
        this.fileDownNum = fileDownNum;
    }

    @Override
    public String toString() {
        return "FileV2{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUserId=" + fileUserId +
                ", fileTime='" + fileTime + '\'' +
                ", fileDownNum=" + fileDownNum +
                '}';
    }
}
