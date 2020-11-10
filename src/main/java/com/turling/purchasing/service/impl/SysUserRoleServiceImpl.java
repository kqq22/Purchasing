package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.SysUserRoleMapper;
import com.turling.purchasing.entity.SysUserRole;
import com.turling.purchasing.entity.SysUserRoleExample;
import com.turling.purchasing.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据用户id查询角色id
     * @param id
     * @return
     */
    @Override
    public SysUserRole findSysUserRole(long id) {
        SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
        sysUserRoleExample.createCriteria().andUserIdEqualTo(id);
        return sysUserRoleMapper.selectByExample(sysUserRoleExample).get(0);
    }
}
