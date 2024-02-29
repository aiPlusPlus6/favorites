package com.aiplusplus.favorites.security;

import com.aiplusplus.favorites.doman.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 11:33
 * @package com.aiplusplus.favorites.security
 * @ClassName: SecurityUtils
 * @Description: TODO(描述)
 */
public class SecurityUtils {
    public static SysUser getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof SysUser) {
            return ((SysUser) principal);
        }
        return null;
    }
}
