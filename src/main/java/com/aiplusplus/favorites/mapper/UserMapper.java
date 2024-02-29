package com.aiplusplus.favorites.mapper;


import com.aiplusplus.favorites.doman.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 10:56
 * @package com.aiplusplus.favorites.mapper
 * @ClassName: UserMapper
 * @Description: TODO(描述)
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
    SysUser selectSecurityUser(@Param("userName")String userName);
}
