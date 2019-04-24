package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/20 11:04
 * @Description:
 */
public class TMessage {

    private int status;         //返回的信息状态码(0和200)
    private String message;     //返回的提示信息
    private String rollback;    //返回的数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRollback() {
        return rollback;
    }

    public void setRollback(String rollback) {
        this.rollback = rollback;
    }

    @Override
    public String toString() {
        return "TMessage{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", rollback='" + rollback + '\'' +
                '}';
    }
}
