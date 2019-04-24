package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sise.bean.LoginAuth;
import com.sise.bean.TMessage;
import com.sise.bean.UpdateStuInfo;
import com.sise.entity.*;
import com.sise.mapper.*;
import com.sise.service.IBaseMangerService;
import com.sise.service.IBaseStudentService;
import com.sise.service.IBaseTeacherService;
import com.sise.service.ILoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.sise.common.Assert.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-01-08
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    @Resource
    private  ILoginService loginService;

    @Resource
    private IBaseMangerService baseMangerService;

    @Resource
    private IBaseStudentService baseStudentService;

    @Resource
    private IBaseTeacherService baseTeacherService;

    @Resource
    private BaseDepartmentMapper baseDepartmentMapper;

    @Resource
    private BaseTeacherMapper baseTeacherMapper;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private BaseStudentMapper baseStudentMapper;

    @Resource
    private BaseClassMapper baseClassMapper;

    @Resource
    private BaseMajorMapper baseMajorMapper;


    /*
     * 用户登录
     */
    @Override
    public LoginAuth getUserMessage(String username, String password, String identity){
        LoginAuth loginAuth = new LoginAuth();
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            //学生或者教师身份进入login表查询
            if ("S".equals(identity) || "T".equals(identity)) {
                if (isNumber(username) == false) {
                    logger.info("identity为教师或学生身份,username格式错误！");
                    loginAuth.setMessage("identity为教师或学生身份,username格式错误！");
                    return loginAuth;
                }
                Long user_id = Long.parseLong(username);
                //条件构造器
                QueryWrapper<Login> qw = new QueryWrapper<Login>();
                params.put("user_id", user_id);
                params.put("password", password);
                params.put("identity", identity);
                qw.allEq(true, params, false);
                Login one = loginService.getOne(qw);
                if (notNull(one)) {
                    logger.info("查询成功！");
                    loginAuth.setMessage("查询成功！");
                    loginAuth.setLoginId(one.getLoginId());
                    loginAuth.setUserId(one.getUserId());
                    loginAuth.setIdentity(one.getIdentity());
                    String userName = loginService.getUserName(user_id, identity);
                    loginAuth.setUserName(userName);
                    if ("T".equals(identity)){
                        loginAuth.setIdentity_str("老师");
                    } else {
                        loginAuth.setIdentity_str("同学");
                    }
                } else {
                    logger.info("用户名或者密码错误！");
                    loginAuth.setMessage("用户名或者密码错误！");
                }

            }

            //超级管理员或者部门管理员进入base_manger表查询
            if ("SM".equals(identity) || "DM".equals(identity)) {
                //条件构造器
                String manger_name = username;
                QueryWrapper<BaseManger> qw = new QueryWrapper<>();
                params.put("manger_name", manger_name);
                params.put("manger_password", password);
                params.put("manger_identity", identity);
                qw.allEq(true, params, false);
                BaseManger one = baseMangerService.getOne(qw);
                if (notNull(one)) {
                    logger.info("查询成功！");
                    loginAuth.setMessage("查询成功！");
                    loginAuth.setLoginId(one.getMangerId());
                    loginAuth.setMangerName(one.getMangerName());
                    loginAuth.setIdentity(one.getMangerIdentity());
                    loginAuth.setUserName(one.getMangerName());
                    if ("SM".equals(identity)){
                        loginAuth.setIdentity_str("超级管理员");
                    } else {
                        /*部门管理员多添加个系别信息*/
                        if (notNull(one.getMangerDepartment())) {
                            loginAuth.setDepartment(one.getMangerDepartment());
                            BaseDepartment baseDepartment = baseDepartmentMapper.selectById(one.getMangerDepartment());
                            if (notNull(baseDepartment)) {
                                loginAuth.setDepartment_str(baseDepartment.getBaseDepartmentName());
                                loginAuth.setIdentity_str(baseDepartment.getBaseDepartmentName()+"管理员");
                            }
                        }
                    }
                } else {
                    logger.info("用户名或者密码错误！");
                    loginAuth.setMessage("用户名或者密码错误！");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            loginAuth.setMessage("系统出错！");
            logger.info("系统出错！");
        }


        return  loginAuth;
    }



    /*
     * 根据用户身份跟id获取用户的用户名
     */
    @Override
    public String getUserName(Long loginId,String identity){
        String userName = null;
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            if ("S".equals(identity)){
                QueryWrapper<BaseStudent> qw = new QueryWrapper<BaseStudent>();
                params.put("student_id", loginId);
                qw.allEq(true, params, true);
                BaseStudent student = baseStudentService.getOne(qw);
                if (notNull(student)) {
                    userName = student.getStudentName();

                }
            }
            if ("T".equals(identity)){
                QueryWrapper<BaseTeacher> qw = new QueryWrapper<BaseTeacher>();
                params.put("teacher_id", loginId);
                qw.allEq(true, params, true);
                BaseTeacher teacher = baseTeacherService.getOne(qw);
                if (notNull(teacher)) {
                    userName = teacher.getTeacherName();

                }
            }
        } catch (Exception e){
           e.printStackTrace();
        }


        return userName;
    }


    /*
     * 添加系统用户
     */
    public TMessage addUser(Long add_userId, String add_username, String add_identity, Long add_departmentId){
        TMessage tMessage  = new TMessage();
        try {
            /*教师身份先添加到教师表，再更新登录表信息*/
            if ("T".equals(add_identity)){
                BaseTeacher baseTeacher = new BaseTeacher();
                baseTeacher.setTeacherId(add_userId);
                baseTeacher.setTeacherName(add_username);
                baseTeacher.setTeacherDeparementId(add_departmentId);
                int insertTea = baseTeacherMapper.insert(baseTeacher);
                if (notNull(insertTea)){
                    logger.info("添加教师表成功！");
                    Login login = new Login();
                    login.setIdentity(add_identity);
                    login.setUserId(add_userId);
                    login.setPassword("123456");
                    int insertTeaLogin = loginMapper.insert(login);
                    if (notNull(insertTeaLogin)){
                        logger.info("添加登录表成功！");
                        tMessage.setMessage("注册成功！");
                    }
                }
            } else if ("S".equals(add_identity)){
                BaseStudent baseStudent = new BaseStudent();
                baseStudent.setStudentId(add_userId);
                baseStudent.setDepatrmentId(add_departmentId);
                baseStudent.setStudentName(add_username);
                int insertStu = baseStudentMapper.insert(baseStudent);
                if (notNull(insertStu)){
                    logger.info("添加学生表成功！");
                    Login login = new Login();
                    login.setIdentity(add_identity);
                    login.setUserId(add_userId);
                    login.setPassword("123456");
                    int insertStuLogin = loginMapper.insert(login);
                    if (notNull(insertStuLogin)){
                        logger.info("添加登录表成功！");
                        tMessage.setMessage("注册成功！");
                        tMessage.setStatus(200);
                        tMessage.setRollback(add_userId.toString()+","+add_departmentId);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错");
            tMessage.setMessage("系统出错！");
        }
        return tMessage;
    }


    /*
     * 完善学生的基本信息
     */
    public UpdateStuInfo completeStuInfo(String message){
        UpdateStuInfo updateStuInfo = new UpdateStuInfo();
        try {
            if (isNull(message)){
                logger.info("参数不足！");
            } else {
                String[] split = message.split(",");
                if (notNull(split)) {
                    Long userId = Long.parseLong(split[0]);
                    Long departmentId = Long.parseLong(split[1]);
                    updateStuInfo.setStudent_id(userId);
                    QueryWrapper<BaseClass> qwClass = new QueryWrapper<>();
                    qwClass.eq("base_department_id", departmentId);
                    List<BaseClass> baseClasses = baseClassMapper.selectList(qwClass);
                    updateStuInfo.setClassess(baseClasses);
                    QueryWrapper<BaseMajor> qwMajor = new QueryWrapper<>();
                    qwMajor.eq("majro_department_id", departmentId);
                    List<BaseMajor> baseMajors = baseMajorMapper.selectList(qwMajor);
                    updateStuInfo.setMajors(baseMajors);
                    QueryWrapper<BaseTeacher> qwTea = new QueryWrapper<>();
                    qwTea.eq("teacher_deparement_id", departmentId);
                    List<BaseTeacher> baseTeachers = baseTeacherMapper.selectList(qwTea);
                    updateStuInfo.setTeachers(baseTeachers);
                    BaseStudent baseStudent = baseStudentMapper.selectById(userId);
                    updateStuInfo.setStudent_name(baseStudent.getStudentName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
        }
        return updateStuInfo;
    }



    /*
     *保存学生完善的信息
     */
    public TMessage savecompleteStuInfo(Long student_id, Long student_sex, Long class_id, Long major_id, Long study_teacher_id, Long tutor_teacher_id){
        TMessage tMessage = new TMessage();
        try {
            if (isNull(student_id) || isNull(student_sex) ||isNull(class_id) || isNull(major_id) || isNull(study_teacher_id) || isNull(tutor_teacher_id)){
                logger.info("参数不足！");
                tMessage.setMessage("参数不足!");
            } else {
                BaseStudent baseStudent = new BaseStudent();
                baseStudent.setStudentSex(student_sex);
                baseStudent.setClassId(class_id);
                baseStudent.setMajorId(major_id);
                baseStudent.setStudyTeacherId(study_teacher_id);
                baseStudent.setTutorTeacherId(tutor_teacher_id);
                QueryWrapper<BaseStudent> qw = new QueryWrapper<>();
                qw.eq("student_id", student_id);
                int update = baseStudentMapper.update(baseStudent, qw);
                if (notNull(update)){
                    logger.info("完善成功！");
                    tMessage.setMessage("完善成功!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统出错！");
            tMessage.setMessage("系统出错!");
        }
        return tMessage;
    }


}
