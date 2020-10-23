package com.turling.purchasing.dao;

import com.turling.purchasing.entity.EnquireDetail;
import com.turling.purchasing.entity.EnquireDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnquireDetailMapper {
    long countByExample(EnquireDetailExample example);

    int deleteByExample(EnquireDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EnquireDetail record);

    int insertSelective(EnquireDetail record);

    List<EnquireDetail> selectByExample(EnquireDetailExample example);

    EnquireDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EnquireDetail record, @Param("example") EnquireDetailExample example);

    int updateByExample(@Param("record") EnquireDetail record, @Param("example") EnquireDetailExample example);

    int updateByPrimaryKeySelective(EnquireDetail record);

    int updateByPrimaryKey(EnquireDetail record);
}