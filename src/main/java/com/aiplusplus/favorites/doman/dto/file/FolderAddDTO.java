package com.aiplusplus.favorites.doman.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "文件夹名不能为空")
    private String folderName;
    @Schema(description = "父类文件夹id")
    @NotNull(message = "父类文件夹id不能为空")
    private Long parentFolderId;
}
