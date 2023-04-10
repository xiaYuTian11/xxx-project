// package com.zenith.xxx.controller.base;
//
// import com.zenith.xxx.util.MinIOUtils;
// import io.swagger.annotations.ApiOperation;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
//
// import java.util.List;
//
// /**
//  * https://juejin.cn/post/6992487558865223710
//  *
//  * @author TMW
//  * @since 2023/4/3 16:34
//  */
// @RestController
// @RequestMapping("/minio2")
// @Slf4j
// @Validated
// public class Minio2Controller {
//     @Autowired
//     private MinIOUtils minioUtil;
//
//     @ApiOperation(value = "minio上传测试")
//     @PostMapping("/upload")
//     public List<String> upload(@RequestParam(name = "multipartFile") MultipartFile[] multipartFile) {
//         return minioUtil.upload(multipartFile);
//     }
//
//     @ApiOperation(value = "minio下载测试")
//     @GetMapping("/download")
//     public ResponseEntity<byte[]> download(@RequestParam String fileName) {
//         return minioUtil.download(fileName);
//     }
//
//     @ApiOperation(value = "minio创建桶")
//     @PostMapping("/existBucket")
//     public void existBucket(@RequestParam String bucketName) {
//         minioUtil.existBucket(bucketName);
//     }
//
// }
