package com.aiplusplus.favorites.doman.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 14:50
 * @package com.aiplusplus.favorites.doman.dto
 * @ClassName: RoleAddDTO
 * @Description: TODO(描述)
 */

@Schema(name = "RoleAddDTO", description = "角色新增")
@Data
public class RoleAddDTO {
    @Schema(name = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    @Schema(name = "显示顺序")
    private Integer roleSort;
    @Schema(name = "数据范围 1：全部数据权限 2：自定数据权限")
    @NotNull(message = "数据范围不能为空")
    private Integer dataScope;
}
