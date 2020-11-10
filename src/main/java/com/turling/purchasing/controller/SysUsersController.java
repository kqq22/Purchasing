package com.turling.purchasing.controller;

import com.turling.purchasing.entity.SysUsers;
import com.turling.purchasing.service.SysUsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SysUsersController {
    @Autowired
    private SysUsersService sysUsersService;

    /**
     * 登录
     * @param sysUsers
     * @param model
     * @return
     */
    @RequestMapping("/loginShiro")
    public String loginShiro(SysUsers sysUsers, Model model, HttpSession session){
        //用shiro提供的验证方式进行登录
        //1.获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //创建一个token对象封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(sysUsers.getLoginId(),sysUsers.getPassword());
        //执行验证
        try {
            subject.login(token);
            //登录成功
            SysUsers sysUsers1 = new SysUsers();
            sysUsers1.setLoginId(sysUsers.getLoginId());
            SysUsers users = sysUsersService.findUsers(sysUsers1);
            session.setAttribute("users",users);

            return "/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "/login";
        }
    }

    /**
     * 添加用户
     * @param sysUsers
     * @return
     */
    @RequestMapping("/addUsers")
    public String addUser(SysUsers sysUsers){
        int row = sysUsersService.addUser(sysUsers);
        //System.out.println(row);
        return "/login";
    }
}
