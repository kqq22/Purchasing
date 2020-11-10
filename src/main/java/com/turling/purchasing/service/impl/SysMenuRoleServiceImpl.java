package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.SysMenuRoleMapper;
import com.turling.purchasing.dao.SysMenusMapper;
import com.turling.purchasing.entity.SysMenuRole;
import com.turling.purchasing.entity.SysMenuRoleExample;
import com.turling.purchasing.entity.SysMenus;
import com.turling.purchasing.service.SysMenuRoleService;
import com.turling.purchasing.service.SysMenusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuRoleServiceImpl implements SysMenuRoleService {
    @Resource
    private SysMenuRoleMapper sysMenuRoleMapper;

    /**
     * 根据角色id查询菜单id
     * @param id
     * @return
     */
    @Override
    public List<SysMenuRole> findSysMenuRole(long id) {
        SysMenuRoleExample sysMenuRoleExample = new SysMenuRoleExample();
        sysMenuRoleExample.createCriteria().andRoleIdEqualTo(id);
        return sysMenuRoleMapper.selectByExample(sysMenuRoleExample);
    }
}
