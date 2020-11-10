package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.QuoteMapper;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Quote;
import com.turling.purchasing.entity.QuoteExample;
import com.turling.purchasing.service.QuoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报价书业务逻辑实现类
 */
@Service
public class QuoteServiceImpl implements QuoteService {
    @Resource
    private QuoteMapper quoteMapper;

    /**
     * 查询最后添加的id
     * @return
     */
    @Override
    public Long getLastId() {
        return quoteMapper.getLastId();
    }

    /**
     * 添加报价书
     * @param quote
     * @return
     */
    @Override
    public int addQuote(Quote quote) {
        return quoteMapper.insertSelective(quote);
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findQuoteAll(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(quoteMapper.getCount());
        easyUIDataGrid.setRows(quoteMapper.selectQuoteAll((curPage-1)*pageSize,pageSize));
        return easyUIDataGrid;
    }

    /**
     * 根据id查询Quote
     * @param id
     * @return
     */
    @Override
    public Quote findQuoteById(long id) {
        return quoteMapper.selectQuoteById(id);
    }

    /**
     * 根据id修改报价明细
     * @param quote
     * @return
     */
    @Override
    public int updateQuoteById(Quote quote) {
        return quoteMapper.updateByPrimaryKeySelective(quote);
    }

    /**
     * 查询报价，状态为已揭示
     * @return
     */
    @Override
    public EasyUIDataGrid findQuoteToContract(Integer curPage,Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(quoteMapper.selectQuoteByStatus((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(quoteMapper.getCountByStatus());
        return easyUIDataGrid;
    }

    /**
     * 根据id查询报价书及相关信息
     * @param id
     * @return
     */
    @Override
    public Quote findQuoteByIdToContract(long id) {
        return quoteMapper.selectQuoteByIdToContract(id);
    }
}
