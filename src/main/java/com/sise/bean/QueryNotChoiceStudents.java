package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/2/27 16:14
 * @Description:
 */
public class QueryNotChoiceStudents {
    private Long bs_topic_id;       //题目id
    private String bs_topic_name;   //题目名称
    private List<TeaQueryNotChoiceStu> teaQueryNotChoiceStus;   //  未选题学生

    public Long getBs_topic_id() {
        return bs_topic_id;
    }

    public void setBs_topic_id(Long bs_topic_id) {
        this.bs_topic_id = bs_topic_id;
    }

    public String getBs_topic_name() {
        return bs_topic_name;
    }

    public void setBs_topic_name(String bs_topic_name) {
        this.bs_topic_name = bs_topic_name;
    }

    public List<TeaQueryNotChoiceStu> getTeaQueryNotChoiceStus() {
        return teaQueryNotChoiceStus;
    }

    public void setTeaQueryNotChoiceStus(List<TeaQueryNotChoiceStu> teaQueryNotChoiceStus) {
        this.teaQueryNotChoiceStus = teaQueryNotChoiceStus;
    }

    @Override
    public String toString() {
        return "QueryNotChoiceStudents{" +
                "bs_topic_id=" + bs_topic_id +
                ", bs_topic_name='" + bs_topic_name + '\'' +
                ", teaQueryNotChoiceStus=" + teaQueryNotChoiceStus +
                '}';
    }
}
