package com.aiplusplus.favorites.doman.entity.base;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 李俊杰
 * {@code @date} 2024年02月29日 17:46
 * @package com.aiplusplus.favorites.doman.entity.base
 * @ClassName: DateEntity
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "时间实体类")
public class DateEntity {
    @Schema(name = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @Schema(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @Schema(name = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDate;
    @Schema(name = "删除标识 0-未删除 1-删除")
    @TableLogic
    private Integer deleted;
}
