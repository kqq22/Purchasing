package com.turling.purchasing.service;

import com.turling.purchasing.entity.SysMenuRole;

import java.util.List;

public interface SysMenuRoleService {
    /**
     * 根据角色id查询菜单id
     * @param id
     * @return
     */
    public List<SysMenuRole> findSysMenuRole(long id);
}
