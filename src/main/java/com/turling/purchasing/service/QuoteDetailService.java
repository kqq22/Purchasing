package com.turling.purchasing.service;

import com.turling.purchasing.entity.QuoteDetail;

/**
 * 报价书明细业务逻辑接口类
 */
public interface QuoteDetailService {
    /**
     * 添加报价书明细
     * @param quoteDetail
     * @return
     */
    public int addQuoteDetail(QuoteDetail quoteDetail);
}
