package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/13 12:05
 * @Description:
 */
public class StuMessage {

    private String student_sex;            //性别
    private Long class_id;                 //班级编号
    private String class_name;             //班级名称
    private Long department_id;            //系别编号
    private String departmenr_name;        //系别名称
    private Long major_id;                 //专业编号
    private String major_name;             //专业名称
    private Long study_teacher_id;         //学习指导老师编号
    private String study_teacher_name;     //学习指导老师姓名
    private long tutor_teacher_id;         //辅导员编号
    private String tutor_teacher_name;     //辅导员姓名
    private Long bysj_teacher_id;          //毕业设计指导老师编号
    private String bysj_teacher_name;      //毕业设计指导老师姓名


    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Long getBysj_teacher_id() {
        return bysj_teacher_id;
    }

    public void setBysj_teacher_id(Long bysj_teacher_id) {
        this.bysj_teacher_id = bysj_teacher_id;
    }

    public String getBysj_teacher_name() {
        return bysj_teacher_name;
    }

    public void setBysj_teacher_name(String bysj_teacher_name) {
        this.bysj_teacher_name = bysj_teacher_name;
    }

    public String getStudent_sex() {
        return student_sex;
    }

    public void setStudent_sex(String student_sex) {
        this.student_sex = student_sex;
    }

    public Long getMajor_id() {
        return major_id;
    }

    public void setMajor_id(Long major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public Long getStudy_teacher_id() {
        return study_teacher_id;
    }

    public void setStudy_teacher_id(Long study_teacher_id) {
        this.study_teacher_id = study_teacher_id;
    }

    public String getStudy_teacher_name() {
        return study_teacher_name;
    }

    public void setStudy_teacher_name(String study_teacher_name) {
        this.study_teacher_name = study_teacher_name;
    }

    public String getTutor_teacher_name() {
        return tutor_teacher_name;
    }

    public void setTutor_teacher_name(String tutor_teacher_name) {
        this.tutor_teacher_name = tutor_teacher_name;
    }

    public long getTutor_teacher_id() {
        return tutor_teacher_id;
    }

    public void setTutor_teacher_id(long tutor_teacher_id) {
        this.tutor_teacher_id = tutor_teacher_id;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartmenr_name() {
        return departmenr_name;
    }

    public void setDepartmenr_name(String departmenr_name) {
        this.departmenr_name = departmenr_name;
    }

    @Override
    public String toString() {
        return "StuMessage{" +
                "student_sex='" + student_sex + '\'' +
                ", class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                ", department_id=" + department_id +
                ", departmenr_name='" + departmenr_name + '\'' +
                ", major_id=" + major_id +
                ", major_name='" + major_name + '\'' +
                ", study_teacher_id=" + study_teacher_id +
                ", study_teacher_name='" + study_teacher_name + '\'' +
                ", tutor_teacher_id=" + tutor_teacher_id +
                ", tutor_teacher_name='" + tutor_teacher_name + '\'' +
                ", bysj_teacher_id=" + bysj_teacher_id +
                ", bysj_teacher_name='" + bysj_teacher_name + '\'' +
                '}';
    }
}
