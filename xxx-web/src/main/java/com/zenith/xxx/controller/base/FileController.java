package com.zenith.xxx.controller.base;

import cn.hutool.core.util.StrUtil;
import com.sjr.common.entity.Result;
import com.sjr.common.entity.ResultEnum;
import com.sjr.common.permission.Permission;
import com.zenith.xxx.api.base.FileService;
import com.zenith.xxx.config.LogAop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件操作相关
 *
 * @author TMW
 * @since 2022/4/26 9:27
 */
@RestController
@RequestMapping("/file")
@Validated
@Api(tags = "文件操作")
@Permission
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private LogAop aop;

    /**
     * 上传文件
     *
     * @param file   上传文件
     * @param unique true 保证唯一文件，false 文件名冲突会更改文件名称
     * @return 返回文件信息
     */
    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传", response = Result.class)
    public Result upload(@RequestParam("file") MultipartFile file,
                         @RequestParam(value = "unique", required = false) boolean unique) throws Exception {
        if (file.isEmpty() || StrUtil.isBlank(file.getOriginalFilename())) {
            return Result.build(ResultEnum.NOT_CHECK_FILE);
        }

        final String originalFilename = file.getOriginalFilename();
        // aop.saveLog(OptTypeEnum.IMPORT, file.getOriginalFilename());
        return fileService.upload(file, unique);
    }

}
