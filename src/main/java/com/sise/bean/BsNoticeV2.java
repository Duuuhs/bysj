package com.sise.bean;

import java.util.Date;

/**
 * @Author: DMY
 * @Date: 2019/2/21 13:56
 * @Description:
 */
public class BsNoticeV2 {

    private Long bsNoticeId;

    private String bsNoticeContent;

    private Long bsTeacherId;

    private String bsNoticeDate;


    public Long getBsNoticeId() {
        return bsNoticeId;
    }

    public void setBsNoticeId(Long bsNoticeId) {
        this.bsNoticeId = bsNoticeId;
    }

    public String getBsNoticeContent() {
        return bsNoticeContent;
    }

    public void setBsNoticeContent(String bsNoticeContent) {
        this.bsNoticeContent = bsNoticeContent;
    }

    public Long getBsTeacherId() {
        return bsTeacherId;
    }

    public void setBsTeacherId(Long bsTeacherId) {
        this.bsTeacherId = bsTeacherId;
    }

    public String getBsNoticeDate() {
        return bsNoticeDate;
    }

    public void setBsNoticeDate(String bsNoticeDate) {
        this.bsNoticeDate = bsNoticeDate;
    }

    @Override
    public String toString() {
        return "BsNoticeV2{" +
                "bsNoticeId=" + bsNoticeId +
                ", bsNoticeContent='" + bsNoticeContent + '\'' +
                ", bsTeacherId=" + bsTeacherId +
                ", bsNoticeDate='" + bsNoticeDate + '\'' +
                '}';
    }
}
