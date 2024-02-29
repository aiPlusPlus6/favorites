package com.aiplusplus.favorites.security.login;

import com.aiplusplus.favorites.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 9:01
 * @package com.aiplusplus.favorites.security.login
 * @ClassName: CustomAuthenticationEntryPoint
 * @Description: TODO(未登录异常)
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        // 根据您的`R`类的设计，适当修改此处
        R<String> responseMessage = R.error("请先登录");
        responseMessage.setCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseMessage));
    }
}
