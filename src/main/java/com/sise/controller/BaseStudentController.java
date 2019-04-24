package com.sise.controller;


import com.sise.bean.QueryStuMessageByMangerPage;
import com.sise.bean.TMessage;
import com.sise.common.JsonUtil;
import com.sise.service.IBaseStudentService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("basestudent")
public class BaseStudentController{

    Logger logger = Logger.getLogger(BaseStudentController.class.getName());

    @Resource
    private IBaseStudentService baseStudentService;


    /*
     * 部门管理员给学生分配别有设计老师
     */
    @RequestMapping(value = "addBsTeacher.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void addBsTeacher(HttpServletRequest request, HttpServletResponse response, String student_id, String bysj_teacher_id) {
        if (isNull(student_id) || isNull(bysj_teacher_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            Long student_id_L = Long.parseLong(student_id);
            Long bysj_teacher_id_L = Long.parseLong(bysj_teacher_id);
            TMessage tMessage = baseStudentService.addBsTeacher(student_id_L, bysj_teacher_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }


        /*
     *查看该系学生的基本信息(分页)
     * params:bysj_teacher_id
     */
    /*@RequestMapping(value = "getStuMessageByMangerPage.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public void getStuMessageByMangerPage(HttpServletRequest request, HttpServletResponse response, String page_num, String base_department_id){
        if (isNull(base_department_id)){
            logger.info("参数不足！");
            JsonUtil.outPrint(response,"参数不足!");
        } else {
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
            QueryStuMessageByMangerPage stuMessageByMangerPage = baseStudentService.getStuMessageByMangerPage(page_num_I, base_department_id_L);
            JsonUtil.outPrint(response, stuMessageByMangerPage);
        }
    }*/


    }
