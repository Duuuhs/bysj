package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/21 23:23
 * @Description:
 */
public class TeaMessage {

    private Long department_id;         //系别编号
    private String deparement_name;     //系别名称
    private String hasBsStu;            //是否有毕业设计的学生(T/F)
    private int bsStuCount;             //所带毕业生人数

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDeparement_name() {
        return deparement_name;
    }

    public void setDeparement_name(String deparement_name) {
        this.deparement_name = deparement_name;
    }

    public String getHasBsStu() {
        return hasBsStu;
    }

    public void setHasBsStu(String hasBsStu) {
        this.hasBsStu = hasBsStu;
    }

    public int getBsStuCount() {
        return bsStuCount;
    }

    public void setBsStuCount(int bsStuCount) {
        this.bsStuCount = bsStuCount;
    }

    @Override
    public String toString() {
        return "TeaMessage{" +
                "department_id=" + department_id +
                ", deparement_name='" + deparement_name + '\'' +
                ", hasBsStu='" + hasBsStu + '\'' +
                ", bsStuCount=" + bsStuCount +
                '}';
    }
}
