package com.aiplusplus.favorites.mapper;

import com.aiplusplus.favorites.doman.entity.simple.SimpleCity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月01日 9:24
 * @package com.aiplusplus.favorites.mapper
 * @ClassName: SimpleCityMapper
 * @Description: TODO(描述)
 */
@Mapper
public interface SimpleCityMapper extends BaseMapper<SimpleCity> {
    SimpleCity getIpOne(@Param("province") String province, @Param("city") String city);
}
