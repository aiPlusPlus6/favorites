package com.aiplusplus.favorites.doman.entity;

import com.aiplusplus.favorites.doman.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 11:13
 * @package com.aiplusplus.favorites.doman.entity
 * @ClassName: SysRole
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "系统角色实体类")
@TableName("sys_role")
public class SysRole extends BaseEntity {
    @Schema(name = "角色名称")
    private String roleName;
    @Schema(name = "显示顺序")
    private Integer roleSort;
    @Schema(name = "数据范围 1：全部数据权限 2：自定数据权限")
    private Integer dataScope;
    @Schema(name = "角色状态（0正常 1停用）")
    private Integer status;
}
