package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.doman.dto.file.FolderAddDTO;
import com.aiplusplus.favorites.doman.entity.SysUser;
import com.aiplusplus.favorites.doman.entity.file.Folder;
import com.aiplusplus.favorites.doman.vo.file.FolderVO;
import com.aiplusplus.favorites.mapper.FileStorageMapper;
import com.aiplusplus.favorites.mapper.FolderMapper;
import com.aiplusplus.favorites.security.SecurityUtils;
import com.aiplusplus.favorites.web.service.FolderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 10:59
 * @package com.aiplusplus.favorites.web.service.impl
 * @ClassName: FolderServiceImpl
 * @Description: TODO(描述)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements FolderService {
    private final FolderMapper folderMapper;
    private final FileStorageMapper fileStorageMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFolder(FolderAddDTO folderAddDTO) {
        SysUser currentUsername = SecurityUtils.getCurrentUsername();
        if(folderAddDTO.getParentFolderId() != 0){
            //校验父文件夹是否存在
            Folder folder = folderMapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getId, folderAddDTO.getParentFolderId()).eq(Folder::getCreateBy, currentUsername.getId()));
            if(folder==null){
                throw new BizException("父文件夹不存在");
            }
        }
        //校验同级是否有相同的文件夹
        Folder folder = folderMapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getFolderName, folderAddDTO.getFolderName()).eq(Folder::getParentFolderId, folderAddDTO.getParentFolderId()).eq(Folder::getCreateBy, currentUsername.getId()));
        if(folder != null){
            throw new BizException("文件夹已存在");
        }
        folder = new Folder();
        BeanUtils.copyProperties(folderAddDTO, folder);
        folder.setVersion(1);
        folder.setDeleted(0);
        int insert = folderMapper.insert(folder);
        if(insert != 1){
            throw new BizException("新增文件夹失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFolder(Long folderId, Integer version) {
        SysUser currentUsername = SecurityUtils.getCurrentUsername();
        //查询该文件目录下所有的文件
        List<FolderVO> folderVOList = folderMapper.selectFolderList(currentUsername.getId());
        Map<String,List<FolderVO>> folderVOListMap = folderVOList.stream().collect(Collectors.groupingBy(FolderVO::getParentFolderId));
        List<FolderVO> dfs = dfs(folderVOListMap, String.valueOf(folderId));

    }

    private List<FolderVO> dfs(Map<String,List<FolderVO>> folderVOListMap,  String folderId){
        List<FolderVO> folderVOS = folderVOListMap.get(folderId);
        for (FolderVO folderVO : folderVOS) {
            List<FolderVO> folderVOS1 = dfs(folderVOListMap, folderVO.getId());
            folderVO.setChildFolderList(folderVOS1);
        }
        return folderVOS;
    }
}
