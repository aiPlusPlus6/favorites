package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.doman.enums.FileType;
import com.aiplusplus.favorites.web.service.MinioFileService;
import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 10:15
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: MinioFileServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioFileServiceImpl implements MinioFileService {
    private final MinioClient minioClient;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        // 获取文件名和内容类型
        String fileName = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
        //uuid重新设置文件名
        String contentType = file.getContentType();
        // 使用minioClient上传文件
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(contentType)
                        .build()
        );
        return fileName;
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }

    @Override
    public String previewFile(String fileName, Integer time) throws Exception {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder() .method(Method.GET)
                .bucket(bucketName)
                .object(fileName)
                .expiry(time).build());
    }

    //校验上传的文件是否符合要求
    public FileType validateFileType(MultipartFile file){
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            throw new BizException("文件名为空或没有扩展名");
        }
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
        return switch (extension){
            case "ZIP" -> FileType.ZIP;
            case "PNG", "JPG", "JPEG", "GIF" -> FileType.IMAGE;
            case "DOC", "DOCX", "PDF", "TXT" -> FileType.DOCUMENT;
            case "MP4", "AVI", "MOV" -> FileType.VIDEO;
            case "MP3", "WAV", "AAC" -> FileType.AUDIO;
            case "XLS", "XLSX" -> FileType.SPREADSHEET;
            case "PPT", "PPTX" -> FileType.PRESENTATION;
            default -> throw new BizException("不支持的文件类型");
        };
    }
}
