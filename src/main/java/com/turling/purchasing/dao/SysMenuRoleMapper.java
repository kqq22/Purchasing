package com.turling.purchasing.dao;

import com.turling.purchasing.entity.SysMenuRole;
import com.turling.purchasing.entity.SysMenuRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuRoleMapper {
    long countByExample(SysMenuRoleExample example);

    int deleteByExample(SysMenuRoleExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    List<SysMenuRole> selectByExample(SysMenuRoleExample example);

    SysMenuRole selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByExample(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByPrimaryKeySelective(SysMenuRole record);

    int updateByPrimaryKey(SysMenuRole record);
}