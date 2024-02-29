package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.doman.dto.UserRegisterDTO;
import com.aiplusplus.favorites.doman.entity.SysUser;
import com.aiplusplus.favorites.mapper.UserMapper;
import com.aiplusplus.favorites.web.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 9:28
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: UserServiceImpl
 * @Description: TODO(描述)
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Lazy
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.selectSecurityUser(username);
        if (sysUser==null){
            throw new BizException("用户不存在");
        }
        return sysUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterDTO user) {
        SysUser one = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, user.getUserName()));
        if(one!=null){
            throw new BizException("用户名重复");
        }
        one = userMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, user.getPhone()));
        if(one!=null){
            throw new BizException("该手机号已注册");
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user,sysUser);
        sysUser.setStatus(0);
        String encode = passwordEncoder.encode(user.getPassword());
        sysUser.setPassword(encode);
        sysUser.setVersion(1);
        sysUser.setDeleted(0);
        int insert = userMapper.insert(sysUser);
        if(insert!=1){
            throw new BizException("注册失败,请联系管理员");
        }
    }
}
