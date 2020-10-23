package com.turling.purchasing.service;

import com.turling.purchasing.entity.SysMenus;

import java.util.List;

/**
 * 菜单信息表业务逻辑接口类
 */
public interface SysMenusService {
    /**
     * 查询所有菜单信息
     * @return 返回菜单信息list
     */
    public List<SysMenus> findSysMenusAll(Integer id);

}
