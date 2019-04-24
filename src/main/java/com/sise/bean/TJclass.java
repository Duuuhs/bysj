package com.sise.bean;

import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/3/7 22:17
 * @Description:
 */
public class TJclass {
    private String departmentName;      //系别名称
    private List<TJclasses> tJclasses;  //统计数据

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<TJclasses> gettJclasses() {
        return tJclasses;
    }

    public void settJclasses(List<TJclasses> tJclasses) {
        this.tJclasses = tJclasses;
    }

    @Override
    public String toString() {
        return "TJclass{" +
                "departmentName='" + departmentName + '\'' +
                ", tJclasses=" + tJclasses +
                '}';
    }
}
