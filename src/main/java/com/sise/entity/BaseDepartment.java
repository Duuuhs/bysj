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

public class BaseDepartment{

    private static final long serialVersionUID = 1L;

    @TableId(value = "base_department_id", type = IdType.AUTO)
    private Long baseDepartmentId;

    private String baseDepartmentName;


    public Long getBaseDepartmentId() {
        return baseDepartmentId;
    }

    public void setBaseDepartmentId(Long baseDepartmentId) {
        this.baseDepartmentId = baseDepartmentId;
    }

    public String getBaseDepartmentName() {
        return baseDepartmentName;
    }

    public void setBaseDepartmentName(String baseDepartmentName) {
        this.baseDepartmentName = baseDepartmentName;
    }

    @Override
    public String toString() {
        return "BaseDepartment{" +
                "baseDepartmentId=" + baseDepartmentId +
                ", baseDepartmentName='" + baseDepartmentName + '\'' +
                '}';
    }
}
