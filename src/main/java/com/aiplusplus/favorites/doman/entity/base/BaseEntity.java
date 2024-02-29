package com.aiplusplus.favorites.doman.entity.base;

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
public class BaseEntity extends DateEntity{
    @Schema(name = "创建者id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @Schema(name = "修改者id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @Schema(name = "版本号")
    @Version
    private Integer version;
}
