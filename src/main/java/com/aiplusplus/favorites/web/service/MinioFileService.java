package com.aiplusplus.favorites.web.service;


import com.aiplusplus.favorites.doman.enums.FileType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 9:55
 * @package com.aiplusplus.favorites.web.service
 * @ClassName: MinioFileService
 * @Description: TODO(描述)
 */
public interface MinioFileService {
    //上传

    /**
     * 上传文件
     * @param file 文件
     * @return 系统文件名
     */
    String uploadFile(MultipartFile file) throws Exception;
    //删除

    /**
     * 删除文件
     * @param fileName 系统文件名
     */
    void deleteFile(String fileName) throws Exception;
    //预览

    /**
     * 预览文件
     * @param fileName 系统文件名
     * @param time 预览时间（分钟）
     * @return 预览地址
     */
    String previewFile(String fileName,Integer time) throws Exception;

    /**
     * 验证文件类型
     * @param file 文件
     * @return 文件类型
     */
    FileType validateFileType(MultipartFile file);
}
