package com.sise.mapper;

import com.sise.entity.BaseTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DMY
 * @since 2019-02-21
 */
public interface BaseTeacherMapper extends BaseMapper<BaseTeacher> {

    @Select("<script>SELECT COUNT(*) FROM base_teacher</script>")
    int selectTeaCount();
}
