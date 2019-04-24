package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sise.bean.DmMessage;
import com.sise.bean.SmManger;
import com.sise.entity.BaseManger;
import com.sise.entity.BaseStudent;
import com.sise.entity.BaseTeacher;
import com.sise.mapper.*;
import com.sise.service.IBaseMangerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

import static com.sise.common.Assert.isNull;
import static com.sise.common.Assert.isNumber;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-03-01
 */
@Service
public class BaseMangerServiceImpl extends ServiceImpl<BaseMangerMapper, BaseManger> implements IBaseMangerService {

    Logger logger = Logger.getLogger(BaseManger.class.getName());

    @Resource
    private BaseStudentMapper baseStudentMapper;

    @Resource
    private BaseTeacherMapper baseTeacherMapper;

    @Resource
    private BsChoiceMapper bsChoiceMapper;

    @Resource
    private BaseDepartmentMapper baseDepartmentMapper;


    /*
     * 获取部门管理员的基本信息
     */
    public DmMessage getDmMessage(Long department_id){
        DmMessage dmMessage = new DmMessage();
        try {
            if (isNull(department_id)){
                logger.info("参数不足！");
            } else {
                //本系教职工人数
                QueryWrapper<BaseTeacher> teacher_qw = new QueryWrapper<>();
                teacher_qw.eq("teacher_deparement_id", department_id);
                Integer teaCount = baseTeacherMapper.selectCount(teacher_qw);
                dmMessage.setTeaCount(teaCount);
                //本系毕业生人数
                QueryWrapper<BaseStudent> student_qw = new QueryWrapper<>();
                student_qw.eq("depatrment_id", department_id);
                Integer stuCount = baseStudentMapper.selectCount(student_qw);
                dmMessage.setStuCount(stuCount);
                //本系已确认选题学生人数
                int sureCount = bsChoiceMapper.selectStuSureByDepartmentId(department_id);
                dmMessage.setStuChoiceCount(sureCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dmMessage;
    }


    /*
     * 获取部门管理员的基本信息
     */
    public SmManger getSmMessage(){
        SmManger smManger = new SmManger();
        //院系数目
        int departCount = baseDepartmentMapper.selectDeparementCount();
        smManger.setDepartmentCount(departCount);
        //教职工数目
        int teaCount = baseTeacherMapper.selectTeaCount();
        smManger.setTeaCount(teaCount);
        //毕业生人数
        int stuCount = baseStudentMapper.selectStuCount();
        smManger.setStuCount(stuCount);
        //已确认选题人数
        int sureCount = bsChoiceMapper.selectStuSureCount();
        smManger.setStuChoiceCount(sureCount);
        return smManger;
    }
}
