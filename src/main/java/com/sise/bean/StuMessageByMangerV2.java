package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/3 12:05
 * @Description:
 */
public class StuMessageByMangerV2 {
    private long studentId;             //学号
    private String studentName;         //学生姓名
    private String baseDepartmentName;  //系别
    private String studyTeacherName;    //学习老师姓名
    private String tutorTeacherName;    //辅导员姓名
    private String bysjTeacherName;     //毕业设计老师

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

    public String getBaseDepartmentName() {
        return baseDepartmentName;
    }

    public void setBaseDepartmentName(String baseDepartmentName) {
        this.baseDepartmentName = baseDepartmentName;
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

    @Override
    public String toString() {
        return "StuMessageByMangerV2{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", baseDepartmentName='" + baseDepartmentName + '\'' +
                ", studyTeacherName='" + studyTeacherName + '\'' +
                ", tutorTeacherName='" + tutorTeacherName + '\'' +
                '}';
    }
}
