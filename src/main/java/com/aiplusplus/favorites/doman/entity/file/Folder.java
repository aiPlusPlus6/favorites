package com.aiplusplus.favorites.doman.entity.file;

import com.aiplusplus.favorites.doman.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 9:17
 * @package com.aiplusplus.favorites.doman.entity.file
 * @ClassName: Folder
 * @Description: TODO(描述)
 */
@Data
@TableName("folder")
@Schema(name = "文件夹表")
public class Folder extends BaseEntity {
    @Schema(description = "文件夹名")
    private String folderName;
    @Schema(description = "父类文件夹id")
    private Long parentFolderId;
}
