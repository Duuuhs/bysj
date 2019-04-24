package com.sise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * <p>
 * 
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */

public class Login  {

    private static final long serialVersionUID = 1L;

    /**
     * 登录id
     */
    @TableId(value = "login_id", type = IdType.AUTO)
    private Long loginId;

    /**
     * 帐号，与学号，工号相对应
     */
    private Long userId;

    private String password;

    /**
     * 身份（S：学生；T：教师；DM：部门管理员；SM：超级管理员）
     */
    private String identity;


    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginId=" + loginId +
                ", userId=" + userId +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                '}';
    }
}
