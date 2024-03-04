package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.time.Duration;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月04日 15:56
 * @package com.aiplusplus.favorites.web
 * @ClassName: MinioFileController
 * @Description: TODO(描述)
 */
@Tag(name = "文件管理")
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/minio/file")
public class MinioFileController {
    @Resource
    private MinioClient minioClient;
    @Value("${minio.bucketName}")
    private String bucketName;

    //文件上传
    @PostMapping("/upload")
    public R<String> uploadFile(MultipartFile file) throws Exception{
        // 获取文件名和内容类型
        String fileName = file.getOriginalFilename();
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
        return R.ok(objectWriteResponse.object());
    }
    //删除文件
    @DeleteMapping("/delete/{fileName}")
    public R<String> deleteFile(@PathVariable String fileName) throws Exception{
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
        return R.ok("删除成功");
    }

    //获取预览地址
    @GetMapping("/preview/{fileName}")
    public R<String> previewFile(@PathVariable String fileName) throws Exception{
        int duration = 60*4;
        String presignedUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder() .method(Method.GET)
                .bucket(bucketName)
                .object(fileName)
                .expiry(duration).build());
        return R.ok(presignedUrl);
    }
}
