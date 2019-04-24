package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.*;
import com.sise.entity.*;

import com.sise.mapper.BaseStudentMapper;
import com.sise.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.notNull;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-02-13
 */
@Service
public class BaseStudentServiceImpl extends ServiceImpl<BaseStudentMapper, BaseStudent> implements IBaseStudentService {

    Logger logger = Logger.getLogger(BaseStudentServiceImpl.class.getName());

    Map<String, Object> params = new HashMap<String, Object>();

    @Resource
    private IBaseStudentService baseStudentService;

    @Resource
    private IBaseTeacherService baseTeacherService;

    @Resource
    private IBaseClassService baseClassService;

    @Resource
    private IBaseMajorService baseMajorService;

    @Resource
    private IBaseDepartmentService baseDepartmentService;
    
    @Resource
    private BaseStudentMapper baseStudentMapper;

    /*
     * 根据学号获取学生的基本信息
     */
    public StuMessage getStuMessage(Long student_id){
        StuMessage stuMessage = new StuMessage();
        try {
            //根据id获取学生的基本信息，在进行其他表的相关数据查询
            BaseStudent baseStudentById = baseStudentService.getById(student_id);
            if (isNull(baseStudentById)){
                logger.info("查找无相关学生的信息");
            } else {
                //性别,1为男0为女
                if (notNull(baseStudentById.getStudentSex())){
                    Long studentSex = baseStudentById.getStudentSex();
                    if (1L == studentSex){
                        stuMessage.setStudent_sex("男");
                    } else if (0L == studentSex) {
                        stuMessage.setStudent_sex("女");
                    }
                } else {
                    stuMessage.setStudent_sex("暂无性别信息");
                }
                //班级信息
                if (notNull(baseStudentById.getClassId())) {
                    Long class_id = baseStudentById.getClassId();
                    stuMessage.setClass_id(class_id);
                    BaseClass baseCLassById = baseClassService.getById(class_id);
                    if (notNull(baseCLassById)) {
                        stuMessage.setClass_name(baseCLassById.getBaseClassName());
                    }
                } else {
                    stuMessage.setClass_id(null);
                    stuMessage.setClass_name("暂无班级信息");
                }
                //系别信息
                if (notNull(baseStudentById.getDepatrmentId())){
                    Long depatrment_id = baseStudentById.getDepatrmentId();
                    stuMessage.setDepartment_id(depatrment_id);
                    BaseDepartment baseDepartment = baseDepartmentService.getById(depatrment_id);
                    if (notNull(baseDepartment)){
                        String baseDepartmentName = baseDepartment.getBaseDepartmentName();
                        stuMessage.setDepartmenr_name(baseDepartmentName);
                    }
                }
                //专业信息
                if (notNull(baseStudentById.getMajorId())) {
                    Long major_id = baseStudentById.getMajorId();
                    stuMessage.setMajor_id(major_id);
                    BaseMajor baseMajorById = baseMajorService.getById(major_id);
                    if (notNull(baseMajorById)) {
                        stuMessage.setMajor_name(baseMajorById.getMajorName());
                    }
                } else {
                    stuMessage.setMajor_id(null);
                    stuMessage.setMajor_name("暂无专业信息");
                }
                //学习指导老师信息
                if (notNull(baseStudentById.getStudyTeacherId())) {
                    Long study_teacher_id = baseStudentById.getStudyTeacherId();
                    stuMessage.setStudy_teacher_id(study_teacher_id);
                    BaseTeacher studyTeacherById = baseTeacherService.getById(study_teacher_id);
                    if (notNull(studyTeacherById)) {
                        stuMessage.setStudy_teacher_name(studyTeacherById.getTeacherName());
                    }
                } else {
                    stuMessage.setStudy_teacher_id(null);
                    stuMessage.setStudy_teacher_name("暂无学习指导老师信息");
                }
                //辅导员信息
                if (notNull(baseStudentById.getTutorTeacherId())) {
                    Long tutor_teacher_id = baseStudentById.getTutorTeacherId();
                    stuMessage.setTutor_teacher_id(tutor_teacher_id);
                    BaseTeacher tutorTeacherById = baseTeacherService.getById(tutor_teacher_id);
                    if (notNull(tutorTeacherById)) {
                        stuMessage.setTutor_teacher_name(tutorTeacherById.getTeacherName());
                    }
                } else {
                    /*stuMessage.setTutor_teacher_id(null);*/
                    stuMessage.setTutor_teacher_name("暂无辅导员信息");
                }
                //毕业设计指导老师信息
                if (notNull(baseStudentById.getBysjTeacherId())) {
                    Long bysj_teacher_id = baseStudentById.getBysjTeacherId();
                    stuMessage.setBysj_teacher_id(bysj_teacher_id);
                    BaseTeacher bysjTeacherById = baseTeacherService.getById(bysj_teacher_id);
                    if (notNull(bysjTeacherById)) {
                        stuMessage.setBysj_teacher_name(bysjTeacherById.getTeacherName());
                    }
                } else {
                    stuMessage.setBysj_teacher_id(null);
                    stuMessage.setBysj_teacher_name("暂无毕业设计指导老师信息");
                }
            }
        } catch (Exception e) {
            logger.info("系统出错");
            e.printStackTrace();
        }
        return  stuMessage;
    }



    /*
     * 部门管理员给学生分配毕业设计老师
     */
    public TMessage addBsTeacher(Long student_id, Long bysj_teacher_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(student_id) || isNull(bysj_teacher_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足");
            } else {
                QueryWrapper<BaseStudent> qw = new QueryWrapper<>();
                qw.eq("student_id", student_id);
                BaseStudent baseStudent = new BaseStudent();
                baseStudent.setBysjTeacherId(bysj_teacher_id);
                int update = baseStudentMapper.update(baseStudent, qw);
                if (update > 0){
                    tMessage.setMessage("分配成功！");
                } else {
                    tMessage.setMessage("分配失败！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            tMessage.setMessage("系统出错！");
        }
        return tMessage;
    }



    /*
     * 查询该系的学生的基本信息(分页)(DM)
     */
    public  QueryStuMessageByMangerPage getStuMessageByMangerPage(int page_num, Long base_department_id, String student_id, String student_name, String hasBsTea){
        QueryStuMessageByMangerPage queryStuMessageByMangerPage = new QueryStuMessageByMangerPage();
        try {
            if (isNull(base_department_id)){
                logger.info("参数不足！");
                queryStuMessageByMangerPage.setMessage("参数不足！");
            } else {
                int i = baseStudentMapper.selectStuMessageByMangerCount(base_department_id,student_id, student_name, hasBsTea);
                int pageRecord = 10;//默认设置每行10条数据
                int pag_max = (int)Math.ceil((double)i/(double)pageRecord);
                int dataStart;
                int dataEnd;
                if (page_num >= pag_max){
                    page_num = pag_max;
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = i;
                } else {
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = page_num * pageRecord;
                }
                Page<StuMessageByManger> baseStudentPage = new Page<>(page_num,pageRecord);
                List<StuMessageByManger> stuMessageByMangers = baseStudentMapper.selectStuMessageByManger(baseStudentPage, base_department_id, student_id, student_name, hasBsTea);
                queryStuMessageByMangerPage.setPageMax(pag_max);
                queryStuMessageByMangerPage.setPageNum(page_num);
                queryStuMessageByMangerPage.setDataStart(dataStart);
                queryStuMessageByMangerPage.setDataEnd(dataEnd);
                queryStuMessageByMangerPage.setStuCount(i);
                queryStuMessageByMangerPage.setStuMessageByMangers(stuMessageByMangers);
                queryStuMessageByMangerPage.setMessage("查找成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            queryStuMessageByMangerPage.setMessage("系统出错！");
        }
        return  queryStuMessageByMangerPage;
    }



    /*
     * 查询该系的学生的基本信息(分页)(SM)
     */
    public QueryStuMessageByMangerPage getStuMessageByMangerPage(int page_num,String student_id, String student_name, String hasBsTea){
        QueryStuMessageByMangerPage queryStuMessageByMangerPage = new QueryStuMessageByMangerPage();
        try {
            int i = baseStudentMapper.selectStuMessageByMangerCountV2(student_id, student_name, hasBsTea);
            int pageRecord = 10;//默认设置每行10条数据
            int pag_max = (int)Math.ceil((double)i/(double)pageRecord);
            int dataStart;
            int dataEnd;
            if (page_num >= pag_max){
                page_num = pag_max;
                dataStart = (page_num - 1)* pageRecord + 1;
                dataEnd = i;
            } else {
                dataStart = (page_num - 1)* pageRecord + 1;
                dataEnd = page_num * pageRecord;
            }
            Page<StuMessageByMangerV2> baseStudentPage = new Page<>(page_num,pageRecord);
            List<StuMessageByMangerV2> stuMessageByMangers = baseStudentMapper.selectStuMessageByMangerV2(baseStudentPage,student_id, student_name, hasBsTea);
            queryStuMessageByMangerPage.setPageMax(pag_max);
            queryStuMessageByMangerPage.setPageNum(page_num);
            queryStuMessageByMangerPage.setDataStart(dataStart);
            queryStuMessageByMangerPage.setDataEnd(dataEnd);
            queryStuMessageByMangerPage.setStuCount(i);
            queryStuMessageByMangerPage.setStuMessageByMangerV2s(stuMessageByMangers);
            queryStuMessageByMangerPage.setMessage("查找成功！");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            queryStuMessageByMangerPage.setMessage("系统出错！");
        }
        return  queryStuMessageByMangerPage;
    }




}
