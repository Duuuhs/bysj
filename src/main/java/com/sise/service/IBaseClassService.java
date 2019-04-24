package com.sise.service;

import com.sise.bean.TJclass;
import com.sise.entity.BaseClass;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-03-03
 */
public interface IBaseClassService extends IService<BaseClass> {


    /*
     *统计班级人数(系)
     */
    TJclass selectClasses(Long base_department_id);

}
