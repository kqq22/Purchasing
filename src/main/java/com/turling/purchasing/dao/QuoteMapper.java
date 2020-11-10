package com.turling.purchasing.dao;

import com.turling.purchasing.entity.Quote;
import com.turling.purchasing.entity.QuoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuoteMapper {

    @Select("select max(id) from quote")
    public Long getLastId();

    List<Quote> selectQuoteAll(@Param("curPage")Integer curPage,@Param("pageSize")Integer pageSize);

    int getCount();

    Quote selectQuoteById(long id);

    List<Quote> selectQuoteByStatus(@Param("curPage")Integer curPage,@Param("pageSize")Integer pageSize);

    int getCountByStatus();

    Quote selectQuoteByIdToContract(long id);

    long countByExample(QuoteExample example);

    int deleteByExample(QuoteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Quote record);

    int insertSelective(Quote record);

    List<Quote> selectByExample(QuoteExample example);

    Quote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Quote record, @Param("example") QuoteExample example);

    int updateByExample(@Param("record") Quote record, @Param("example") QuoteExample example);

    int updateByPrimaryKeySelective(Quote record);

    int updateByPrimaryKey(Quote record);
}