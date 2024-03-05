package com.aiplusplus.favorites.doman.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 11:09
 * @package com.aiplusplus.favorites.doman.dto.file
 * @ClassName: FolderAddDTO
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "新增文件夹DTO")
public class FolderAddDTO {
    @Schema(description = "文件夹名")
    private String folderName;
    @Schema(description = "父类文件夹id")
    private Long parentFolderId;
}
