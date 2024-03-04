package com.aiplusplus.favorites.doman.entity.simple;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月04日 14:21
 * @package com.aiplusplus.favorites.doman.entity.simple
 * @ClassName: SimpleCityUser
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "天气支持城市用户表")
public class SimpleCityUser {
    @Schema(name = "id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @Schema(name = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @Schema(name = "创建者id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @Schema(name = "天气id")
    private Long simpleCityId;
}
