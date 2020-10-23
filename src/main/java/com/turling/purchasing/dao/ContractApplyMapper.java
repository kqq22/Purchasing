package com.turling.purchasing.dao;

import com.turling.purchasing.entity.ContractApply;
import com.turling.purchasing.entity.ContractApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContractApplyMapper {
    long countByExample(ContractApplyExample example);

    int deleteByExample(ContractApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ContractApply record);

    int insertSelective(ContractApply record);

    List<ContractApply> selectByExample(ContractApplyExample example);

    ContractApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);

    int updateByExample(@Param("record") ContractApply record, @Param("example") ContractApplyExample example);

    int updateByPrimaryKeySelective(ContractApply record);

    int updateByPrimaryKey(ContractApply record);
}