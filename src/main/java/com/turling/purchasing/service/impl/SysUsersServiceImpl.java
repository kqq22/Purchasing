package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.SysUsersMapper;
import com.turling.purchasing.entity.SysUsers;
import com.turling.purchasing.entity.SysUsersExample;
import com.turling.purchasing.service.SysUsersService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUsersServiceImpl implements SysUsersService {
    @Resource
    private SysUsersMapper sysUsersMapper;

    /**
     * 添加用户
     * @param sysUsers
     * @return
     */
    @Override
    public int addUser(SysUsers sysUsers) {
        String newPwd = new Md5Hash(sysUsers.getPassword(),sysUsers.getLoginId(),3).toString();
        sysUsers.setPassword(newPwd);
        return sysUsersMapper.insertSelective(sysUsers);
    }

    /**
     * 查询登录用户
     * @param sysUsers
     * @return
     */
    @Override
    public SysUsers findUsers(SysUsers sysUsers) {
        SysUsersExample sysUsersExample = new SysUsersExample();
        sysUsersExample.createCriteria().andLoginIdEqualTo(sysUsers.getLoginId());
        return sysUsersMapper.selectByExample(sysUsersExample).get(0);
    }
}
