package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/2 15:02
 * @Description:
 */
public class StuMessageByManger {

    private long studentId;             //学号
    private String studentName;         //学生姓名
    private String majorName;           //专业方向名称
    private String baseClassName;       //班级姓名
    private String studyTeacherName;    //学习老师姓名
    private String tutorTeacherName;    //辅导员姓名
    private String bysjTeacherName;     //毕业设计老师名字

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBaseClassName() {
        return baseClassName;
    }

    public void setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
    }

    public String getStudyTeacherName() {
        return studyTeacherName;
    }

    public void setStudyTeacherName(String studyTeacherName) {
        this.studyTeacherName = studyTeacherName;
    }

    public String getTutorTeacherName() {
        return tutorTeacherName;
    }

    public void setTutorTeacherName(String tutorTeacherName) {
        this.tutorTeacherName = tutorTeacherName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getBysjTeacherName() {
        return bysjTeacherName;
    }

    public void setBysjTeacherName(String bysjTeacherName) {
        this.bysjTeacherName = bysjTeacherName;
    }

    @Override
    public String toString() {
        return "StuMessageByManger{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", baseClassName='" + baseClassName + '\'' +
                ", studyTeacherName='" + studyTeacherName + '\'' +
                ", tutorTeacherName='" + tutorTeacherName + '\'' +
                ", bysjTeacherName='" + bysjTeacherName + '\'' +
                '}';
    }
}
