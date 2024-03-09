package com.aiplusplus.favorites.doman.entity.file;

import com.aiplusplus.favorites.doman.entity.base.BaseEntity;
import com.aiplusplus.favorites.doman.enums.FileType;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 9:25
 * @package com.aiplusplus.favorites.doman.entity.file
 * @ClassName: FileStorage
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "文件存储表")
@TableName("file_storage")
public class FileStorage extends BaseEntity {
    @Schema(description = "原文件名")
    private String originalFileName;
    @Schema(description = "系统文件名")
    private String systemFileName;
    @Schema(description = "文件夹id")
    private Long folderId;
    @Schema(description = "文件类型")
    private FileType fileType;
    @Schema(description = "文件大小(Mb)")
    private BigDecimal fileSize;
}
