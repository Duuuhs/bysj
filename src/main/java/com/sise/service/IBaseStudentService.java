package com.sise.service;

import com.sise.bean.QueryStuMessageByMangerPage;
import com.sise.bean.StuMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sise.bean.TMessage;
import com.sise.entity.BaseStudent;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */
public interface IBaseStudentService extends IService<BaseStudent> {

    /*
     * 根据学号获取学生的基本信息
     */
    public StuMessage getStuMessage(Long student_id);


    /*
     * 部门管理员给学生分配别有设计老师
     */
    TMessage addBsTeacher(Long student_id, Long bysj_teacher_id);


    /*
     * 查询该系的学生的基本信息(分页)(DM)
     */
    QueryStuMessageByMangerPage getStuMessageByMangerPage(int page_num, Long base_department_id, String student_id, String student_name, String hasBsTea);


    /*
     * 查询该系的学生的基本信息(分页)(SM)
     */
    QueryStuMessageByMangerPage getStuMessageByMangerPage(int page_num,String student_id, String student_name, String hasBsTea);
}
