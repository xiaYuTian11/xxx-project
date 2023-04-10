// package com.zenith.xxx.controller.base;
//
// import com.efficient.common.result.Result;
// import com.efficient.common.util.JackSonUtil;
// import com.zenith.xxx.util.MinioUtil;
// import io.minio.messages.Bucket;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
//
// import javax.servlet.http.HttpServletResponse;
// import java.io.File;
// import java.io.InputStream;
// import java.util.Collections;
// import java.util.List;
// import java.util.Objects;
// import java.util.Set;
// import java.util.stream.Collectors;
//
// /**
//  * https://juejin.cn/post/6992487558865223710
//  *
//  * @author TMW
//  * @since 2023/4/3 16:34
//  */
// @RestController
// @RequestMapping("/minio")
// @Slf4j
// @Validated
// public class MinioController {
//
//     @Autowired
//     private MinioUtil minioUtil;
//     private String bucketName = "tmw";
//
//     @GetMapping("/createBucket")
//     public Result createBucket() throws Exception {
//         minioUtil.createBucket(bucketName);
//         return Result.ok();
//     }
//
//     /**
//      * 上传文件
//      */
//     @PostMapping("/upload")
//     public Result upload(@RequestParam("file") MultipartFile file) throws Exception {
//         String url = minioUtil.upload(file, bucketName);
//         return Result.ok(url);
//     }
//
//     @GetMapping("/uploadFile")
//     public Result upload() throws Exception {
//         File file = new File("C:\\Users\\tmw\\Desktop\\2023.03.01各方面总名册 (模板) .docx");
//         String url = minioUtil.upload(file, bucketName);
//         return Result.ok(url);
//     }
//
//     /**
//      * 获取全部bucket
//      *
//      * @return
//      */
//     @GetMapping("/getAllBuckets")
//     public Result getAllBuckets() throws Exception {
//         List<Bucket> allBuckets = minioUtil.getAllBuckets();
//         List<String> bucketList = allBuckets.stream().map(Bucket::name).collect(Collectors.toList());
//         // return Result.ok(bucketList);
//         return Result.ok(bucketList);
//     }
//
//     /**
//      * 根据bucketName获取信息
//      *
//      * @param bucketName bucket名称
//      */
//     @GetMapping("/getBucket")
//     public Result getBucket(String bucketName) throws Exception {
//         Bucket bucket = minioUtil.getBucket(bucketName);
//         return Result.ok(Objects.isNull(bucket) ? null : bucket.name());
//     }
//
//     /**
//      * 根据bucketName删除信息
//      *
//      * @param bucketName bucket名称
//      */
//     @GetMapping("/removeBucket")
//     public Result removeBucket(String bucketName) throws Exception {
//         minioUtil.removeBucket(bucketName);
//         return Result.ok();
//     }
//
//     /**
//      * 获取⽂件外链
//      *
//      * @param bucketName bucket名称
//      * @param objectName ⽂件名称
//      * @param expires    过期时间 <=7
//      * @return url
//      */
//     @GetMapping("/getObjectUrl")
//     public Result getObjectUrl(String bucketName, String objectName, Integer expires) throws Exception {
//         return Result.ok(minioUtil.getObjectUrl(bucketName, objectName, expires));
//     }
//
//     /**
//      * 获取⽂件
//      *
//      * @param bucketName bucket名称
//      * @param objectName ⽂件名称
//      * @return ⼆进制流
//      */
//     @GetMapping("/getObject")
//     public Result getObject(String bucketName, String objectName, HttpServletResponse response) throws Exception {
//         minioUtil.getObject(bucketName, objectName, response);
//         return Result.ok();
//     }
//
//     @GetMapping("/download")
//     public ResponseEntity<byte[]> download(String bucketName, String objectName) throws Exception {
//         return minioUtil.download(bucketName, objectName);
//     }
//
//     /**
//      * 获取⽂件信息
//      *
//      * @param bucketName bucket名称
//      * @param objectName ⽂件名称
//      * @throws Exception https://docs.minio.io/cn/java-minioClient-api-reference.html#statObject
//      */
//     @GetMapping("/getObjectInfo")
//     public Result getObjectInfo(String bucketName, String objectName) throws Exception {
//         return Result.ok(minioUtil.getObjectInfo(bucketName, objectName));
//     }
//
//     /**
//      * 删除⽂件
//      *
//      * @param bucketName bucket名称
//      * @param objectName ⽂件名称
//      * @throws Exception https://docs.minio.io/cn/java-minioClient-apireference.html#removeObject
//      */
//     @GetMapping("/removeObject")
//     public Result removeObject(String bucketName, String objectName) throws Exception {
//         minioUtil.removeObject(bucketName, objectName);
//         return Result.ok();
//     }
//
//     /**
//      * 查看文件对象
//      *
//      * @return 存储bucket内文件对象信息
//      */
//     @GetMapping("/listObjects")
//     public Result listObjects(String bucketName) {
//         return Result.ok(minioUtil.listObjects(bucketName));
//     }
//
// }
