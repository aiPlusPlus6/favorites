package com.aiplusplus.favorites.mapper;

import com.aiplusplus.favorites.doman.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 16:23
 * @package com.aiplusplus.favorites.mapper
 * @ClassName: RoleMapper
 * @Description: TODO(描述)
 */
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
    Integer selectMaxRoleSort();
}
