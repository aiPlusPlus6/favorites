package com.aiplusplus.favorites.security.login;

import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 16:49
 * @package com.aiplusplus.favorites.security
 * @ClassName: LoginRequest
 * @Description: TODO(描述)
 */
@Data
public class LoginRequest {
    private String userName;
    private String passWord;
}
