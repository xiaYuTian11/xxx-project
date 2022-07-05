package com.zenith.xxx.service.base;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.sjr.common.entity.FileVO;
import com.sjr.common.entity.Result;
import com.zenith.xxx.api.base.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static com.zenith.xxx.model.constant.FileConstant.*;

/**
 * 文件操作 服务类
 *
 * @author TMW
 * @since 2022/4/26 9:29
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public Result upload(MultipartFile multipartFile, boolean unique) {
        FileVO fileVo = new FileVO();
        try {
            // 获取文件的名称
            String originalFilename = multipartFile.getOriginalFilename();

            // 根路径，在 resources/static/upload
            String basePath = ResourceUtils.getURL(CLASSPATH).getPath() + STATIC;
            String fileUrl = UPLOAD_LINE;
            // 根据文件格式重新设置根路径
            if (originalFilename.lastIndexOf(POINT) != -1) {
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(POINT));
                fileUrl += this.getFileUrlFolder(suffix);
            }

            // 创建新的文件
            File fileExist = new File(basePath + fileUrl);
            // 文件夹不存在，则新建
            if (!fileExist.exists()) {
                fileExist.mkdirs();
            }

            // 重名文件重命名
            String fileName = originalFilename.replaceAll(" ", "");
            if (FileUtil.file(basePath + fileUrl, fileName).exists()) {
                if (unique) {
                    FileUtil.del(new File(basePath + fileUrl + File.separator + fileName));
                } else {
                    String name = fileName.substring(0, fileName.lastIndexOf(POINT)) + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
                    fileName = name + Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(POINT));
                }
            }
            // 获取文件对象
            File realFile = new File(basePath + fileUrl, fileName);
            // 完成文件的上传
            multipartFile.transferTo(realFile);
            fileVo.setFileName(originalFilename);
            fileVo.setFilePath(fileUrl + fileName);
        } catch (IOException e) {
            log.error("文件上传异常：", e);
        }
        return Result.ok(fileVo);
    }

    private String saveFilePath(String originalFilename, String fileUrl) throws Exception {
        // 获取文件的名称
        // 根路径，在 resources/static/upload
        String basePath = ResourceUtils.getURL(CLASSPATH).getPath() + STATIC;
        // 根据文件格式重新设置根路径
        if (originalFilename.lastIndexOf(POINT) != -1) {
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(POINT));
            fileUrl += this.getFileUrlFolder(suffix);
        }
        return basePath + fileUrl + originalFilename;
    }

    private String getFileUrlFolder(String suffix) {
        StringBuilder sb = new StringBuilder();
        // 上传图片格式文件
        if (StrUtil.equalsAnyIgnoreCase(suffix, ".xbm", ".tif", ".pjp", ".jfif", ".webp", ".pjpeg", ".avif", ".ico", ".tiff", ".bmp", ".png", ".jpeg", ".svgz", ".jpg", ".gif", ".svg")) {
            sb.append(FOLDER_PIC);
        } else {
            // 上传附件
            sb.append(FOLDER_FILE);
            if (StrUtil.equalsAnyIgnoreCase(suffix, ".doc", ".docx")) {
                sb.append(FOLDER_WORD);
            } else if (StrUtil.equalsAnyIgnoreCase(suffix, ".xls", ".xlsx")) {
                sb.append(FOLDER_EXCEL);
            } else if (StrUtil.equalsAnyIgnoreCase(suffix, ".pdf")) {
                sb.append(FOLDER_PDF);
            }
        }
        return sb.toString();
    }

    /**
     * 创建文件路径
     *
     * @param fileName 文件名称
     * @param isCreate 是否生成文件
     * @return 文件路径
     * @throws Exception
     */
    @Override
    public String createFilePath(String fileName, boolean isCreate) throws Exception {
        final String filePath = this.saveFilePath(fileName, DOWNLOAD_LINE);

        File file = new File(filePath);
        final File parentFile = file.getParentFile();
        if (!file.exists()) {
            parentFile.mkdirs();
        }
        if (isCreate) {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        return filePath;
    }

    @Override
    public String tempExcelPre() throws Exception {
        return ResourceUtils.getURL(CLASSPATH).getPath() + TEMP_EXCEL;
    }

    @Override
    public String tempWordPre() throws Exception {
        return ResourceUtils.getURL(CLASSPATH).getPath() + TEMP_WORD;
    }

}
