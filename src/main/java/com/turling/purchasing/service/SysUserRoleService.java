package com.turling.purchasing.service;

import com.turling.purchasing.entity.SysUserRole;

public interface SysUserRoleService {
    /**
     * 根据用户id查询角色id
     * @param id
     * @return
     */
    public SysUserRole findSysUserRole(long id);
}
