package com.sise.service;

import com.sise.bean.QueryStuBsChoiceStatusPage;
import com.sise.bean.TMessage;
import com.sise.bean.TeaQueryNotChoiceStu;
import com.sise.entity.BsChoice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */
public interface IBsChoiceService extends IService<BsChoice> {

    /*
     *  根据student_id查询该学生的课题选择转态，以及评级意见
     */
    BsChoice getStuBsChoice(Long student_id);



    /*
     *  删除学生选题
     */
    TMessage deleteStuBsChoice(Long student_id, Long bs_topic_id);



    /*
     *  查询该教师下未选题学生
     *  params：bysj_teacher_id
     */
    List<TeaQueryNotChoiceStu> getNotChoiceStu(Long bysj_teacher_id);




    /*
     *查看教师下学生的选题状态(评分意见模块)(分页)
     * params:bysj_teacher_id, page_num
     */
    QueryStuBsChoiceStatusPage getStuBsChoiceStatusPage(int page_num, Long bysj_teacher_id);


    /*
     * 保存学生的毕业设计评分意见
     * params： bs_choice_id
     */
    TMessage saveStuBsChoiceById(Long bs_choice_id,Double review_point,Double reply_point,String guidance);
}
