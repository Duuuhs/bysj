package com.sise.bean;

/**
 * @Author: DMY
 * @Date: 2019/2/19 19:00
 * @Description:
 */
public class StuQueryTeaBsTopics {
    private TeaBsTopics teaBsTopics;    //教师id下所有的毕业设计题目
    private String hasChoice;           //学生是否选过题目了(T/F)
    private String hasTeaChoice;        //教师是否确认过了学生选的题目(T/F)

    public String getHasChoice() {
        return hasChoice;
    }

    public void setHasChoice(String hasChoice) {
        this.hasChoice = hasChoice;
    }

    public String getHasTeaChoice() {
        return hasTeaChoice;
    }

    public void setHasTeaChoice(String hasTeaChoice) {
        this.hasTeaChoice = hasTeaChoice;
    }

    public TeaBsTopics getTeaBsTopics() {
        return teaBsTopics;
    }

    public void setTeaBsTopics(TeaBsTopics teaBsTopics) {
        this.teaBsTopics = teaBsTopics;
    }

    @Override
    public String toString() {
        return "StuQueryTeaBsTopics{" +
                "teaBsTopics=" + teaBsTopics +
                ", hasChoice='" + hasChoice + '\'' +
                ", hasTeaChoice='" + hasTeaChoice + '\'' +
                '}';
    }
}
