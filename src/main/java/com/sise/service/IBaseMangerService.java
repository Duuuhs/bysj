package com.sise.service;

import com.sise.bean.DmMessage;
import com.sise.bean.SmManger;
import com.sise.entity.BaseManger;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-03-01
 */
public interface IBaseMangerService extends IService<BaseManger> {

    /*
     * 获取部门管理员的基本信息
     */
    DmMessage getDmMessage(Long department_id);


    /*
     * 获取部门管理员的基本信息
     */
    SmManger getSmMessage();

}
