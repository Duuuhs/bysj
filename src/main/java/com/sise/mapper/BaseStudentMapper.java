package com.sise.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.*;
import com.sise.entity.BaseStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */
public interface BaseStudentMapper extends BaseMapper<BaseStudent> {

    @Select("<script> SELECT t1.student_id,t1.student_name,bs_choice.bs_topic_id,bs_topic.bs_topic_name,bs_choice.is_sure\n" +
            "FROM base_student t1\n" +
            "LEFT JOIN bs_choice on t1.student_id = bs_choice.student_id\n" +
            "LEFT JOIN bs_topic on bs_topic.bs_topic_id = bs_choice.bs_topic_id\n" +
            "WHERE 1 = 1\n" +
            "<if test=\"bysj_teacher_id != null\">" +
            "and t1.bysj_teacher_id = #{bysj_teacher_id}\n" +
            "</if>" +
            "ORDER BY t1.student_id ASC</script>")
    List<StuBsChoiceV2> selectStuBsChoiceByTea(Page page,@Param("bysj_teacher_id") Long bysj_teacher_id);


    @Select("<script>SELECT COUNT(*) FROM base_student</script>")
    int selectStuCount();


    @Select("<script>SELECT b1.student_id,b1.student_name,b6.major_name,b3.base_class_name, b4.teacher_name AS \"study_teacher_name\", b5.teacher_name AS \"tutor_teacher_name\", b7.teacher_name AS \"bysj_teacher_name\"\n" +
            "from base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.depatrment_id\n" +
            "LEFT JOIN base_class b3 on b3.base_class_id = b1.class_id\n" +
            "LEFT JOIN base_teacher b4 ON b4.teacher_id = b1.study_teacher_id\n" +
            "LEFT JOIN base_teacher b5 ON b5.teacher_id = b1.tutor_teacher_id\n" +
            "LEFT JOIN base_major b6 ON b6.major_id = b1.major_id\n" +
            "LEFT JOIN base_teacher b7 ON b7.teacher_id = b1.bysj_teacher_id\n"+
            "WHERE 1=1\n" +
            "<if test=\"base_department_id != null\">" +
            "and b1.depatrment_id = #{base_department_id}\n" +
            "</if>" +
            "<if test=\"student_id != null\">" +
            "and b1.student_id LIKE \"%\" #{student_id} \"%\"\n" +
            "</if>" +
            "<if test=\"student_name != null\">" +
            "and b1.student_name LIKE \"%\" #{student_name} \"%\"\n" +
            "</if>" +
            "<if test=\'hasBsTea == \"F\"\'>" +
            "and b1.bysj_teacher_id IS NULL\n" +
            "</if>" +
            "ORDER BY b1.student_id ASC </script>")
    List<StuMessageByManger> selectStuMessageByManger(Page page, @Param("base_department_id") Long base_department_id, @Param("student_id") String student_id, @Param("student_name") String student_name, @Param("hasBsTea") String hasBsTea);



    @Select("<script>SELECT COUNT(*) " +
            "from base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.depatrment_id\n" +
            "LEFT JOIN base_class b3 on b3.base_class_id = b1.class_id\n" +
            "LEFT JOIN base_teacher b4 ON b4.teacher_id = b1.study_teacher_id\n" +
            "LEFT JOIN base_teacher b5 ON b5.teacher_id = b1.tutor_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"base_department_id != null\">" +
            "and b1.depatrment_id = #{base_department_id}\n" +
            "</if>" +
            "<if test=\"student_id != null\">" +
            "and b1.student_id LIKE \"%\" #{student_id} \"%\"\n" +
            "</if>" +
            "<if test=\"student_name != null\">" +
            "and b1.student_name LIKE \"%\" #{student_name} \"%\"\n" +
            "</if>" +
            "<if test=\'hasBsTea == \"F\"\'>" +
            "and b1.bysj_teacher_id IS NULL\n" +
            "</if>" +
            "ORDER BY b1.student_id ASC </script>")
    int selectStuMessageByMangerCount(@Param("base_department_id") Long base_department_id, @Param("student_id") String student_id, @Param("student_name") String student_name, @Param("hasBsTea") String hasBsTea);



    @Select("<script>SELECT b1.student_id,b1.student_name,b2.base_department_name, b4.teacher_name AS \"study_teacher_name\", b5.teacher_name AS \"tutor_teacher_name\", b7.teacher_name AS \"bysj_teacher_name\"\n" +
            "from base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.depatrment_id\n" +
            "LEFT JOIN base_teacher b4 ON b4.teacher_id = b1.study_teacher_id\n" +
            "LEFT JOIN base_teacher b5 ON b5.teacher_id = b1.tutor_teacher_id\n" +
            "LEFT JOIN base_teacher b7 ON b7.teacher_id = b1.bysj_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"student_id != null\">" +
            "and b1.student_id LIKE \"%\" #{student_id} \"%\"\n" +
            "</if>" +
            "<if test=\"student_name != null\">" +
            "and b1.student_name LIKE  \"%\" #{student_name} \"%\" \n" +
            "</if>" +
            "<if test=\'hasBsTea == \"F\"\'>" +
            "and b1.bysj_teacher_id IS NULL\n" +
            "</if>" +
            "ORDER BY b1.depatrment_id ASC,b1.student_id ASC</script>")
    List<StuMessageByMangerV2> selectStuMessageByMangerV2(Page page,@Param("student_id") String student_id, @Param("student_name") String student_name, @Param("hasBsTea") String hasBsTea);



    @Select("<script>SELECT COUNT(*)\n" +
            "from base_student b1\n" +
            "LEFT JOIN base_department b2 ON b2.base_department_id = b1.depatrment_id\n" +
            "LEFT JOIN base_teacher b4 ON b4.teacher_id = b1.study_teacher_id\n" +
            "LEFT JOIN base_teacher b5 ON b5.teacher_id = b1.tutor_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"student_id != null\">" +
            "and b1.student_id LIKE \"%\" #{student_id} \"%\"\n" +
            "</if>" +
            "<if test=\"student_name != null\">" +
            "and b1.student_name LIKE  \"%\" #{student_name} \"%\" \n" +
            "</if>" +
            "<if test=\'hasBsTea == \"F\"\'>" +
            "and b1.bysj_teacher_id IS NULL\n" +
            "</if>" +
            "</script>")
    int selectStuMessageByMangerCountV2(@Param("student_id") String student_id, @Param("student_name") String student_name, @Param("hasBsTea") String hasBsTea);



    @Select("<script>SELECT b1.teacher_id, b1.teacher_name, b3.base_department_name, COUNT(b2.bysj_teacher_id) AS \"stu_count\"\n" +
            "FROM base_teacher b1\n" +
            "LEFT JOIN base_student b2 ON b2.bysj_teacher_id = b1.teacher_id\n" +
            "LEFT JOIN base_department b3 ON b3.base_department_id = b1.teacher_deparement_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"base_department_id != null\">" +
            "and b1.teacher_deparement_id = #{base_department_id}\n" +
            "</if>" +
            "GROUP BY(b1.teacher_id)\n" +
            "ORDER BY b1.teacher_deparement_id ASC , b1.teacher_id ASC</script>")
    List<TeaMessageByManger> selectTeaMessageByManger(Page page, @Param("base_department_id") Long base_department_id);


    @Select("<script>SELECT COUNT(*)\n" +
            "FROM base_teacher\n" +
            "WHERE 1=1\n" +
            "<if test=\"base_department_id != null\">" +
            "and base_teacher.teacher_deparement_id = #{base_department_id}\n" +
            "</if></script>")
    int selectTeaMessageByMangerCount(@Param("base_department_id") Long base_department_id);



    /*
     * 根据学生id查找该系所有老师(飞辅导员,辅导员是1开头,教师是2开头)
     */
    @Select("<script>SELECT b1.teacher_id, b1.teacher_name\n" +
            "FROM base_teacher b1\n" +
            "LEFT JOIN base_student b2 ON b2.depatrment_id = b1.teacher_deparement_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"student_id != null\">" +
            "and b2.student_id = #{student_id}\n" +
            "</if>"+
            "AND b1.teacher_id LIKE \'2%\'" +
            "ORDER BY b1.teacher_id ASC</script>")
    List<QueryTeaList> selectTeaList(@Param("student_id") Long student_id);



    /*
     * 管理员查看教师带的毕业设计学生
     */
    @Select("<script>SELECT b1.student_id, b1.student_name\n" +
            "FROM base_student b1\n" +
            "LEFT JOIN base_teacher b2 ON b2.teacher_id = b1.bysj_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"teacher_id != null\">" +
            "and b2.teacher_id = #{teacher_id}\n" +
            "</if></script>")
    List<TeaQueryNotChoiceStu> selectTeaBsStus(@Param("teacher_id") Long teacher_id);



    /*
     * 管理员查看教师的毕业设计题目
     */
    @Select("<script>SELECT b1.bs_topic_id, b1.bs_topic_name\n" +
            "FROM bs_topic b1\n" +
            "LEFT JOIN base_teacher b2 ON b2.teacher_id = b1.bs_teacher_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"teacher_id != null\">" +
            "and b2.teacher_id = #{teacher_id}\n" +
            "</if></script>")
    List<BsTopicContent> selectTeaBsTopic(@Param("teacher_id") Long teacher_id);

}
