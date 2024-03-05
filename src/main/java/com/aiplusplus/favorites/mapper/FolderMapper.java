package com.aiplusplus.favorites.mapper;

import com.aiplusplus.favorites.doman.entity.file.Folder;
import com.aiplusplus.favorites.doman.vo.file.FolderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 10:58
 * @package com.aiplusplus.favorites.mapper
 * @ClassName: FolderMapper
 * @Description: TODO(描述)
 */
@Mapper
public interface FolderMapper extends BaseMapper<Folder> {

    List<FolderVO> selectFolderList(@Param("id") Long id);
}
