package com.aiplusplus.favorites.doman.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 9:32
 * @package com.aiplusplus.favorites.doman.enums
 * @ClassName: FileType
 * @Description: TODO(描述)
 */
@Getter
@Schema(name = "文件类型")
public enum FileType {
    ZIP("压缩包","ZIP"),
    IMAGE("图片","IMAGE"),
    DOCUMENT("文档","DOCUMENT"),
    VIDEO("视频","VIDEO"),
    AUDIO("音频","AUDIO"),
    SPREADSHEET("表格", "SPREADSHEET"),
    PRESENTATION("演示文稿", "PRESENTATION");
    private final String name;
    private final String value;
    FileType(String name,String value){
        this.name = name;
        this.value = value;
    }
}
