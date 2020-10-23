package com.turling.purchasing.dao;

import com.turling.purchasing.entity.Material;
import com.turling.purchasing.entity.MaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MaterialMapper {
    @Select("select * from material limit #{curPage},#{pageSize}")
    public List<Material> findAll(Integer curPage,Integer pageSize);

    @Select("select count(*) from material")
    public Integer getCount();

    long countByExample(MaterialExample example);

    int deleteByExample(MaterialExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Material record);

    int insertSelective(Material record);

    List<Material> selectByExample(MaterialExample example);

    Material selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByExample(@Param("record") Material record, @Param("example") MaterialExample example);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}