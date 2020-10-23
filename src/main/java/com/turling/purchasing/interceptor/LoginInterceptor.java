package com.turling.purchasing.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 自定义拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    //在controller执行之前
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //如何判断用户登录了，通过session来判断
//        if(request.getSession().getAttribute("user")!=null){
//            //用户登录了 放行
//            return true;
//        }else{
//            response.sendRedirect("/");
//            return false;
//        }
//    }
}
