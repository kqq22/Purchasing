package com.turling.purchasing.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;

import java.util.HashMap;

@Controller
public class ShiroConfig {
    //1.配置一个 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiro底层拦截通过过滤器进行拦截
        HashMap<String,String > map = new HashMap<>();
        //map.put("/*","authc");
        map.put("/login","anon");
        map.put("/loginShiro","anon");
        map.put("/addUser","anon");
        map.put("/addUsers","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //设置拦截后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }

    //2.配置一个安全管理器进行管理 SecurityManager
    @Bean
    public SecurityManager securityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3.配置Realm
    @Bean
    public Realm realm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyReal myReal = new MyReal();
        myReal.setCredentialsMatcher(hashedCredentialsMatcher);
        return myReal;
    }

    //需要配置一个加密器
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }
}
