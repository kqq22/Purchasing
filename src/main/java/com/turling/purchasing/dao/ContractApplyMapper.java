package com.turling.purchasing.dao;

import com.turling.purchasing.entity.ContractApply;
import com.turling.purchasing.entity.ContractApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ContractApplyMapper {
    @Select("select max(id) from contract_apply")
    public Long getLastId();

    ContractApply selectStock(long id);

    List<ContractApply> selectContract(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int getCount();

    List<ContractApply> selectContractByStatus(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int getCountByStatus();

    List<ContractApply> selectContractByContractStatus(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int getCountByContractStatus();

    ContractApply selectContractById(long id);

    List<ContractApply> selectContractByContractStatus2(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int getCountByContractStatus2();

    List<ContractApply> selectContractByContractStatus3(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int getCountByContractStatus3();

    List<ContractApply> selectContractByContractStatus4(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    int getCountByContractStatus4();

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