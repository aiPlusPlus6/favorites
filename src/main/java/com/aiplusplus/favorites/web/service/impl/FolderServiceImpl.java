package com.aiplusplus.favorites.web.service.impl;

import com.aiplusplus.favorites.common.customizeException.BizException;
import com.aiplusplus.favorites.doman.dto.file.FolderAddDTO;
import com.aiplusplus.favorites.doman.dto.file.FolderUpdateDTO;
import com.aiplusplus.favorites.doman.entity.SysUser;
import com.aiplusplus.favorites.doman.entity.file.Folder;
import com.aiplusplus.favorites.doman.vo.file.FileStorageVO;
import com.aiplusplus.favorites.doman.vo.file.FolderVO;
import com.aiplusplus.favorites.mapper.FileStorageMapper;
import com.aiplusplus.favorites.mapper.FolderMapper;
import com.aiplusplus.favorites.security.SecurityUtils;
import com.aiplusplus.favorites.web.service.FolderService;
import com.aiplusplus.favorites.web.service.MinioFileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
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
    private final MinioFileService minioFileService;
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
        Folder folder = folderMapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getId, folderId).eq(Folder::getCreateBy, currentUsername.getId()));
        if(!Objects.equals(folder.getVersion(), version)){
            throw new BizException("数据已被修改，请刷新页面");
        }
        //查询该文件目录下所有的文件
        List<FolderVO> folderVOList = folderMapper.selectFolderList(currentUsername.getId());
        Map<String,List<FolderVO>> folderVOListMap = folderVOList.stream().collect(Collectors.groupingBy(FolderVO::getParentFolderId));
        List<FolderVO> dfs = dfs(folderVOListMap, String.valueOf(folderId));
        List<FileStorageVO> fileStorageVOList = new ArrayList<>();
        List<String> deleteFolderId = new ArrayList<>();
        deleteFolderId.add(String.valueOf(folderId));
        //BFS取出其中所有的系统文件名
        if(!dfs.isEmpty()) {
            LinkedList<FolderVO> queue = new LinkedList<>(dfs);
            while (!queue.isEmpty()) {
                FolderVO poll = queue.poll();
                deleteFolderId.add(poll.getId());
                if (poll.getFileStorageVOList() != null) {
                    fileStorageVOList.addAll(poll.getFileStorageVOList());
                }
                if (poll.getChildFolderList() != null) {
                    queue.addAll(poll.getChildFolderList());
                }
            }
        }
        //文件名不为空
        if(!fileStorageVOList.isEmpty()){
            List<String> collect = fileStorageVOList.stream().map(FileStorageVO::getSystemFileName).toList();
            List<String> idList = fileStorageVOList.stream().map(FileStorageVO::getId).toList();
            try {
                minioFileService.deleteFiles(collect);
            } catch (Exception e) {
                throw new BizException("删除文件失败");
            }
            //真删除数据库数据
            Integer reallyDelete = fileStorageMapper.reallyDelete(idList);
            if(reallyDelete != idList.size()){
                throw new BizException("删除文件失败");
            }
        }
        //真实删除文件夹
        Integer reallyDelete = folderMapper.reallyDelete(deleteFolderId);
        if(reallyDelete != deleteFolderId.size()){
            throw new BizException("删除文件夹失败");
        }
    }

    @Override
    public void updateFolder(FolderUpdateDTO folderUpdateDTO) {

    }

    private List<FolderVO> dfs(Map<String,List<FolderVO>> folderVOListMap,  String folderId){
        List<FolderVO> folderVOS = folderVOListMap.get(folderId);
        for (FolderVO folderVO : folderVOS) {
            List<FolderVO> folderVOS1 = dfs(folderVOListMap, folderVO.getId());
            BigDecimal folderSize = folderVOS1.stream().map(FolderVO::getFolderSize).reduce(BigDecimal.ZERO, BigDecimal::add);
            folderSize = folderSize.add(folderVO.getFileStorageVOList().stream().map(FileStorageVO::getFileSize).reduce(BigDecimal.ZERO, BigDecimal::add));
            folderVO.setFolderSize(folderSize);
            folderVO.setChildFolderList(folderVOS1);
        }
        return folderVOS;
    }
}
