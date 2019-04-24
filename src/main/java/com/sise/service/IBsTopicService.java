package com.sise.service;

import com.sise.bean.*;
import com.sise.entity.BsTopic;
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
public interface IBsTopicService extends IService<BsTopic> {

    /*
     *  改变毕业设计题目的is_Choice属性
     *  params: bs_topic_id, is_choice(T/F)
     *  return: T/F
     */
    String changeBsChoice(Long bs_topic_id, String is_choice);



    /*
     *  查询该教师布置了多少条毕设题目
     *  params: teacher_id, student_id
     */
    TeaBsTopics getTeaBsTopics(Long bs_teacher_id);


    /*
     *  查询该教师布置了多少条毕设题目,和该学生是否已经选题，教师是否确认题目
     *  params: teacher_id, student_id
     */
    StuQueryTeaBsTopics getTeaBsTopicsV2(Long bs_teacher_id, Long student_id);


    /*
     *  选择毕业设计题目
     *  prarms: bs_topic_id, student_id
     */
    TMessage saveOrUpdateStuChoice(Long bs_topic_id, Long student_id);


    /*
     *  删除无人选择的毕业设计题目
     *  prarms: bs_topic_id
     */
    TMessage deleteBsTopic(Long bs_topic_id);



    /*
     *  添加毕业设计题目
     *  prarms: bs_topic_id
     */
    TMessage addBsTopic(String bs_topic_name, Long bs_teacher_id);



    /*
     * 教师确认毕业设计题目
     *  prarms: bs_topic_id
     */
    TMessage sureBsTopic(Long bs_topic_id);


    /*
     * 教师取消确认过的毕业设计题目
     *  prarms: bs_topic_id,student_id
     */
    TMessage noSureBsTopic(Long bs_topic_id, Long student_id);




    /*
     * 根据教师id查找相关的毕业设计题目及相关选题状况，进行分页,默认5条为一页
     * prarms: bysj_teacher_id, page_num(页码)
     */
    QueryTopicByTeaPage getTopicListByTea(Long bs_teacher_id, int page_num);





}
