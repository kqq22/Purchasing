package com.turling.purchasing.dao;

import com.turling.purchasing.entity.Orders;
import com.turling.purchasing.entity.Stock;
import com.turling.purchasing.entity.StockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StockMapper {
    @Select("select max(id) from stock")
    Long getLastId();

    @Select("select * from stock limit #{curPage},#{pageSize}")
    public List<Orders> findStockAll(Integer curPage, Integer pageSize);

    @Select("select count(*) from stock")
    public int getCount();

    public int getCountByStatus();

    public List<Stock> selectStockByStatus(@Param("curPage")Integer curPage, @Param("pageSize") Integer pageSize);

    List<Stock> selectstockshow(Integer curPage,Integer pageSize);

    List<Stock> selectStockByStatus2(@Param("curPage")Integer curPage, @Param("pageSize") Integer pageSize);

    List<Stock> selectStockByStatus3(@Param("curPage")Integer curPage, @Param("pageSize") Integer pageSize);

    int getCountByStatus3();

    Stock selectStockById(long id);

    long countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}