package com.zenith.xxx.api.base;

import com.sjr.common.entity.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author TMW
 * @since 2022/4/26 9:28
 */
public interface FileService {
    /**
     * 文件上传
     *
     * @param file   文件
     * @param unique 是否唯一
     * @return 返回文件信息
     */
    Result upload(MultipartFile file, boolean unique);

    /**
     * 创建文件路径
     *
     * @param fileName 文件名称
     * @param isCreate 是否生成文件
     * @return 文件路径
     * @throws Exception
     */
    String createFilePath(String fileName, boolean isCreate) throws Exception;

    /**
     * 获取 excel 模版文件路径
     *
     * @return
     * @throws Exception
     */
    String tempExcelPre() throws Exception;

    /**
     * 获取 word 模版文件路径
     *
     * @return
     * @throws Exception
     */
    String tempWordPre() throws Exception;
}
