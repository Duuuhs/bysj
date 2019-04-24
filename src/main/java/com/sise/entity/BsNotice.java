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
 * @since 2019-02-21
 */

public class BsNotice {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bs_notice_id", type = IdType.AUTO)
    private Long bsNoticeId;

    private String bsNoticeContent;

    private Long bsTeacherId;

    private Date bsNoticeDate;


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

    public Date getBsNoticeDate() {
        return bsNoticeDate;
    }

    public void setBsNoticeDate(Date bsNoticeDate) {
        this.bsNoticeDate = bsNoticeDate;
    }

    @Override
    public String toString() {
        return "BsNotice{" +
                "bsNoticeId=" + bsNoticeId +
                ", bsNoticeContent='" + bsNoticeContent + '\'' +
                ", bsTeacherId=" + bsTeacherId +
                ", bsNoticeDate=" + bsNoticeDate +
                '}';
    }
}
