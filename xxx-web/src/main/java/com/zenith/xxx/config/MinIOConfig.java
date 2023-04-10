// package com.zenith.xxx.config;
//
// import com.zenith.xxx.properties.MinioProperties;
// import io.minio.MinioClient;
// import lombok.Data;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.context.properties.EnableConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * @author TMW
//  * @since 2023/4/3 16:38
//  */
// @Configuration
// @EnableConfigurationProperties(MinioProperties.class)
// public class MinIOConfig {
//
//     @Autowired
//     private MinioProperties minioProperties;
//
//     @Bean
//     public MinioClient minioClient(){
//         return MinioClient.builder()
//                 .endpoint(minioProperties.getEndpoint())
//                 .credentials(minioProperties.getAccessKey(),minioProperties.getSecretKey())
//                 .build();
//     }
// }
