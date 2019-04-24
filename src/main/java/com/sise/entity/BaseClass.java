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

public class BaseClass {

    private static final long serialVersionUID = 1L;

    @TableId(value = "base_class_id", type = IdType.AUTO)
    private Long baseClassId;

    private String baseClassName;

    private Long baseDepartmentId;


    public Long getBaseClassId() {
        return baseClassId;
    }

    public void setBaseClassId(Long baseClassId) {
        this.baseClassId = baseClassId;
    }

    public String getBaseClassName() {
        return baseClassName;
    }

    public void setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
    }

    public Long getBaseDepartmentId() {
        return baseDepartmentId;
    }

    public void setBaseDepartmentId(Long baseDepartmentId) {
        this.baseDepartmentId = baseDepartmentId;
    }

    @Override
    public String toString() {
        return "BaseClass{" +
                "baseClassId=" + baseClassId +
                ", baseClassName='" + baseClassName + '\'' +
                ", baseDepartmentId=" + baseDepartmentId +
                '}';
    }
}
