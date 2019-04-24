package com.sise.bean;

import java.util.Date;

/**
 * @Author: DMY
 * @Date: 2019/3/5 14:14
 * @Description:
 */
public class QueryStuUpLoad {

    private Long studentId;     //学号
    private String studentName; //姓名
    private Long fileId;        //文件id
    private String fileName;    //文件名
    private Date fileTime;      //上传时间

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

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

    public Date getFileTime() {
        return fileTime;
    }

    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    @Override
    public String toString() {
        return "QueryStuUpLoad{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileTime=" + fileTime +
                '}';
    }
}
