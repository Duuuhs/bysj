package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/1/15 23:13
 * @Description: 登陆后的实体类
 */
public class LoginAuth {

    private String message;        //登录提示信息
    private Long loginId;          //登录id
    private Long userId;           //用户id(S/T身份有效)
    private String mangerName;     //管理员id（SM/DM身份有效）
    private Long department;       //部门管理员系别(DM身份有效)
    private String department_str;   //部门管理员系别名称(DM身份有效)
    private String userName;       //用户名
    private String identity;       //用户身份
    private String identity_str;   //用户身份显示的字符串


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getMangerName() {
        return mangerName;
    }

    public void setMangerName(String mangerName) {
        this.mangerName = mangerName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentity_str() {
        return identity_str;
    }

    public void setIdentity_str(String identity_str) {
        this.identity_str = identity_str;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public String getDepartment_str() {
        return department_str;
    }

    public void setDepartment_str(String department_str) {
        this.department_str = department_str;
    }

    @Override
    public String toString() {
        return "LoginAuth{" +
                "message='" + message + '\'' +
                ", loginId=" + loginId +
                ", userId=" + userId +
                ", mangerName='" + mangerName + '\'' +
                ", department=" + department +
                ", department_str='" + department_str + '\'' +
                ", userName='" + userName + '\'' +
                ", identity='" + identity + '\'' +
                ", identity_str='" + identity_str + '\'' +
                '}';
    }
}
