package com.turling.purchasing.dao;

import com.turling.purchasing.entity.Enquire;
import com.turling.purchasing.entity.EnquireExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EnquireMapper {
    @Select("select max(id) from enquire")
    public Long getLastId();

    int getCount();

    List<Enquire> selectenquire(@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    Enquire selectEnquireById(long id);

    Enquire selectEnquireToQuote(long id);



    long countByExample(EnquireExample example);

    int deleteByExample(EnquireExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Enquire record);

    int insertSelective(Enquire record);

    List<Enquire> selectByExample(EnquireExample example);

    Enquire selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByExample(@Param("record") Enquire record, @Param("example") EnquireExample example);

    int updateByPrimaryKeySelective(Enquire record);

    int updateByPrimaryKey(Enquire record);
}