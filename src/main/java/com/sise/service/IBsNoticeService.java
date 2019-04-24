package com.sise.service;

import com.sise.bean.BsNoticeV2;
import com.sise.bean.TMessage;
import com.sise.entity.BsNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */
public interface IBsNoticeService extends IService<BsNotice> {


    /*
     *  新增公告
     *  prarms: bs_teacher_id, bs_notice_content
     *  return: tMessage
     */
    public TMessage saveBsNotice(Long bs_teacher_id, String bs_notice_content);


    /*
     *  删除公告
     *  prarms: bs_notice_id
     *  return: tMessage
     */
    public TMessage deleteBsNotice(Long bs_notice_id);


    /*
     *  获取公告列表
     *  prarms: bs_teacher_id
     *  return: tMessage
     */
    public List<BsNoticeV2> getBsNotices(Long bs_teacher_id);

}
