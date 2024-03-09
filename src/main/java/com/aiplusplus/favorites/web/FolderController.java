package com.aiplusplus.favorites.web;

import com.aiplusplus.favorites.common.R;
import com.aiplusplus.favorites.doman.dto.file.FolderAddDTO;
import com.aiplusplus.favorites.doman.dto.file.FolderUpdateDTO;
import com.aiplusplus.favorites.web.service.FolderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李俊杰
 * {@code @date} 2024年03月05日 10:57
 * @package com.aiplusplus.favorites.web
 * @ClassName: FolderController
 * @Description: TODO(描述)
 */
@Tag(name = "文件夹管理")
@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/folder")
public class FolderController {
    private final FolderService folderService;
    //新增文件夹
    @PostMapping("/add")
    public R<String> addFolder(@RequestBody FolderAddDTO folderAddDTO) {
        folderService.addFolder(folderAddDTO);
        return R.ok("新增成功");
    }
    //删除文件夹
    @PostMapping("/delete/{folderId}")
    public R<String> deleteFolder(@PathVariable Long folderId,@RequestParam Integer version) {
        folderService.deleteFolder(folderId,version);
        return R.ok("删除成功");
    }
    //修改文件夹
    @PostMapping("/update")
    public R<String> updateFolder(@RequestBody @Validated FolderUpdateDTO folderUpdateDTO) {
        folderService.updateFolder(folderUpdateDTO);
        return R.ok("修改成功");
    }
    //查询文件夹
}
