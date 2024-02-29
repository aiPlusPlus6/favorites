package com.aiplusplus.favorites.security.jwt;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.security.SecurityProperties;
import com.aiplusplus.favorites.web.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 9:25
 * @package com.aiplusplus.favorites.common
 * @ClassName: JwtFilter
 * @Description: TODO(登录权限类)
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    private final SecurityProperties securityProperties;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        //去除/前的路径
        List<String> ignoredPaths = securityProperties.getIgnoredPaths();
        if (shouldNotFilter(path,ignoredPaths)){
            filterChain.doFilter(request, response);
            return;
        }
        log.info("path:{}",path);
        String jwtToken = request.getHeader("Authorization");//从请求头中获取token
        if (jwtToken != null && !jwtToken.isEmpty() &&jwtUtil.checkToken(jwtToken)){
            try {//token可用
                Claims claims = jwtUtil.getTokenBody(jwtToken);
                String userName = (String) claims.get("userName");
                UserDetails user = userService.loadUserByUsername(userName);
                if (user != null){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e){
                log.error(e.getMessage());
                throw new BizException("请先登录!");
            }
        }else {
            log.warn("token is null or empty or out of time, probably user is not log in !");
            throw new BizException("请先登录!");
        }
        filterChain.doFilter(request, response);//继续过滤
    }
    // 检查路径是否匹配忽略鉴权的URL
    protected boolean shouldNotFilter(String path ,List<String> ignoreUrls){
        return ignoreUrls.stream().anyMatch(url -> pathMatcher.match(url, path));
    }
}
