package com.aiplusplus.favorites.security;



import com.aiplusplus.favorites.common.prefix.SecurityProperties;
import com.aiplusplus.favorites.security.jwt.JwtFilter;

import com.aiplusplus.favorites.security.login.CustomAccessDeniedHandler;
import com.aiplusplus.favorites.security.login.CustomAuthenticationEntryPoint;
import com.aiplusplus.favorites.web.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 11:25
 * @package com.aiplusplus.favorites.security
 * @ClassName: SecurityConfig
 * @Description: TODO(Security配置)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private UserService userService;
    @Resource
    private JwtFilter jwtFilter;
    @Resource
    private SecurityProperties securityProperties;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        List<String> ignoredPaths = securityProperties.getIgnoredPaths();
        httpSecurity
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // 未登录
                        .accessDeniedHandler(customAccessDeniedHandler) // 无权限
                )
                .formLogin(AbstractHttpConfigurer::disable) // 取消默认登录页面的使用
                .logout(AbstractHttpConfigurer::disable) // 取消默认登出页面的使用
                .csrf(AbstractHttpConfigurer::disable) // 禁用csrf保护，前后端分离不需要
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 禁用session，因为我们已经使用了JWT
                .httpBasic(AbstractHttpConfigurer::disable); // 禁用httpBasic

        // 先配置忽略的路径
        if (ignoredPaths != null && !ignoredPaths.isEmpty()) {
            httpSecurity.authorizeHttpRequests(auth -> auth
                    .requestMatchers(ignoredPaths.toArray(new String[0])).permitAll());
        }

        // 然后配置其他所有请求都需要认证
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // JWT过滤器

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
