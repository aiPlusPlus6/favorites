package com.aiplusplus.favorites.doman.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月20日 9:13
 * @package com.aiplusplus.favorites.doman
 * @ClassName: BaseEntity
 * @Description: TODO(所有数据库实体类的基类)
 */
@Data
@Schema(name = "基础实体类")
public class BaseEntity {
    @Schema(name = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @Schema(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @Schema(name = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDate;
    @Schema(name = "创建者id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @Schema(name = "修改者id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @Schema(name = "版本号")
    @Version
    private Integer version;
    @Schema(name = "删除标识 0-未删除 1-删除")
    @TableLogic
    private Integer deleted;
}
