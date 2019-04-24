package com.sise.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sise.bean.TeaQueryBsTopic;
import com.sise.entity.BsTopic;
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
public interface BsTopicMapper extends BaseMapper<BsTopic> {

    @Select("<script>SELECT bs_topic.bs_topic_id,bs_topic.bs_topic_name,base_student.student_id,base_student.student_name,bs_choice.is_sure\n" +
            "FROM bs_topic\n" +
            "LEFT JOIN bs_choice on bs_choice.bs_topic_id = bs_topic.bs_topic_id\n" +
            "LEFT JOIN base_student on base_student.student_id = bs_choice.student_id\n" +
            "WHERE 1=1\n" +
            "<if test=\"bs_teacher_id != null\">" +
            "and bs_topic.bs_teacher_id = #{bs_teacher_id}" +
            "</if>" +
            "ORDER BY bs_topic.bs_topic_id ASC</script>")
    List<TeaQueryBsTopic> selectBsTopicByTea(Page page, @Param("bs_teacher_id") Long bs_teacher_id);

}
