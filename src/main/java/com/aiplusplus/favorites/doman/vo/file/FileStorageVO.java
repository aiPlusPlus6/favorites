package com.aiplusplus.favorites.doman.vo.file;

import com.aiplusplus.favorites.doman.enums.FileType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 14:00
 * @package com.aiplusplus.favorites.doman.vo.file
 * @ClassName: FileStorageVO
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "文件存储VO")
public class FileStorageVO {
    @Schema(description = "文件id")
    private String id;
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
    @Schema(description = "版本号")
    private Integer version;
}
