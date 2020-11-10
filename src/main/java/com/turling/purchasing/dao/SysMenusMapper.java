package com.turling.purchasing.dao;

import com.turling.purchasing.entity.SysMenus;
import com.turling.purchasing.entity.SysMenusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysMenusMapper {

    @Select("<script>"
            + "SELECT * FROM sys_menus WHERE id IN "
            + "<foreach item='item' index='index' collection='list'      open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    public List<SysMenus> findAll(@Param("list") List<String> list);


    long countByExample(SysMenusExample example);

    int deleteByExample(SysMenusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    List<SysMenus> selectByExample(SysMenusExample example);

    SysMenus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    int updateByExample(@Param("record") SysMenus record, @Param("example") SysMenusExample example);

    int updateByPrimaryKeySelective(SysMenus record);

    int updateByPrimaryKey(SysMenus record);
}