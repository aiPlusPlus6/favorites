package com.aiplusplus.favorites.doman.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月08日 8:49
 * @package com.aiplusplus.favorites.doman.dto.file
 * @ClassName: FolderUpdateDTO
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "文件夹更新DTO")
public class FolderUpdateDTO {
    @NotNull(message = "文件夹id不能为空")
    @Schema(description = "文件夹id")
    private Long id;
    @NotBlank(message = "文件夹名不能为空")
    @Schema(description = "文件夹名")
    private String folderName;
    @NotNull(message = "父类文件夹id不能为空")
    @Schema(description = "父类文件夹id")
    private Long parentFolderId;
    @NotNull(message = "版本号不能为空")
    @Schema(description = "版本号")
    private Integer version;
}
