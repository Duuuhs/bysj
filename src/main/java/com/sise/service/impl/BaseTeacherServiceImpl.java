package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.*;
import com.sise.controller.LoginController;
import com.sise.entity.BaseDepartment;
import com.sise.entity.BaseStudent;
import com.sise.entity.BaseTeacher;
import com.sise.mapper.BaseDepartmentMapper;
import com.sise.mapper.BaseStudentMapper;
import com.sise.mapper.BaseTeacherMapper;
import com.sise.service.IBaseTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
 * @since 2019-02-21
 */
@Service
public class BaseTeacherServiceImpl extends ServiceImpl<BaseTeacherMapper, BaseTeacher> implements IBaseTeacherService {

    Logger logger = Logger.getLogger(LoginController.class.getName());

    Map<String, Object> params = new HashMap<>();

    @Resource
    private BaseTeacherMapper baseTeacherMapper;

    @Resource
    private BaseDepartmentMapper baseDepartmentMapper;

    @Resource
    private BaseStudentMapper baseStudentMapper;


    /*
     * 获取教师的基本信息
     */
    public TeaMessage getTeaMessage(Long teacher_id){
        TeaMessage teaMessage = new TeaMessage();

        try {
            if (isNull(teacher_id)) {
                logger.info("参数不足");
            } else {
                BaseTeacher teacher = baseTeacherMapper.selectById(teacher_id);
                if (notNull(teacher)){
                    //系别编号
                    Long teacherDeparementId = teacher.getTeacherDeparementId();
                    teaMessage.setDepartment_id(teacherDeparementId);
                    //系别名称
                    BaseDepartment baseDepartment = baseDepartmentMapper.selectById(teacherDeparementId);
                    if (notNull(baseDepartment)){
                        teaMessage.setDeparement_name(baseDepartment.getBaseDepartmentName());
                    }
                }
                //是否有毕业设计的学生
                QueryWrapper<BaseStudent> qw = new QueryWrapper<>();
                qw.eq("bysj_teacher_id", teacher_id);
                Integer integer = baseStudentMapper.selectCount(qw);
                teaMessage.setBsStuCount(integer);
                if (notNull(integer) && integer > 0){
                    teaMessage.setHasBsStu("T");
                } else {
                    teaMessage.setHasBsStu("F");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错");
        }
        return teaMessage;
    }


    /*
     * 根据教师id查找相关的毕业设计学生,进行分页,默认5条为一页
     * prarms: bysj_teacher_id, page_num(页码)
     */
    public QueryStuByTeaPage getStuListByTea(Long bysj_teacher_id, int page_num){
        QueryStuByTeaPage queryStuByTeaPage = new QueryStuByTeaPage();
        try {
            if (isNull(bysj_teacher_id) || isNull(page_num)) {
                queryStuByTeaPage.setMessage("参数不足");
                logger.info("参数不足");
            } else {
                QueryWrapper<BaseStudent> qw = new QueryWrapper<>();
                qw.eq("bysj_teacher_id", bysj_teacher_id);
                qw.orderByAsc("student_id");
                Integer stuCount = baseStudentMapper.selectCount(qw);
                int pageRecord = 5;//默认设置每行5条数据
                int pag_max = (int)Math.ceil((double)stuCount/(double)pageRecord);
                int dataStart;
                int dataEnd;
                if (page_num >= pag_max){
                    page_num = pag_max;
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = stuCount;
                } else {
                    dataStart = (page_num - 1)* pageRecord + 1;
                    dataEnd = page_num * pageRecord;
                }
                Page<BaseStudent> baseStudentPage = new Page<>(page_num,pageRecord);
                List<StuBsChoiceV2> stuList = baseStudentMapper.selectStuBsChoiceByTea(baseStudentPage, bysj_teacher_id);
                queryStuByTeaPage.setPageMax(pag_max);
                queryStuByTeaPage.setPageNum(page_num);
                queryStuByTeaPage.setDataStart(dataStart);
                queryStuByTeaPage.setDataEnd(dataEnd);
                queryStuByTeaPage.setStuCount(stuCount);
                queryStuByTeaPage.setStuList(stuList);
                queryStuByTeaPage.setMessage("查找成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            queryStuByTeaPage.setMessage("系统出错！");
            logger.info("系统出错！");
        }
        return queryStuByTeaPage;
    }


    /*
     *查找教师基本信息以及所带毕业生列表(DM/SM)(分页)
     */
    public QueryTeaByMangerPage getTeaMessageByManger(Long base_department_id, int page_num){
        QueryTeaByMangerPage  queryTeaByMangerPage = new QueryTeaByMangerPage();
        try {
            int teaCount = baseStudentMapper.selectTeaMessageByMangerCount(base_department_id);
            int pageRecord = 5;//默认设置每行5条数据
            int pag_max = (int)Math.ceil((double)teaCount/(double)pageRecord);
            int dataStart;
            int dataEnd;
            if (page_num >= pag_max){
                page_num = pag_max;
                dataStart = (page_num - 1)* pageRecord + 1;
                dataEnd = teaCount;
            } else {
                dataStart = (page_num - 1)* pageRecord + 1;
                dataEnd = page_num * pageRecord;
            }
            Page<TeaQueryNotChoiceStu> baseStudentPage = new Page<>(page_num,pageRecord);
            List<TeaMessageByManger> teaList = baseStudentMapper.selectTeaMessageByManger(baseStudentPage, base_department_id);
            queryTeaByMangerPage.setPageMax(pag_max);
            queryTeaByMangerPage.setPageNum(page_num);
            queryTeaByMangerPage.setDataStart(dataStart);
            queryTeaByMangerPage.setDataEnd(dataEnd);
            queryTeaByMangerPage.setTeaCount(teaCount);
            queryTeaByMangerPage.setTeaList(teaList);
            queryTeaByMangerPage.setMessage("查找成功！");
        } catch (Exception e) {
            e.printStackTrace();
            queryTeaByMangerPage.setMessage("系统出错！");
            logger.info("系统出错！");
        }
        return queryTeaByMangerPage;
    }



}
