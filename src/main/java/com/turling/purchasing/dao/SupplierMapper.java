package com.turling.purchasing.dao;

import com.turling.purchasing.entity.Orders;
import com.turling.purchasing.entity.Supplier;
import com.turling.purchasing.entity.SupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SupplierMapper {
    @Select("select * from supplier limit #{curPage},#{pageSize}")
    public List<Supplier> findSupplier(Integer curPage, Integer pageSize);

    @Select("select count(*) from supplier")
    public Integer getCount();

    @Select("select max(id) from supplier")
    public Long getLastId();

    long countByExample(SupplierExample example);

    int deleteByExample(SupplierExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierExample example);

    Supplier selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}