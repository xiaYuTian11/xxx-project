package com.zenith.xxx.config;

import com.efficient.auth.interceptor.PermissionInterceptor;
import com.efficient.idempotence.interceptor.IdempotenceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author TMW
 * @since 2022/3/1 14:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private PermissionInterceptor permissionInterceptor;
    @Autowired
    private IdempotenceInterceptor idempotenceInterceptor;
    /**
     * 默认拦截器排除资源
     */
    private final List<String> excludePaths = Arrays.asList("/login", "classpath:/static/",
            "classpath:/public/", "/upload/**", "/download/**", "/swagger**", "/webjars/springfox-swagger-ui/**");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(permissionInterceptor).addPathPatterns("/**")
        //         .excludePathPatterns(excludePaths);
        registry.addInterceptor(idempotenceInterceptor).addPathPatterns("/**")
                .excludePathPatterns(excludePaths);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/static/upload/");
        registry.addResourceHandler("/download/**")
                .addResourceLocations("classpath:/static/download/");
        // registry.addResourceHandler("/**")
        //         .addResourceLocations("classpath:/public/");
        // 映射webjars 资源
        // registry.addResourceHandler("swagger-ui.html")
        //         .addResourceLocations("classpath:/META-INF/resources/");
        // registry.addResourceHandler("/webjars/**")
        //         .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept",
                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .maxAge(3600);
    }

}
