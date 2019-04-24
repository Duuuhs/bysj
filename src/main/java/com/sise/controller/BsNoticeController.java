package com.sise.controller;


import com.sise.bean.BsNoticeV2;
import com.sise.bean.TMessage;
import com.sise.bean.TeaBsTopics;
import com.sise.common.JsonUtil;
import com.sise.service.IBsNoticeService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */
@RestController
@RequestMapping("bsNotice")
public class BsNoticeController {

    Logger logger = Logger.getLogger(BsNoticeController.class.getName());

    @Resource
    private IBsNoticeService bsNoticeService;


    /*
     *  新增公告
     *  prarms: bs_teacher_id, bs_notice_content
     */
    @RequestMapping(value = "saveBsNotice.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void saveBsNotice(HttpServletRequest request, HttpServletResponse response, String bs_teacher_id, String bs_notice_content) {
        if (isNull(bs_teacher_id) || isNull(bs_notice_content)){
            logger.info("教师id不能为空！");
            JsonUtil.outPrint(response, "教师id不能为空!");
        } else {
            Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
            TMessage tMessage = bsNoticeService.saveBsNotice(bs_teacher_id_L, bs_notice_content);
            JsonUtil.outPrint(response, tMessage);
        }
    }


    /*
     *  删除公告
     *  prarms: bs_notice_id
     */
    @RequestMapping(value = "deleteBsNotice.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void deleteBsNotice(HttpServletRequest request, HttpServletResponse response, String bs_notice_id) {
        if (isNull(bs_notice_id)){
            logger.info("公告id不能为空！");
            JsonUtil.outPrint(response, "公告id不能为空!");
        } else {
            Long bs_notice_id_L = Long.parseLong(bs_notice_id);
            TMessage tMessage = bsNoticeService.deleteBsNotice(bs_notice_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }


    /*
     *  获取公告列表
     *  prarms: bs_teacher_id
     */
    @RequestMapping(value = "getBsNotices.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getBsNotices(HttpServletRequest request, HttpServletResponse response, String bs_teacher_id) {
        if (isNull(bs_teacher_id)){
            logger.info("教师id不能为空！");
            JsonUtil.outPrint(response, "教师id不能为空!");
        } else {
            Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
            List<BsNoticeV2> bsNotices = bsNoticeService.getBsNotices(bs_teacher_id_L);
            JsonUtil.outPrint(response, bsNotices);
        }
    }


}
