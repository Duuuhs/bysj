package com.sise.controller;


import com.sise.bean.BsTopicContent;
import com.sise.bean.QueryStuByTeaPage;
import com.sise.bean.StuBsChoiceV2;
import com.sise.bean.TeaQueryNotChoiceStu;
import com.sise.common.JsonUtil;
import com.sise.entity.BaseStudent;
import com.sise.mapper.BaseStudentMapper;
import com.sise.service.IBaseTeacherService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.isNumber;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */
@RestController
@RequestMapping("baseTeacher")
public class BaseTeacherController {

    Logger logger = Logger.getLogger(BaseTeacherController.class.getName());

    @Resource
    private IBaseTeacherService baseTeacherService;

    @Resource
    private BaseStudentMapper baseStudentMapper;



    /*
     * 根据教师id查找相关的毕业设计学生,进行分页,默认10条为一页
     * prarms: bysj_teacher_id, page_num(页码)
     */
    @RequestMapping(value = "getStuListByTea.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuListByTea(HttpServletRequest request, HttpServletResponse response,String bysj_teacher_id, String page_num){
        if (isNull(bysj_teacher_id)){
            logger.info("参数不足");
            JsonUtil.outPrint(response,"参数不足");
        } else {
            Long bysj_teacher_id_L = Long.parseLong(bysj_teacher_id);
            int page_num_I;
            if (isNull(page_num) || !isNumber(page_num)){
                page_num_I = 1;
            } else {
                page_num_I = Integer.parseInt(page_num);
                if (page_num_I < 1){
                    page_num_I = 1;
                }

            }
            QueryStuByTeaPage stuListByTea = baseTeacherService.getStuListByTea(bysj_teacher_id_L, page_num_I);
            JsonUtil.outPrint(response, stuListByTea);
        }
    }



    /*
     * 管理员查看教师带的毕业设计学生
     */
    @RequestMapping(value = "selectTeaBsStus.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void selectTeaBsStus(HttpServletRequest request, HttpServletResponse response, String teacher_id) {
        if (isNull(teacher_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "’参数不足！");
        } else {
            Long teacher_id_L = Long.parseLong(teacher_id);
            List<TeaQueryNotChoiceStu> teaQueryNotChoiceStus = baseStudentMapper.selectTeaBsStus(teacher_id_L);
            JsonUtil.outPrint(response, teaQueryNotChoiceStus);
        }
    }


    /*
     * 管理员查看教师的毕业设计题目
     */
    @RequestMapping(value = "selectTeaBsTopic.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void selectTeaBsTopic(HttpServletRequest request, HttpServletResponse response, String teacher_id) {
        if (isNull(teacher_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "’参数不足！");
        } else {
            Long teacher_id_L = Long.parseLong(teacher_id);
            List<BsTopicContent> bsTopicContents = baseStudentMapper.selectTeaBsTopic(teacher_id_L);
            JsonUtil.outPrint(response, bsTopicContents);

        }
    }


    }
