package com.sise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author DMY
 * @since 2019-03-01
 */

public class BaseManger {

    private static final long serialVersionUID = 1L;

    @TableId(value = "manger_id", type = IdType.AUTO)
    private Long mangerId;

    private String mangerName;

    private String mangerPassword;

    private String mangerIdentity;

    /**
     * 所在系（除超级管理员之外）
     */
    private Long mangerDepartment;

    public Long getMangerId() {
        return mangerId;
    }

    public void setMangerId(Long mangerId) {
        this.mangerId = mangerId;
    }

    public String getMangerName() {
        return mangerName;
    }

    public void setMangerName(String mangerName) {
        this.mangerName = mangerName;
    }

    public String getMangerPassword() {
        return mangerPassword;
    }

    public void setMangerPassword(String mangerPassword) {
        this.mangerPassword = mangerPassword;
    }

    public String getMangerIdentity() {
        return mangerIdentity;
    }

    public void setMangerIdentity(String mangerIdentity) {
        this.mangerIdentity = mangerIdentity;
    }

    public Long getMangerDepartment() {
        return mangerDepartment;
    }

    public void setMangerDepartment(Long mangerDepartment) {
        this.mangerDepartment = mangerDepartment;
    }

    @Override
    public String toString() {
        return "BaseManger{" +
                "mangerId=" + mangerId +
                ", mangerName='" + mangerName + '\'' +
                ", mangerPassword='" + mangerPassword + '\'' +
                ", mangerIdentity='" + mangerIdentity + '\'' +
                ", mangerDepartment=" + mangerDepartment +
                '}';
    }
}
