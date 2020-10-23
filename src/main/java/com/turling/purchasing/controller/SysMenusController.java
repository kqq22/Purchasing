package com.turling.purchasing.controller;

import com.turling.purchasing.entity.SysMenus;
import com.turling.purchasing.service.SysMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysMenusController {
    @Autowired
    private SysMenusService sysMenusService;

    /**
     * 查询所有菜单信息
     * @return
     */
    @GetMapping("/showSysMenus")
    @ResponseBody
    public List<SysMenus> showSysMenus(@RequestParam(defaultValue = "1") Integer id){
        return sysMenusService.findSysMenusAll(1);
    }
}
