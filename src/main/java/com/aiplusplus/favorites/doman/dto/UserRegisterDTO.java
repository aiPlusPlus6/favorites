package com.aiplusplus.favorites.doman.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



/**
 * @author 李俊杰
 * {@code @date} 2024年02月28日 11:53
 * @package com.aiplusplus.favorites.doman.dto
 * @ClassName: UserRegisterDTO
 * @Description: TODO(描述)
 */
@Data
public class UserRegisterDTO {
    @Schema(name = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @Schema(name = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    @Schema(name = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
    @Schema(name = "性别")
    @NotNull(message = "性别不能为空")
    private Integer gender;
    @Schema(name = "头像存储地址")
    @NotBlank(message = "头像不能为空")
    private String avatar;
    @Schema(name = "个性签名")
    @NotBlank(message = "个性签名不能为空")
    private String signature;

}
