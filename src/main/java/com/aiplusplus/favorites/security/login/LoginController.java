package com.aiplusplus.favorites.security.login;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.security.SecurityUtils;
import com.aiplusplus.favorites.security.jwt.JwtUtil;
import com.aiplusplus.favorites.doman.dto.UserRegisterDTO;
import com.aiplusplus.favorites.doman.entity.SysUser;
import com.aiplusplus.favorites.web.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 16:48
 * @package com.aiplusplus.favorites.security
 * @ClassName: LoginController
 * @Description: TODO(描述)
 */
@RequestMapping("/api")
@ResponseBody
@RestController
public class LoginController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserService userService;
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord());
        Authentication authentication = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtil.getToken(userDetails.getUsername());
        return R.ok(jwtToken);
    }

    @PostMapping("/register")
    public R<String> register(@RequestBody @Validated UserRegisterDTO user) {
        userService.register(user);
        return R.ok("注册成功");
    }

    @PostMapping("/logout")
    private R<String> logout() {
        SysUser currentUsername = SecurityUtils.getCurrentUsername();
        jwtUtil.deleteToken(currentUsername.getUserName());
        SecurityContextHolder.clearContext();
        return R.ok("退出成功");
    }
}
