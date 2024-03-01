package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.doman.entity.simple.SimpleCity;
import com.aiplusplus.favorites.mapper.SimpleCityMapper;
import com.aiplusplus.favorites.web.service.SimpleCityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月01日 9:25
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: SimpleCityServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
public class SimpleCityServiceImpl extends ServiceImpl<SimpleCityMapper, SimpleCity> implements SimpleCityService {
}
