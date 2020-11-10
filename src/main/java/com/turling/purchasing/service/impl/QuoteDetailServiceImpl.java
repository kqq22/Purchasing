package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.QuoteDetailMapper;
import com.turling.purchasing.entity.QuoteDetail;
import com.turling.purchasing.service.QuoteDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuoteDetailServiceImpl implements QuoteDetailService {
    @Resource
    private QuoteDetailMapper quoteDetailMapper;

    /**
     * 添加报价书明细
     * @param quoteDetail
     * @return
     */
    @Override
    public int addQuoteDetail(QuoteDetail quoteDetail) {
        return quoteDetailMapper.insertSelective(quoteDetail);
    }
}
