package com.sise.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.QueryStuUpLoad;
import com.sise.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DMY
 * @since 2019-03-04
 */
public interface FileMapper extends BaseMapper<File> {


    /*查找教师下的毕业学生上传文件的情况(分页)*/
    @Select("<script>select  b1.student_id, b1.student_name, b3.file_id, b3.file_name, b3.file_time\n" +
            "from file b3\n" +
            "LEFT JOIN base_student b1 on b1.student_id = b3.file_user_id\n" +
            "LEFT JOIN base_teacher b2 on b2.teacher_id = b1.bysj_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"teacher_id != null\">" +
            "and b2.teacher_id = #{teacher_id}" +
            "</if>" +
            "ORDER BY b3.file_time DESC</script>")
    List<QueryStuUpLoad> selectStuUpLoadPage(Page page, @Param("teacher_id") Long teacher_id);



    @Select("<script>select  COUNT(*)\n" +
            "from file b3\n" +
            "LEFT JOIN base_student b1 on b1.student_id = b3.file_user_id\n" +
            "LEFT JOIN base_teacher b2 on b2.teacher_id = b1.bysj_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"teacher_id != null\">" +
            "and b2.teacher_id = #{teacher_id}" +
            "</if>" +
            "</script>")
    int selectStuUpLoadCount(@Param("teacher_id") Long teacher_id);

}
