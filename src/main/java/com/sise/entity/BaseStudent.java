package com.sise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */

public class BaseStudent{

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "student_id", type = IdType.AUTO)
    private Long studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    private Long studentSex;

    private Long classId;

    /**
     * 系别
     */
    private Long depatrmentId;

    /**
     * 专业方向
     */
    private Long majorId;

    /**
     * 学习指导老师
     */
    private Long studyTeacherId;

    /**
     * 辅导员
     */
    private Long tutorTeacherId;

    /**
     * 毕业设计指导老师
     */
    private Long bysjTeacherId;


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Long studentSex) {
        this.studentSex = studentSex;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getDepatrmentId() {
        return depatrmentId;
    }

    public void setDepatrmentId(Long depatrmentId) {
        this.depatrmentId = depatrmentId;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Long getStudyTeacherId() {
        return studyTeacherId;
    }

    public void setStudyTeacherId(Long studyTeacherId) {
        this.studyTeacherId = studyTeacherId;
    }

    public Long getTutorTeacherId() {
        return tutorTeacherId;
    }

    public void setTutorTeacherId(Long tutorTeacherId) {
        this.tutorTeacherId = tutorTeacherId;
    }

    public Long getBysjTeacherId() {
        return bysjTeacherId;
    }

    public void setBysjTeacherId(Long bysjTeacherId) {
        this.bysjTeacherId = bysjTeacherId;
    }

    @Override
    public String toString() {
        return "BaseStudent{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentSex=" + studentSex +
                ", classId=" + classId +
                ", depatrmentId=" + depatrmentId +
                ", majorId=" + majorId +
                ", studyTeacherId=" + studyTeacherId +
                ", tutorTeacherId=" + tutorTeacherId +
                ", bysjTeacherId=" + bysjTeacherId +
                '}';
    }
}
