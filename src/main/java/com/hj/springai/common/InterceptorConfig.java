package com.hj.springai.common;

/**
 * @FileName InterceptorConfig
 * @Description
 * @Author fazhu
 * @date 2024-12-10
 **/


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptors())
                .addPathPatterns("/*")  // 其他接口token验证
                .excludePathPatterns("/register")  // 注册接口放行
                .excludePathPatterns("/login");  // 登录接口放行
    }
}
