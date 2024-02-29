package com.aiplusplus.favorites.web.service;

import com.aiplusplus.favorites.doman.dto.UserRegisterDTO;
import com.aiplusplus.favorites.doman.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 9:27
 * @package com.aiplusplus.favorites.web.service
 * @ClassName: UserService
 * @Description: TODO(描述)
 */
public interface UserService extends UserDetailsService {
    void register(UserRegisterDTO user);
}
