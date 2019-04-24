package com.sise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author DMY
 * @since 2019-03-04
 */

public class File {

    private static final long serialVersionUID = 1L;

    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    private String fileName;

    private Long fileUserId;

    private Date fileTime;

    private Long fileDownNum;

    private String fileUserIdentity;


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

    public Date getFileTime() {
        return fileTime;
    }

    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    public Long getFileDownNum() {
        return fileDownNum;
    }

    public void setFileDownNum(Long fileDownNum) {
        this.fileDownNum = fileDownNum;
    }

    public String getFileUserIdentity() {
        return fileUserIdentity;
    }

    public void setFileUserIdentity(String fileUserIdentity) {
        this.fileUserIdentity = fileUserIdentity;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileUserId=" + fileUserId +
                ", fileTime=" + fileTime +
                ", fileDownNum=" + fileDownNum +
                ", fileUserIdentity='" + fileUserIdentity + '\'' +
                '}';
    }
}
