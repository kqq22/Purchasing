package com.turling.purchasing.dao;

import com.turling.purchasing.entity.Orders;
import com.turling.purchasing.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrdersMapper {
    @Select("select * from orders limit #{curPage},#{pageSize}")
    public List<Orders> findOrdersAll(Integer curPage,Integer pageSize);

    @Select("select count(*) from orders")
    public Integer getCount();

    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}