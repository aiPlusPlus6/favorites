package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.doman.entity.file.FileStorage;
import com.aiplusplus.favorites.mapper.FileStorageMapper;
import com.aiplusplus.favorites.web.service.FileStorageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 13:35
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: FileStorageServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl extends ServiceImpl<FileStorageMapper, FileStorage> implements FileStorageService {
}
