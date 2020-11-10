package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Quote;

import java.util.List;

/**
 * 报价书业务逻辑接口类
 */
public interface QuoteService {
    /**
     * 查询最后添加的id
     * @return
     */
    public Long getLastId();

    /**
     * 添加报价书
     * @param quote
     * @return
     */
    public int addQuote(Quote quote);

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findQuoteAll(Integer curPage,Integer pageSize);

    /**
     * 根据id查询Quote
     * @param id
     * @return
     */
    public Quote findQuoteById(long id);

    /**
     * 根据id修改报价明细
     * @param quote
     * @return
     */
    public int updateQuoteById(Quote quote);

    /**
     * 查询报价，状态为已揭示
     * @return
     */
    public EasyUIDataGrid findQuoteToContract(Integer curPage,Integer pageSize);

    /**
     * 根据id查询报价书及相关信息
     * @param id
     * @return
     */
    public Quote findQuoteByIdToContract(long id);
}
