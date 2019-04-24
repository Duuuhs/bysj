package com.sise.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.*;
import com.sise.entity.*;
import com.sise.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: DMY
 * @Date: 2019/1/16 23:02
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext-dao.xml",
        "classpath:applicationContext-service.xml"
})
public class ILoginServiceTest {

    @Resource
    private  ILoginService loginService;

    @Resource
    private  IBaseStudentService baseStudentService;

    @Resource
    private  IBaseClassService baseClassService;

    @Resource
    private  IBaseTeacherService baseTeacherService;

    @Resource
    private  IBsTopicService bsTopicService;

    @Resource
    private  IBsChoiceService bsChoiceService;

    @Resource
    private IBsNoticeService bsNoticeService;

    @Resource
    private BsTopicMapper bsTopicMapper;

    @Resource
    private BsChoiceMapper bsChoiceMapper;

    @Resource
    private BaseStudentMapper baseStudentMapper;

    @Resource
    private BaseClassMapper baseClassMapper;

    @Resource
    private FileMapper fileMapper;

    Logger logger = Logger.getLogger(ILoginServiceTest.class.getName());

    @Test
    public  void getUserMessage(){
        String username = "dmanger";
        String password = "dmanger";
        String identity = "DM";
        LoginAuth userMessage = loginService.getUserMessage(username, password, identity);
        System.out.println("userMessage = " + userMessage);
    }

    @Test
    public void getUserName(){
        Long loginId = 101L;
        String identity = "T";
        String userName = loginService.getUserName(loginId, identity);
        System.out.println("username = " + userName);
    }

    @Test
    public void getStuMessage(){
        Long student_id = 1540128137L;
        StuMessage stuMessage = baseStudentService.getStuMessage(student_id);
        System.out.println(stuMessage);
    }

    @Test
    public void getBaseClass(){
        Long class_id = 101L;
        BaseClass byId = baseClassService.getById(class_id);
        System.out.println(byId);
    }

    @Test
    public void getTeaBsTopics(){
        Long bs_teacher_id = 205L;
        TeaBsTopics teaBsTopics = bsTopicService.getTeaBsTopics(bs_teacher_id);
        System.out.println(teaBsTopics);
    }


    @Test
    public void getTeaBsTopicsV2(){
        Long bs_teacher_id = 205L;
        Long student_id = 1540128137L;
        StuQueryTeaBsTopics teaBsTopicsV2 = bsTopicService.getTeaBsTopicsV2(bs_teacher_id, student_id);
        System.out.println(teaBsTopicsV2);
    }


    @Test
    public void getStuBsChoice(){
        Long student_id = 1540128137L;
        BsChoice stuBsChoice = bsChoiceService.getStuBsChoice(student_id);
        System.out.println(stuBsChoice);
    }

    @Test
    public void saveOrUpdateStuChoice(){
        Long bs_topic_id = 150L;
        Long student_id = 1540128139L;
        TMessage tMessage = bsTopicService.saveOrUpdateStuChoice(bs_topic_id, student_id);
        System.out.println(tMessage);
    }

    @Test
    public void saveBsNotice(){
        Long bs_teacher_id = 210L;
        String bs_notice_content = "3月9号进行系统答辩，请大家做好准备。";
        TMessage tMessage = bsNoticeService.saveBsNotice(bs_teacher_id, bs_notice_content);
        System.out.println(tMessage);
    }


    @Test
    public void deleteBsNotice(){
        Long bs_notice_id = 6L;
        TMessage tMessage = bsNoticeService.deleteBsNotice(bs_notice_id);
        System.out.println(tMessage);
    }

    @Test
    public void getBsNotices(){
        Long bs_teacher_id = 205L;
        List<BsNoticeV2> bsNotices = bsNoticeService.getBsNotices(bs_teacher_id);
        System.out.println(bsNotices);
    }


    @Test
    public void getStuListByTea(){
        QueryStuByTeaPage stuListByTea = baseTeacherService.getStuListByTea(205L, 2);
        System.out.println(stuListByTea);
    }

    @Test
    public void selectBsTopicByTea(){
        Page<BsTopic> page = new Page<>(2,3);
        Long bs_teacher_id = 205L;
        List<TeaQueryBsTopic> teaQueryBsTopics = bsTopicMapper.selectBsTopicByTea(page, bs_teacher_id);
        System.out.println(teaQueryBsTopics);
    }

    @Test
    public void getTopicListByTea(){
        Long bs_teacher_id = 205L;
        QueryTopicByTeaPage topicListByTea = bsTopicService.getTopicListByTea(bs_teacher_id, 2);
        System.out.println(topicListByTea);
    }


    @Test
    public void deleteBsTopic(){
        Long bs_topic_id = 158L;
        TMessage tMessage = bsTopicService.deleteBsTopic(bs_topic_id);
        System.out.println(tMessage);
    }


    @Test
    public void addBsTopic(){
        String bs_topic_name = "JavaWeb毕设";
        Long bs_teacher_id = 100L;
        TMessage tMessage = bsTopicService.addBsTopic(bs_topic_name, bs_teacher_id);
        System.out.println(tMessage);
    }


    @Test
    public void sureBsTopic(){
        Long bs_topic_id = 150L;
        TMessage tMessage = bsTopicService.sureBsTopic(bs_topic_id);
        System.out.println(tMessage);
    }

    @Test
    public void deleteStuBsChoice(){
        Long bs_topic_id = 150L;
        Long student_id = 1540128137L;
        TMessage tMessage = bsChoiceService.deleteStuBsChoice(student_id, bs_topic_id);
        System.out.println(tMessage);
    }


    @Test
    public void noSureBsTopic(){
        Long bs_topic_id = 150L;
        Long student_id = null;
        TMessage tMessage = bsTopicService.noSureBsTopic(bs_topic_id, student_id);
        System.out.println(tMessage);
    }


    @Test
    public void selectStuChoiceStatus(){
        Long bysj_teacher_id = 205L;
        int page_num = 1;
        QueryStuBsChoiceStatusPage stuBsChoiceStatusPage = bsChoiceService.getStuBsChoiceStatusPage(page_num, bysj_teacher_id);
        System.out.println(stuBsChoiceStatusPage);
    }

    @Test
    public void saveStuBsChoiceById(){
        Long bs_choice_id = 22L;
        Double review_point=null;
        Double reply_point=null;
        String guidance="嘻嘻";
        TMessage tMessage = bsChoiceService.saveStuBsChoiceById(bs_choice_id, review_point, reply_point, guidance);
        System.out.println(tMessage);
    }


    @Test
    public void getStuSureByDepartmentId(){
        Long department_id = 1L;
        int i = bsChoiceMapper.selectStuSureByDepartmentId(department_id);
        System.out.println(i);

    }


    @Test
    public void selectStuMessageByManger(){
        Long department_id = 1L;
        String student_id = "1540";
        String student_name = null;
        String hasBsTea = "F";
        Page<StuMessageByManger> page = new Page<>(1,10);
        List<StuMessageByManger> stuMessageByMangers = baseStudentMapper.selectStuMessageByManger(page, department_id, student_id, student_name, hasBsTea);
        System.out.println(stuMessageByMangers);
    }


    @Test
    public  void selectStuMessageByMangerCount(){
        Long department_id = 1L;
        String student_id = "1540";
        String student_name = null;
        String hasBsTea = "F";
        int i = baseStudentMapper.selectStuMessageByMangerCount(department_id, student_id, student_name, hasBsTea);
        System.out.println(i);
    }


    @Test
    public  void selectStuMessageByMangerCountV2(){
        String student_id = "1540";
        String student_name = "陈";
        String hasBsTea = "F";
        int i = baseStudentMapper.selectStuMessageByMangerCountV2(student_id, student_name, hasBsTea);
        System.out.println(i);
        Page<StuMessageByMangerV2> page = new Page<>(1,10);
        List<StuMessageByMangerV2> stuMessageByMangerV2s = baseStudentMapper.selectStuMessageByMangerV2(page,student_id, student_name, hasBsTea);
        System.out.println(stuMessageByMangerV2s);
    }


    @Test
    public void selectTeaMessageByManger(){
        Long base_department_id = 1L;
        Page<TeaMessageByManger> page = new Page<>(1,10);
        List<TeaMessageByManger> teaMessageByMangers = baseStudentMapper.selectTeaMessageByManger(page, base_department_id);
        int i = baseStudentMapper.selectTeaMessageByMangerCount(base_department_id);
        System.out.println(teaMessageByMangers);
        System.out.println(i);
    }


    @Test
    public void addUser(){
        Long add_userId = 210L;
        String add_username = "学生";
        String add_identity = "S";
        Long add_departmentId = 1L;
        TMessage tMessage = loginService.addUser(add_userId, add_username, add_identity, add_departmentId);
        System.out.println(tMessage);
    }


    @Test
    public void completeStuInfo(){
        String message = "1540128137,1";
        UpdateStuInfo updateStuInfo = loginService.completeStuInfo(message);
        System.out.println(updateStuInfo);
    }


    @Test
    public void selectStuUpLoadPage(){
        Long teacher_id = 205L;
        Page<QueryStuUpLoad> page = new Page<>();
        List<QueryStuUpLoad> queryStuUpLoads = fileMapper.selectStuUpLoadPage(page, teacher_id);
        System.out.println(queryStuUpLoads);
    }


    @Test
    public void selectTeaList(){
        Long student_id = 1540128137L;
        List<QueryTeaList> queryTeaLists = baseStudentMapper.selectTeaList(student_id);
        System.out.println(queryTeaLists);
    }


    @Test
    public void selectTeaBsTopic(){
        Long teacher_id = 202L;
        List<BsTopicContent> bsTopicContents = baseStudentMapper.selectTeaBsTopic(teacher_id);
        System.out.println(bsTopicContents);
    }

    @Test
    public void selectClasses(){
        Long department_id = 1L;
        TJclass tJclass = baseClassService.selectClasses(department_id);
        System.out.println(tJclass);
    }


    @Test
    public void selectStuSex(){
        Long department_id = 1L;
        List<TJsex> tJsexes = baseClassMapper.selectStuSex(department_id);
        System.out.println(tJsexes);
    }


    @Test
    public void selectDepart(){
        List<TJdepart> tJdeparts = baseClassMapper.selectDepart();
        System.out.println(tJdeparts);
    }

    @Test
    public void selectXT(){
        Long department_id = 1L;
        int i = baseClassMapper.selectNotTea(department_id);
        int i1 = baseClassMapper.selectHasTeaNoChoice(department_id);
        int i2 = baseClassMapper.selectHasTeaHasChoice(department_id);
        TJbs tJbs = new TJbs();
        tJbs.setHasTeaHasChoice(i2);
        tJbs.setHasTeaNoChoice(i1);
        tJbs.setNotTea(i);
        System.out.println(tJbs);
    }

}