package com.sise.controller;


import com.sise.bean.*;
import com.sise.common.JsonUtil;
import com.sise.entity.BsChoice;
import com.sise.entity.BsTopic;
import com.sise.mapper.BaseStudentMapper;
import com.sise.mapper.BsChoiceMapper;
import com.sise.service.IBaseStudentService;
import com.sise.service.IBaseTeacherService;
import com.sise.service.IBsChoiceService;
import com.sise.service.IBsTopicService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */
@RestController
@RequestMapping("bschoice")
public class BsChoiceController {

    Logger logger = Logger.getLogger(BsTopicController.class.getName());

    @Resource
    private IBsChoiceService bsChoiceService;
    
    @Resource
    private IBsTopicService bsTopicService;

    @Resource
    private BsChoiceMapper bsChoiceMapper;

    @Resource
    private IBaseStudentService baseStudentService;

    @Resource
    private IBaseTeacherService baseTeacherService;

    @Resource
    private BaseStudentMapper baseStudentMapper;

    /*
     * 根据student_id查询该学生的课题选择转态，以及评级意见
     */
    @RequestMapping(value = "getStuBsChoice",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuBsChoice(HttpServletRequest request, HttpServletResponse response, String student_id){
        if (isNull(student_id)){
            logger.info("学生id不能为空！");
            JsonUtil.outPrint(response, "学生id不能为空!");
        } else {
            StuBsChoice stuBsChoice =  new StuBsChoice();
            try {
                Long student_id_L = Long.parseLong(student_id);
                BsChoice bsChoice = bsChoiceService.getStuBsChoice(student_id_L);
                if (notNull(bsChoice)){
                    if (notNull(bsChoice.getBsChoiceId())){
                        stuBsChoice.setBsChoiceId(bsChoice.getBsChoiceId());
                    }
                    if (notNull(bsChoice.getStudentId())){
                        stuBsChoice.setStudentId(bsChoice.getStudentId());
                    }
                    if (notNull(bsChoice.getBsTopicId())){
                        stuBsChoice.setBsTopicId(bsChoice.getBsTopicId());
                    }
                    if (isNull(bsChoice.getIsSure()) || "F".equals(bsChoice.getIsSure())){
                        stuBsChoice.setIsSure("未确认");
                    } else {
                        stuBsChoice.setIsSure("已确认");
                    }
                    if (isNull(bsChoice.getGuidance())){
                        stuBsChoice.setGuidance("暂无教师意见");
                    } else {
                        stuBsChoice.setGuidance(bsChoice.getGuidance());
                    }
                    /*评阅分数*/
                    if (notNull(bsChoice.getReviewPoint())){
                        stuBsChoice.setReviewPoint(bsChoice.getReviewPoint());
                    }
                    /*答辩分数*/
                    if (notNull(bsChoice.getReplyPoint())){
                        stuBsChoice.setReplyPoint(bsChoice.getReplyPoint());
                    }
                    /* 毕设题目*/
                    if (notNull(bsChoice.getBsTopicId())){
                        BsTopic bsTopic = bsTopicService.getById(bsChoice.getBsTopicId());
                        if (notNull(bsTopic.getBsTopicName())){
                            stuBsChoice.setBsTopicName(bsTopic.getBsTopicName());
                        }
                    }

                }
                JsonUtil.outPrint(response, stuBsChoice);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JsonUtil.outPrint(response, "系统出错！");
            }



        }
    }



    /*
     * 根据bs_choice_id查询该评级意见
     */
    @RequestMapping(value = "getStuBsChoiceV2.do",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuBsChoiceV2(HttpServletRequest request, HttpServletResponse response, String bs_choice_id) {
        if (isNull(bs_choice_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            Long bs_choice_id_L = Long.parseLong(bs_choice_id);
            BsChoice bsChoice = bsChoiceMapper.selectById(bs_choice_id_L);
            JsonUtil.outPrint(response, bsChoice);
        }
    }




    /*
     * 保存学生的毕业设计评分意见
     * params： bs_choice_id
     */
    @RequestMapping(value = "saveStuBsChoice.do",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void saveStuBsChoice(HttpServletRequest request, HttpServletResponse response, String bs_choice_id,String review_point,String reply_point,String guidance) {
        if (isNull(bs_choice_id)){
            logger.info("系统出错！");
            JsonUtil.outPrint(response, "系统出错！");
        } else if (isNumber(reply_point) && isNumber(review_point)){
            Long bs_choice_id_L = Long.parseLong(bs_choice_id);
            Double reply_point_D = Double.parseDouble(reply_point);
            Double review_point_D = Double.parseDouble(review_point);
            TMessage tMessage = bsChoiceService.saveStuBsChoiceById(bs_choice_id_L, review_point_D, reply_point_D, guidance);
            JsonUtil.outPrint(response,tMessage);
        } else {
            logger.info("请确保输入的分数为数字格式！");
            TMessage tMessage = new TMessage();
            tMessage.setMessage("请确保输入的分数为数字格式!");
            JsonUtil.outPrint(response, tMessage);
        }
    }




    /*
     * 删除学生选题
     * params: student_id,bs_topic_id
     */
    @RequestMapping(value = "deleteStuBsChoice.do",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void deleteStuBsChoice(HttpServletRequest request, HttpServletResponse response, String student_id, String bs_topic_id){
        if (isNull(student_id) || isNull(bs_topic_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            Long student_id_L = Long.parseLong(student_id);
            Long bs_topic_id_L = Long.parseLong(bs_topic_id);
            TMessage tMessage = bsChoiceService.deleteStuBsChoice(student_id_L, bs_topic_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }


    /*
     * 获取教师的毕业设计题目
     * params: student_id,bs_topic_id
     */
    @RequestMapping(value = "getNotChoiceStuByTea.do",produces="application/json;charset=utf-8", method = RequestMethod.GET)
    public void getNotChoiceStuByTea(HttpServletRequest request, HttpServletResponse response, String bsTopicId, String bs_teacher_id) {
        if (isNull(bsTopicId) || isNull(bs_teacher_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            Long bsTopicId_L = Long.parseLong(bsTopicId);
            Long bs_teacher_id_L = Long.parseLong(bs_teacher_id);
            QueryNotChoiceStudents queryNotChoiceStudents = new QueryNotChoiceStudents();
            List<TeaQueryNotChoiceStu> notChoiceStu = bsChoiceService.getNotChoiceStu(bs_teacher_id_L);
            queryNotChoiceStudents.setTeaQueryNotChoiceStus(notChoiceStu);
            BsTopic bsTopic = bsTopicService.getById(bsTopicId_L);
            if (notNull(bsTopic)){
                queryNotChoiceStudents.setBs_topic_id(bsTopic.getBsTopicId());
                queryNotChoiceStudents.setBs_topic_name(bsTopic.getBsTopicName());
            }
            JsonUtil.outPrint(response, queryNotChoiceStudents);
        }
    }



    /*
     *  根据学生id查找该系所有老师(非辅导员,辅导员是1开头,教师是2开头)
     */
    @RequestMapping(value = "getTeaListByStu.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getTeaListByStu(HttpServletRequest request, HttpServletResponse response, String student_id) {
        if (isNull(student_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "参数不足!");
        } else {
            List<QueryTeaList> queryTeaLists = baseStudentMapper.selectTeaList(Long.parseLong(student_id));
            JsonUtil.outPrint(response, queryTeaLists);
        }
    }




    /*
     *查看教师下学生的选题状态(评分意见模块)(分页)
     * params:bysj_teacher_id
     */
    @RequestMapping(value = "getStuChoiceStatus.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuChoiceStatus(HttpServletRequest request, HttpServletResponse response, String page_num, String bysj_teacher_id){
        if (isNull(bysj_teacher_id)){
             JsonUtil.outPrint(response, "参数不足!");
        } else {
            Long bysj_teacher_id_L = Long.parseLong(bysj_teacher_id);
            int page_num_I;
            if (isNull(page_num) || !isNumber(page_num)) {
                page_num_I = 1;
            } else {
                page_num_I = Integer.parseInt(page_num);
                if (page_num_I < 1) {
                    page_num_I = 1;
                }
            }
            QueryStuBsChoiceStatusPage stuBsChoiceStatusPage = bsChoiceService.getStuBsChoiceStatusPage(page_num_I, bysj_teacher_id_L);
            JsonUtil.outPrint(response, stuBsChoiceStatusPage);
        }
    }



    /*
     *查看该系学生的基本信息(分页)(DM)
     * params:bysj_teacher_id
     */
    @RequestMapping(value = "getStuMessageByManger.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuMessageByManger(HttpServletRequest request, HttpServletResponse response, String page_num, String base_department_id, String student, String hasBsTea){
        String student_id = null;
        String student_name = null;
        if (isNull(base_department_id)){
            logger.info("参数为空！");
            JsonUtil.outPrint(response, "参数为空！");
            return;
        }
        if (student != null && !isNumber(student)){
            student_name = student;
        } else if (notNull(student) && isNumber(student)) {
            student_id = student;
        }
        Long base_department_id_L = Long.parseLong(base_department_id);
        int page_num_I;
        if (isNull(page_num) || !isNumber(page_num)) {
            page_num_I = 1;
        } else {
            page_num_I = Integer.parseInt(page_num);
            if (page_num_I < 1) {
                page_num_I = 1;
            }
        }

        QueryStuMessageByMangerPage stuMessageByMangerPage = baseStudentService.getStuMessageByMangerPage(page_num_I, base_department_id_L, student_id, student_name, hasBsTea);
        JsonUtil.outPrint(response, stuMessageByMangerPage);

        }


    /*
     *查看该系学生的基本信息(分页)(SM)
     * params:bysj_teacher_id
     */
    @RequestMapping(value = "getStuMessageByMangerV2.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuMessageByMangerV2(HttpServletRequest request, HttpServletResponse response, String page_num, String student, String hasBsTea){
        String student_id = null;
        String student_name = null;
        if (notNull(student) && isNumber(student)){
            student_id = student;
        } else if (notNull(student) && !isNumber(student)){
            student_name = student;
        }
        int page_num_I;
        if (isNull(page_num) || !isNumber(page_num)) {
            page_num_I = 1;
        } else {
            page_num_I = Integer.parseInt(page_num);
            if (page_num_I < 1) {
                page_num_I = 1;
            }
        }

        QueryStuMessageByMangerPage stuMessageByMangerPage = baseStudentService.getStuMessageByMangerPage(page_num_I, student_id, student_name, hasBsTea);
        JsonUtil.outPrint(response, stuMessageByMangerPage);

    }




    /*
     *查看教师的基本信息(分页)(DM/SM)
     * params:bysj_teacher_id
     */
    @RequestMapping(value = "getTeaMessageByManger.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getTeaMessageByManger(HttpServletRequest request, HttpServletResponse response, String page_num, String base_department_id) {
        int page_num_I;
        if (isNull(page_num) || !isNumber(page_num)) {
            page_num_I = 1;
        } else {
            page_num_I = Integer.parseInt(page_num);
            if (page_num_I < 1) {
                page_num_I = 1;
            }
        }
        Long base_department_id_L = null;
        if (notNull(base_department_id)){
            base_department_id_L = Long.parseLong(base_department_id);
        }
        QueryTeaByMangerPage teaMessageByManger = baseTeacherService.getTeaMessageByManger(base_department_id_L, page_num_I);
        JsonUtil.outPrint(response, teaMessageByManger);

    }

}
