package com.turling.purchasing.dao;

import com.turling.purchasing.entity.SuppMaterial;
import com.turling.purchasing.entity.SuppMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuppMaterialMapper {
    long countByExample(SuppMaterialExample example);

    int deleteByExample(SuppMaterialExample example);

    int insert(SuppMaterial record);

    int insertSelective(SuppMaterial record);

    List<SuppMaterial> selectByExample(SuppMaterialExample example);

    int updateByExampleSelective(@Param("record") SuppMaterial record, @Param("example") SuppMaterialExample example);

    int updateByExample(@Param("record") SuppMaterial record, @Param("example") SuppMaterialExample example);
}