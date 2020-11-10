package com.turling.purchasing.controller;

import com.turling.purchasing.dao.SysUserRoleMapper;
import com.turling.purchasing.entity.SysMenuRole;
import com.turling.purchasing.entity.SysMenus;
import com.turling.purchasing.entity.SysUserRole;
import com.turling.purchasing.entity.SysUsers;
import com.turling.purchasing.service.SysMenuRoleService;
import com.turling.purchasing.service.SysMenusService;
import com.turling.purchasing.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SysMenusController {
    @Autowired
    private SysMenusService sysMenusService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysMenuRoleService sysMenuRoleService;
    /**
     * 查询所有菜单信息
     * @return
     */
    @GetMapping("/showSysMenus")
    @ResponseBody
    public List<SysMenus> showSysMenus(HttpServletRequest request){
        HttpSession session = request.getSession();
        SysUsers users = (SysUsers) session.getAttribute("users");
        SysUserRole sysUserRole =  sysUserRoleService.findSysUserRole(users.getId());
        List<SysMenuRole> list = sysMenuRoleService.findSysMenuRole(sysUserRole.getRoleId());
        return sysMenusService.findSysMenusAll(list);
    }
}
