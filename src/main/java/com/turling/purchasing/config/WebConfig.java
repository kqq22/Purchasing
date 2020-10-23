package com.turling.purchasing.config;
import com.turling.purchasing.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan("com.turling.purchasing.dao")
public class WebConfig implements WebMvcConfigurer {
    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/*")
        .excludePathPatterns("/","/login","bootstrap/**","js/**");
    }

    //配置视图
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/Order_newform").setViewName("planman/Order_newform");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/test").setViewName("planman/test");
        registry.addViewController("/pclass_select").setViewName("planman/pclass_select");
        registry.addViewController("/leftRequire").setViewName("leftRequire");
    }
}
