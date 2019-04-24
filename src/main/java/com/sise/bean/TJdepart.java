package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/3/8 11:42
 * @Description:
 */
public class TJdepart {

    private String departmentName;      //系别名称
    private int departCount;            //系别人数

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepartCount() {
        return departCount;
    }

    public void setDepartCount(int departCount) {
        this.departCount = departCount;
    }

    @Override
    public String toString() {
        return "TJdepart{" +
                "departmentName='" + departmentName + '\'' +
                ", departCount=" + departCount +
                '}';
    }
}
