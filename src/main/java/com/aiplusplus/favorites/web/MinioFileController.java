package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.web.service.MinioFileService;
import com.aiplusplus.favorites.web.service.impl.MinioFileServiceImpl;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;


/**
 * @author 李俊杰
 * {@code @date} 2024年03月04日 15:56
 * @package com.aiplusplus.favorites.web
 * @ClassName: MinioFileController
 * @Description: TODO(描述)
 */
@Tag(name = "minio文件管理")
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/minio/file")
public class MinioFileController {
    @Resource
    private MinioFileService minioFileService;

    //文件上传
    @PostMapping("/upload")
    public R<String> uploadFile(@RequestParam("file")MultipartFile file) throws Exception{
        return R.ok(minioFileService.uploadFile(file));
    }
    //删除文件
    @DeleteMapping("/delete/{fileName}")
    public R<String> deleteFile(@PathVariable String fileName) throws Exception{
        minioFileService.deleteFile(fileName);
        return R.ok("删除成功");
    }
}
