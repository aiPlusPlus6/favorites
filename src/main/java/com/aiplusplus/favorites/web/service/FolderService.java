package com.aiplusplus.favorites.web.service;

import com.aiplusplus.favorites.doman.dto.file.FolderAddDTO;
import com.aiplusplus.favorites.doman.entity.file.Folder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 10:58
 * @package com.aiplusplus.favorites.web.service
 * @ClassName: FolderService
 * @Description: TODO(描述)
 */
public interface FolderService extends IService<Folder> {
    void addFolder(FolderAddDTO folderAddDTO);

    void deleteFolder(Long folderId, Integer version);
}
