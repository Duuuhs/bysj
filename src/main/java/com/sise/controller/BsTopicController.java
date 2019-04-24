package com.sise.controller;



import com.sise.bean.QueryTopicByTeaPage;
import com.sise.bean.StuQueryTeaBsTopics;
import com.sise.bean.TMessage;
import com.sise.bean.TeaBsTopics;
import com.sise.common.JsonUtil;
import com.sise.service.IBsTopicService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.isNumber;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */
@RestController
@RequestMapping("bstopic")
public class BsTopicController{

    Logger logger = Logger.getLogger(BsTopicController.class.getName());

    @Resource
    private IBsTopicService bsTopicService;

    /*
     * 获取教师布置的毕业设计题目以及条数,需要从前端获得bs_teacher_id
     */
    @RequestMapping(value = "getTeaBsTopics",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getTeaBsTopics(HttpServletRequest request, HttpServletResponse response, String bs_teacher_id){
        if (isNull(bs_teacher_id)){
            logger.info("教师id不能为空！");
            JsonUtil.outPrint(response, "教师id不能为空!");
        } else {
            Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
            TeaBsTopics teaBsTopics = bsTopicService.getTeaBsTopics(bs_teacher_id_L);
            JsonUtil.outPrint(response, teaBsTopics);
        }

    }


    /*
     * 获取教师布置的毕业设计题目以及条数,和该学生是否已经选题，教师是否确认题目
     * params:bs_teacher_id,student_id
     */
    @RequestMapping(value = "getTeaBsTopicsV2",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getTeaBsTopicsV2(HttpServletRequest request, HttpServletResponse response, String bs_teacher_id, String student_id){
        if (isNull(bs_teacher_id) || isNull(student_id)){
            logger.info("教师id或者学生id不能为空！");
            JsonUtil.outPrint(response, "教师id或者学生id不能为空!");
        } else {
            Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
            Long student_id_L = Long.parseLong(student_id);
            StuQueryTeaBsTopics teaBsTopicsV2 = bsTopicService.getTeaBsTopicsV2(bs_teacher_id_L, student_id_L);
            JsonUtil.outPrint(response, teaBsTopicsV2);
        }

    }


    /*
     *  选择毕业设计题目(教师已确认无法选题在前端进行控制！)
     */
    @RequestMapping(value = "saveOrUpdateStuChoice.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void saveOrUpdateStuChoice(HttpServletRequest request, HttpServletResponse response,String bs_topic_id, String student_id){
        if (isNull(bs_topic_id) || isNull(student_id)){
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足");
        } else {
            Long bs_topic_id_L = Long.parseLong(bs_topic_id);
            Long student_id_L = Long.parseLong(student_id);
            TMessage tMessage = bsTopicService.saveOrUpdateStuChoice(bs_topic_id_L, student_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }



    /*
     *  删除无人选择的毕业设计题目
     *  prarms: bs_topic_id
     */
    @RequestMapping(value = "deleteBsTopic.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void deleteBsTopic(HttpServletRequest request, HttpServletResponse response, String bs_topic_id){
        if (isNull(bs_topic_id)){
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足");
        } else {
            Long bs_topic_id_L = Long.parseLong(bs_topic_id);
            TMessage tMessage = bsTopicService.deleteBsTopic(bs_topic_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }




    /*
     *  新增毕业设计题目
     *  prarms: bs_topic_name, bs_teacher_id
     */
    @RequestMapping(value = "addBsTopic.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void addBsTopic(HttpServletRequest request, HttpServletResponse response, String bs_topic_name, String bs_teacher_id){
            if (isNull(bs_topic_name) || isNull(bs_teacher_id)){
                logger.info("参数不足");
                JsonUtil.outPrint(response, "参数不足");
            } else {
                Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
                TMessage tMessage = bsTopicService.addBsTopic(bs_topic_name, bs_teacher_id_L);
                JsonUtil.outPrint(response, tMessage);
            }

    }



    /*
     *  确认毕业设计题目
     *  prarms: bs_topic_id
     */
    @RequestMapping(value = "sureBsTopic.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void sureBsTopic(HttpServletRequest request, HttpServletResponse response, String bs_topic_id){
        if (isNull(bs_topic_id)){
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足");
        } else {
            Long bs_topic_id_L = Long.parseLong(bs_topic_id);
            TMessage tMessage = bsTopicService.sureBsTopic(bs_topic_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }



    /*
     *  取消确认毕业设计题目
     *  prarms: bs_topic_id
     */
    @RequestMapping(value = "noSureBsTopic.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void noSureBsTopic(HttpServletRequest request, HttpServletResponse response, String bs_topic_id, String student_id){
        if (isNull(bs_topic_id) || isNull(student_id)){
            logger.info("参数不足");
            TMessage tMessage = new TMessage();
            tMessage.setMessage("参数不足");
            JsonUtil.outPrint(response, tMessage);
        } else {
            Long bs_topic_id_L = Long.parseLong(bs_topic_id);
            Long student_id_L = Long.parseLong(student_id);
            TMessage tMessage = bsTopicService.noSureBsTopic(bs_topic_id_L, student_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }



    /*
     * 根据教师id查找相关的毕业设计题目及相关选题状况，进行分页,默认10条为一页
     * prarms: bysj_teacher_id, page_num(页码)
     */
    @RequestMapping(value = "getTopicListByTea.do", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getTopicListByTea(HttpServletRequest request, HttpServletResponse response, String bs_teacher_id, String page_num) {
        if (isNull(bs_teacher_id)) {
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足");
        } else {
            Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
            int page_num_I;
            if (isNull(page_num) || !isNumber(page_num)) {
                page_num_I = 1;
            } else {
                page_num_I = Integer.parseInt(page_num);
                if (page_num_I < 1) {
                    page_num_I = 1;
                }
            }
            QueryTopicByTeaPage topicListByTea = bsTopicService.getTopicListByTea(bs_teacher_id_L, page_num_I);
            JsonUtil.outPrint(response, topicListByTea);
        }
    }




}
