package com.sise.service;

import com.sise.bean.LoginAuth;
import com.sise.bean.TMessage;
import com.sise.bean.UpdateStuInfo;
import com.sise.entity.Login;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-01-08
 */
public interface ILoginService extends IService<Login> {

    /*
     * 用户登录
     */
     LoginAuth getUserMessage(String usename, String password, String identity);

    /**
     * 根据用户身份跟id获取用户的用户名
     */
     String getUserName(Long loginId, String identity);


    /*
     * 添加系统用户
     */
    TMessage addUser(Long add_userId, String add_username, String add_identity, Long add_departmentId);


    /*
     * 完善学生的基本信息
     */
    UpdateStuInfo completeStuInfo(String message);



    /*
     *保存学生完善的信息
     */
    TMessage savecompleteStuInfo(Long student_id, Long student_sex, Long class_id, Long major_id, Long study_teacher_id, Long tutor_teacher_id);
}
