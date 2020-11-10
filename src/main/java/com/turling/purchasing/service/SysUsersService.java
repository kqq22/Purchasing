package com.turling.purchasing.service;

import com.turling.purchasing.entity.SysUsers;

import java.util.List;

public interface SysUsersService {
    public int addUser(SysUsers sysUsers);

    /**
     * 查询登录用户
     * @param sysUsers
     * @return
     */
    public SysUsers findUsers(SysUsers sysUsers);
}
