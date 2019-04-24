package com.sise.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.StuBsChoiceStatus;
import com.sise.bean.TeaQueryNotChoiceStu;
import com.sise.entity.BsChoice;
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
 * @since 2019-02-13
 */
public interface BsChoiceMapper extends BaseMapper<BsChoice> {

    @Select("<script>SELECT base_student.student_id,base_student.student_name\n" +
            "FROM base_student\n" +
            "LEFT JOIN bs_choice on bs_choice.student_id = base_student.student_id\n" +
            "WHERE 1=1 \n" +
            "<if test =\"bysj_teacher_id != null\">" +
            "</if>" +
            "AND base_student.bysj_teacher_id = #{bysj_teacher_id} \n" +
            "AND ISNULL(bs_choice.student_id)\n" +
            "ORDER BY base_student.student_id ASC</script>")
    List<TeaQueryNotChoiceStu> selectNotChoiceStu(@Param("bysj_teacher_id") Long bysj_teacher_id);


    /*
     *查看教师下学生的选题状态(评分意见模块)
     */
    @Select("<script>SELECT base_student.student_id,base_student.student_name,bs_choice.bs_choice_id,bs_choice.is_sure\n" +
            "FROM base_student\n" +
            "LEFT JOIN bs_choice on bs_choice.student_id = base_student.student_id\n" +
            "WHERE 1=1 \n" +
            "<if test =\"bysj_teacher_id != null\">" +
            "</if>" +
            "AND base_student.bysj_teacher_id = #{bysj_teacher_id} \n" +
            "AND bs_choice.bs_topic_id != \"\" \n" +
            "ORDER BY base_student.student_id ASC</script>")
    List<StuBsChoiceStatus> selectStuChoiceStatus(Page page, @Param("bysj_teacher_id") Long bysj_teacher_id);



    /*
     *查看教师下学生的选题转态的条数(评分意见模块)
     */
    @Select("<script>SELECT COUNT(*)\n" +
            "FROM base_student\n" +
            "LEFT JOIN bs_choice on bs_choice.student_id = base_student.student_id\n" +
            "WHERE 1=1 \n" +
            "<if test =\"bysj_teacher_id != null\">" +
            "</if>" +
            "AND base_student.bysj_teacher_id = #{bysj_teacher_id} \n" +
            "AND bs_choice.bs_topic_id != \"\" \n" +
            "ORDER BY base_student.student_id ASC</script>")
    int selectStuChoiceStatusCount(@Param("bysj_teacher_id") Long bysj_teacher_id);


    /*
     *查询某个系别已确认选题的毕业生人数
     */
    @Select("<script>SELECT COUNT(*) FROM bs_choice\n" +
            "LEFT JOIN base_student ON base_student.student_id = bs_choice.student_id\n" +
            "WHERE 1=1\n" +
            "<if test =\"depatrment_id != null\">" +
            "</if>" +
            "AND base_student.depatrment_id = #{depatrment_id}\n" +
            "AND bs_choice.is_sure = \"T\"</script>")
    int selectStuSureByDepartmentId(@Param("depatrment_id") Long department_id);



    /*
     *查询已确认选题的毕业生人数
     */
    @Select("<script>SELECT COUNT(*) FROM bs_choice\n" +
            "LEFT JOIN base_student ON bs_choice.student_id = base_student.student_id\n" +
            "WHERE 1=1\n" +
            "AND bs_choice.is_sure = \"T\"</script>")
    int selectStuSureCount();
}
