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

public class BaseTeacher{

    private static final long serialVersionUID = 1L;

    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Long teacherId;

    private String teacherName;

    private Long teacherDeparementId;


    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getTeacherDeparementId() {
        return teacherDeparementId;
    }

    public void setTeacherDeparementId(Long teacherDeparementId) {
        this.teacherDeparementId = teacherDeparementId;
    }

    @Override
    public String toString() {
        return "BaseTeacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherDeparementId=" + teacherDeparementId +
                '}';
    }
}
