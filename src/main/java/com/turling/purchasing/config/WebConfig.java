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
        registry.addViewController("/addUser").setViewName("addUser");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/leftRequire").setViewName("leftRequire");
        registry.addViewController("/bar").setViewName("bar");
        registry.addViewController("/mainRequire").setViewName("mainRequire");

        registry.addViewController("/pclass_select").setViewName("planman/pclass_select");
        registry.addViewController("/Order_newform").setViewName("planman/Order_newform");
        registry.addViewController("/Order_ytb_list").setViewName("planman/Order_ytb_list");
        registry.addViewController("/update_xuqiujihua").setViewName("planman/update_xuqiujihua");
        registry.addViewController("/print_order_detail").setViewName("planman/print_order_detail");
        registry.addViewController("/Order_wbxjfa_list").setViewName("planman/Order_wbxjfa_list");
        registry.addViewController("/bianzhicaigoujihua").setViewName("planman/bianzhicaigoujihua");
        registry.addViewController("/Project_list").setViewName("planman/Project_list");
        registry.addViewController("/xjfatz_xjfamx").setViewName("planman/xjfatz_xjfamx");
        registry.addViewController("/supply").setViewName("planman/supply");
        registry.addViewController("/provider_cx").setViewName("planman/provider_cx");
        registry.addViewController("/category").setViewName("planman/category");
        registry.addViewController("/Apply_daishencaiwu").setViewName("contractmanager/Apply_daishencaiwu");
        registry.addViewController("/Apply_daishenjihua").setViewName("contractmanager/Apply_daishenjihua");
        registry.addViewController("/Apply_list_do").setViewName("contractmanager/Apply_list_do");
        registry.addViewController("/Apply_caiwushenpi").setViewName("contractmanager/Apply_caiwushenpi");
        registry.addViewController("queryandqueto/Project_list").setViewName("queryandqueto/Project_list");
        registry.addViewController("/Project_list4").setViewName("planman/Project_list4");
        registry.addViewController("/Project_list3").setViewName("planman/Project_list3");
        registry.addViewController("/Enquire_bianzhi").setViewName("queryandqueto/Enquire_bianzhi");
        registry.addViewController("/ask1").setViewName("queryandqueto/ask1");
        registry.addViewController("/Enquire_update").setViewName("queryandqueto/Enquire_update");
        registry.addViewController("supplyman/Order_wbxjfa_list").setViewName("supplyman/Order_wbxjfa_list");
        registry.addViewController("supplyman/Project_list").setViewName("supplyman/Project_list");
        registry.addViewController("/addQuote").setViewName("supplyman/addQuote");
        registry.addViewController("/updateQuote").setViewName("supplyman/updateQuote");
        registry.addViewController("/bianzhihetong").setViewName("contractmanager/bianzhihetong");
        registry.addViewController("/jieshilist").setViewName("contractmanager/jieshilist");
        registry.addViewController("/bianzhilist").setViewName("contractmanager/bianzhilist");
        registry.addViewController("/Apply_list_do22").setViewName("contractmanager/Apply_list_do22");
        registry.addViewController("/Apply_queren").setViewName("contractmanager/Apply_queren");
        registry.addViewController("/Apply_querenList").setViewName("contractmanager/Apply_querenList");
        registry.addViewController("/Apply_daishencaiwuhetong").setViewName("contractmanager/Apply_daishencaiwuhetong");
        registry.addViewController("/Apply_caiwushenpihetong").setViewName("contractmanager/Apply_caiwushenpihetong");
        registry.addViewController("/Apply_jihuashenpi").setViewName("contractmanager/Apply_jihuashenpi");
        registry.addViewController("/Apply_daishenjihua").setViewName("contractmanager/Apply_daishenjihua");
        registry.addViewController("/Apply_daishenchangzhang").setViewName("contractmanager/Apply_daishenchangzhang");
        registry.addViewController("/Apply_changzhangshenpi").setViewName("contractmanager/Apply_changzhangshenpi");
        registry.addViewController("/keweihuhetong").setViewName("contractmanager/keweihuhetong");
        registry.addViewController("/xiugaihetong").setViewName("contractmanager/xiugaihetong");
        registry.addViewController("/jiffprov_look").setViewName("supplyman/jiffprov_look");
        registry.addViewController("/guidanghetong").setViewName("contractmanager/guidanghetong");
        registry.addViewController("/ProviderConsignment").setViewName("supplyman/ProviderConsignment");
        registry.addViewController("/Apply_kehuishou").setViewName("contractmanager/Apply_kehuishou");
        registry.addViewController("/contract_view").setViewName("contractmanager/contract_view");
    }

}
