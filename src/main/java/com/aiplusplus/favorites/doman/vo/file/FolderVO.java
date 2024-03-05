package com.aiplusplus.favorites.doman.vo.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 13:54
 * @package com.aiplusplus.favorites.doman.vo.file
 * @ClassName: FolderVO
 * @Description: TODO(描述)
 */
@Data
@Schema(name = "文件夹VO")
public class FolderVO {
    @Schema(description = "文件夹id")
    private String id;
    @Schema(description = "文件夹名")
    private String folderName;
    @Schema(description = "父类文件夹id")
    private String parentFolderId;
    @Schema(description = "版本号")
    private Integer version;
    @Schema(description = "文件夹大小(Mb)")
    private BigDecimal folderSize;
    @Schema(description = "子文件夹")
    private List<FolderVO> childFolderList;
    @Schema(description = "文件")
    private List<FileStorageVO> fileStorageVOList;
}
