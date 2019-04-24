package com.sise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sise.bean.TJclass;
import com.sise.bean.TJclasses;
import com.sise.entity.BaseClass;
import com.sise.entity.BaseDepartment;
import com.sise.mapper.BaseClassMapper;
import com.sise.mapper.BaseDepartmentMapper;
import com.sise.service.IBaseClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

import static com.sise.common.Assert.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DMY
 * @since 2019-03-03
 */
@Service
public class BaseClassServiceImpl extends ServiceImpl<BaseClassMapper, BaseClass> implements IBaseClassService {

    @Resource
    private BaseClassMapper baseClassMapper;

    @Resource
    private BaseDepartmentMapper baseDepartmentMapper;

    Logger logger = Logger.getLogger(BaseClassServiceImpl.class.getName());



    /*
     *统计班级人数(系)
     */
    public TJclass selectClasses(Long base_department_id) {
        TJclass tJclass = new TJclass();
        try {
            if (isNull(base_department_id)){
                logger.info("参数不足！");
            } else {
                BaseDepartment baseDepartment = baseDepartmentMapper.selectById(base_department_id);
                if (notNull(base_department_id)){
                    tJclass.setDepartmentName(baseDepartment.getBaseDepartmentName());
                    List<TJclasses> tJclasses = baseClassMapper.selectClasses(base_department_id);
                    tJclass.settJclasses(tJclasses);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tJclass;
    }
}
