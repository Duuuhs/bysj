package com.sise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author DMY
 * @since 2019-03-03
 */

public class BaseMajor  {

    private static final long serialVersionUID = 1L;

    /**
     * 专业方向id
     */
    @TableId(value = "major_id", type = IdType.AUTO)
    private Long majorId;

    /**
     * 专业方向名称
     */
    private String majorName;

    private Long majroDepartmentId;

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Long getMajroDepartmentId() {
        return majroDepartmentId;
    }

    public void setMajroDepartmentId(Long majroDepartmentId) {
        this.majroDepartmentId = majroDepartmentId;
    }

    @Override
    public String toString() {
        return "BaseMajor{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                ", majroDepartmentId=" + majroDepartmentId +
                '}';
    }
}
