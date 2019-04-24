package com.sise.service;

import com.sise.bean.FileV2;
import com.sise.bean.QueryStuUpLoadPage;
import com.sise.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DMY
 * @since 2019-03-04
 */
public interface IFileService extends IService<File> {


    /*查找教师下的毕业学生上传文件的情况(分页)*/
    QueryStuUpLoadPage getStuUpLoads(Long teacher_id, int page_num);


    /*查找教师上传的资源*/
    List<FileV2> getTeaUpLoad(Long teacher_id);

}
