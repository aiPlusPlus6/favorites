package com.aiplusplus.favorites.security.login;

import com.aiplusplus.favorites.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 9:16
 * @package com.aiplusplus.favorites.security.login
 * @ClassName: CustomAccessDeniedHandler
 * @Description: TODO(权限异常)
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 设置403状态码
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        // 根据您的`R`类的设计，适当修改此处
        R<String> responseMessage = R.error("无访问权限");
        responseMessage.setCode(String.valueOf(HttpServletResponse.SC_FORBIDDEN));
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseMessage));
    }
}
