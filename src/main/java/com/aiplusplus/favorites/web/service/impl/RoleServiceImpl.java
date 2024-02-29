package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.doman.entity.SysRole;
import com.aiplusplus.favorites.mapper.RoleMapper;
import com.aiplusplus.favorites.web.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 16:22
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: RoleServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements RoleService {
}
