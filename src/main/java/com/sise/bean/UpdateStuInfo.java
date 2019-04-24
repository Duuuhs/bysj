package com.sise.bean;

import com.sise.entity.BaseClass;
import com.sise.entity.BaseMajor;
import com.sise.entity.BaseTeacher;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/3 23:22
 * @Description:
 */
public class UpdateStuInfo {
    private Long student_id;            //学号
    private String student_name;        //姓名
    private List<BaseClass> classess;    //班级
    private List<BaseMajor> majors;     //专业方向
    private List<BaseTeacher> teachers; //教师

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public List<BaseClass> getClassess() {
        return classess;
    }

    public void setClassess(List<BaseClass> classess) {
        this.classess = classess;
    }

    public List<BaseMajor> getMajors() {
        return majors;
    }

    public void setMajors(List<BaseMajor> majors) {
        this.majors = majors;
    }

    public List<BaseTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<BaseTeacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "UpdateStuInfo{" +
                "student_id=" + student_id +
                ", student_name='" + student_name + '\'' +
                ", classess=" + classess +
                ", majors=" + majors +
                ", teachers=" + teachers +
                '}';
    }
}
