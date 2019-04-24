package com.sise.controller;


import com.google.gson.JsonObject;
import com.sise.bean.*;
import com.sise.common.JsonUtil;
import com.sise.entity.Login;
import com.sise.service.IBaseMangerService;
import com.sise.service.IBaseStudentService;
import com.sise.service.IBaseTeacherService;
import com.sise.service.ILoginService;
import com.sise.service.impl.LoginServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.isNumber;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DMY
 * @since 2019-01-08
 */
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private ILoginService loginService;

    @Resource
    private IBaseStudentService baseStudentService;

    @Resource
    private IBaseTeacherService baseTeacherService;

    @Resource
    private IBaseMangerService baseMangerService;

    Logger logger = Logger.getLogger(LoginController.class.getName());

    private Map<String, Object> model = new HashMap<String, Object>();




    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, String username, String password, String identity) {

        if (isNull(username) || isNull(password) || isNull(identity)) {
            logger.info("参数不足");
            model.put("message", "参数不足！");
            return new ModelAndView("index", model);
        } else {
            LoginAuth userMessage = loginService.getUserMessage(username, password, identity);
            //用户登录失败
            if (isNull(userMessage.getLoginId())) {
                logger.info(userMessage.getMessage());
                model.put("message", userMessage.getMessage());
                return  new ModelAndView("index", model);
            }else {
                logger.info(userMessage.getMessage());
                model.put("message", userMessage.getMessage());
                model.put("userMessage", userMessage);
                //获取学生的基本信息
                if ("S".equals(identity)){
                    StuMessage stuMessage = baseStudentService.getStuMessage(Long.parseLong(username));
                    model.put("stuMessage", stuMessage);
                }
                //获取教师的基本信息
                if ("T".equals(identity)){
                    TeaMessage teaMessage = baseTeacherService.getTeaMessage(Long.parseLong(username));
                    model.put("teaMessage", teaMessage);
                }
                //获取部门管理员的基本信息
                if ("DM".equals(identity)){
                    Long department_id = userMessage.getDepartment();
                    DmMessage dmMessage = baseMangerService.getDmMessage(department_id);
                    model.put("dmMessage", dmMessage);

                }
                //获取超级管理员的基本信息
                if ("SM".equals(identity)){
                    SmManger smMessage = baseMangerService.getSmMessage();
                    model.put("smMessage", smMessage);
                }
                return  new ModelAndView("main", model);

            }


        }

    }




    /*
     * 添加系统用户
     */
    @RequestMapping(value = "addUser.do", method = RequestMethod.GET)
    public void addUser(HttpServletRequest request, HttpServletResponse response, String add_userId, String add_username, String add_identity, String add_departmentId) {
        if (isNull(add_userId) || !isNumber(add_userId) || isNull(add_identity) || isNull(add_departmentId)){
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足！");
        } else {
            Long add_userId_L = Long.parseLong(add_userId);
            Long add_departmentId_L = Long.parseLong(add_departmentId);
            TMessage tMessage = loginService.addUser(add_userId_L, add_username, add_identity, add_departmentId_L);
            JsonUtil.outPrint(response, tMessage);
        }

    }




    /*
     * 完善学生的基本信息
     */
    @RequestMapping(value = "getcompleteStuInfo.do", method = RequestMethod.GET)
    public void getcompleteStuInfo(HttpServletRequest request, HttpServletResponse response, String message) {
        if (isNull(message)){
            logger.info("参数不足");
            JsonUtil.outPrint(response, "参数不足");
        } else {
            UpdateStuInfo updateStuInfo = loginService.completeStuInfo(message);
            JsonUtil.outPrint(response, updateStuInfo);
        }
    }



    /*
     *保存学生完善的信息
     */
    @RequestMapping(value = "savecompleteStuInfo.do", method = RequestMethod.GET)
    public void savecompleteStuInfo(HttpServletRequest request, HttpServletResponse response, String student_id, String student_sex, String class_id, String major_id, String study_teacher_id, String tutor_teacher_id) {
        if (isNull(student_id) || isNull(student_sex) ||isNull(class_id) || isNull(major_id) || isNull(study_teacher_id) || isNull(tutor_teacher_id)){
            logger.info("参数不足！");
            TMessage tMessage = new TMessage();
            tMessage.setMessage("参数不足！");
            JsonUtil.outPrint(response, tMessage);
        } else {
            Long student_id_L = Long.parseLong(student_id);
            Long student_sex_L = Long.parseLong(student_sex);
            Long class_id_L = Long.parseLong(class_id);
            Long major_id_L = Long.parseLong(major_id);
            Long study_teacher_id_L = Long.parseLong(study_teacher_id);
            Long tutor_teacher_id_L = Long.parseLong(tutor_teacher_id);
            TMessage tMessage = loginService.savecompleteStuInfo(student_id_L, student_sex_L, class_id_L, major_id_L, study_teacher_id_L, tutor_teacher_id_L);
            JsonUtil.outPrint(response, tMessage);
        }
    }
}
