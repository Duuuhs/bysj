package com.sise.mapper;

import com.sise.bean.TJclass;
import com.sise.bean.TJclasses;
import com.sise.bean.TJdepart;
import com.sise.bean.TJsex;
import com.sise.entity.BaseClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DMY
 * @since 2019-03-03
 */
public interface BaseClassMapper extends BaseMapper<BaseClass> {


    /*
     *统计班级人数(系)
     */
    @Select("<script>SELECT b1.base_class_name,COUNT(b1.base_class_name) AS student_num\n" +
            "FROM base_class b1\n" +
            "LEFT JOIN base_student b2 ON b2.class_id = b1.base_class_id\n" +
            "LEFT JOIN base_department b3 ON b3.base_department_id = b1.base_department_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"base_department_id != null\">" +
            "and b1.base_department_id = #{base_department_id}\n" +
            "</if> "+
            "GROUP BY b1.base_class_name\n" +
            "ORDER BY b1.base_class_id ASC</script>")
    List<TJclasses> selectClasses(@Param("base_department_id") Long base_department_id);


    /*
     *统计性别(DM/SM)
     */
    @Select("<script>SELECT base_student.student_sex, COUNT(base_student.student_sex) AS stu_count\n" +
            "FROM base_student\n" +
            "WHERE 1=1\n" +
            "<if test=\"depatrment_id != null\">" +
            "and base_student.depatrment_id = #{depatrment_id}\n" +
            "</if> "+
            "AND base_student.student_sex IS NOT NULL\n" +
            "GROUP BY base_student.student_sex</script>")
    List<TJsex> selectStuSex(@Param("depatrment_id") Long depatrment_id);


    /*
     *统计系别人数(SM)
     */
    @Select("<script>SELECT b1.base_department_name AS department_name, COUNT(b1.base_department_id) AS depart_count\n" +
            "FROM base_student b2\n" +
            "LEFT JOIN base_department b1 ON b1.base_department_id = b2.depatrment_id\n" +
            "WHERE 1=1\n" +
            "AND b2.depatrment_id IS NOT NULL\n" +
            "GROUP BY b1.base_department_id\n" +
            "ORDER BY b1.base_department_id ASC</script>")
    List<TJdepart> selectDepart();


    /*
     * 无毕设老师，未选题
     */
    @Select("<script>SELECT COUNT(b1.student_id)\n" +
            "FROM base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.bysj_teacher_id\n" +
            "WHERE 1=1\n" +
            "AND b1.bysj_teacher_id IS  NULL\n" +
            "<if test=\"depatrment_id != null\">" +
            "and b1.depatrment_id = #{depatrment_id}\n" +
            "</if> "+
            "GROUP BY (b1.bysj_teacher_id IS NULL)</script>")
    int selectNotTea(@Param("depatrment_id") Long depatrment_id);


    /*
     * 有毕设老师，未选题
     */
    @Select("<script>SELECT COUNT(b1.student_id)\n" +
            "FROM base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.bysj_teacher_id\n" +
            "LEFT JOIN bs_choice b3 ON b3.student_id = b1.student_id\n" +
            "WHERE 1=1\n" +
            "AND b1.bysj_teacher_id IS NOT NULL\n" +
            "AND b3.bs_choice_id IS  NULL\n" +
            "<if test=\"depatrment_id != null\">" +
            "and b1.depatrment_id = #{depatrment_id}\n" +
            "</if> "+
            "GROUP BY (b3.bs_choice_id IS NULL)</script>")
    int selectHasTeaNoChoice(@Param("depatrment_id") Long depatrment_id);


    /*
     * 有毕设老师，已选题
     */
    @Select("<script>SELECT COUNT(b1.student_id)\n" +
            "FROM base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.bysj_teacher_id\n" +
            "LEFT JOIN bs_choice b3 ON b3.student_id = b1.student_id\n" +
            "WHERE 1=1\n" +
            "AND b1.bysj_teacher_id IS NOT NULL\n" +
            "AND b3.bs_choice_id IS NOT NULL\n" +
            "<if test=\"depatrment_id != null\">" +
            "and b1.depatrment_id = #{depatrment_id}\n" +
            "</if> "+
            "GROUP BY (b3.bs_choice_id IS NOT NULL)</script>")
    int selectHasTeaHasChoice(@Param("depatrment_id") Long depatrment_id);
}
