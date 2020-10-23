package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.SysMenusMapper;
import com.turling.purchasing.entity.Attributes;
import com.turling.purchasing.entity.SysMenus;
import com.turling.purchasing.entity.SysMenusExample;
import com.turling.purchasing.service.SysMenusService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单信息表业务逻辑实现类
 */
@Service
public class SysMenusServiceImpl implements SysMenusService {
    @Resource
    private SysMenusMapper sysMenusMapper;

    /**
     * 查询所有菜单信息
     * @return 返回菜单信息list
     */
    @Override
    public List<SysMenus> findSysMenusAll(Integer id) {
        //根据id查询根节点
        SysMenusExample sysMenusExample = new SysMenusExample();
        sysMenusExample.createCriteria().andIdEqualTo(new Long(id));
        return getChildrenNode(sysMenusMapper.selectByExample(sysMenusExample));
    }

    /**
     * 查询子节点
     * @param list
     * @return
     */
    private List<SysMenus> getChildrenNode(List<SysMenus> list){
        //循环传入的集合
        for (SysMenus s:list){
            SysMenusExample sysMenusExample = new SysMenusExample();
            sysMenusExample.createCriteria().andParentIdEqualTo(new Long(s.getId()));
            List<SysMenus> lists = sysMenusMapper.selectByExample(sysMenusExample);
            //设置子节点集合
            s.setChildren(lists);
            Attributes attributes=new Attributes();
            attributes.setUrl(s.getLinkUrl());
            s.setAttributes(attributes);
            //递归
            getChildrenNode(lists);
        }
        return list;
    }

}
