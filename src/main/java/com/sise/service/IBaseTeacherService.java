package com.sise.service;

import com.sise.bean.*;
import com.sise.entity.BaseStudent;
import com.sise.entity.BaseTeacher;
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
public interface IBaseTeacherService extends IService<BaseTeacher> {

    /*
     * 获取教师的基本信息
     */
    TeaMessage getTeaMessage(Long teacher_id);




    /*
     * 根据教师id查找相关的毕业设计学生，进行分页,默认5条为一页
     * prarms: bysj_teacher_id, page_num(页码)
     */
    QueryStuByTeaPage getStuListByTea(Long bysj_teacher_id, int page_num);



    /*
     *查找教师基本信息以及所带毕业生列表(DM/SM)(分页)
     */
    QueryTeaByMangerPage getTeaMessageByManger(Long base_department_id, int page_num);
}
