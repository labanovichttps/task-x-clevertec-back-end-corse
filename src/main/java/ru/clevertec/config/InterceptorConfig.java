package ru.clevertec.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.clevertec.interceptor.EntityInterceptor;
import ru.clevertec.interceptor.OrderInterceptor;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final EntityInterceptor entityInterceptor;
    private final OrderInterceptor orderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(entityInterceptor)
                .addPathPatterns("/api/tags")
                .addPathPatterns("/api/certificates")
                .addPathPatterns("/api/users");
        registry.addInterceptor(orderInterceptor)
                .addPathPatterns("/api/orders/**");
    }
}
