package com.sise.mapper;

import com.sise.entity.BaseDepartment;
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
public interface BaseDepartmentMapper extends BaseMapper<BaseDepartment> {

    /*
     * 学校院系数目
     */
    @Select("<script>SELECT COUNT(*) FROM base_department</script>")
    int selectDeparementCount();


}
