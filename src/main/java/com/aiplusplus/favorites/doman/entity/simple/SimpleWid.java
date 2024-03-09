package com.aiplusplus.favorites.doman.entity.simple;

import com.aiplusplus.favorites.doman.entity.base.DateEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月02日 9:50
 * @package com.aiplusplus.favorites.doman.entity.simple
 * @ClassName: SimpleWid
 * @Description: TODO(描述)
 */
@Data
@TableName("simple_wid")
@Schema(name = "天气种类表")
public class SimpleWid extends DateEntity {
    @Schema(name = "天气种类id")
    private String wid;
    @Schema(name = "天气种类")
    private String weather;
}
