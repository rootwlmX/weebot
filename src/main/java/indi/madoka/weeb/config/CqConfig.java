package indi.madoka.weeb.config;

import indi.madoka.weeb.interceptor.CqHttpInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class CqConfig implements WebMvcConfigurer {
    private final CqHttpInterceptor cqHttpInterceptor;
    @Autowired
    public CqConfig(CqHttpInterceptor cqHttpInterceptor) {
        this.cqHttpInterceptor = cqHttpInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cqHttpInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error");
    }
}
