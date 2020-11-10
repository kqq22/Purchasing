package com.turling.purchasing.service;

import com.turling.purchasing.entity.EnquireDetail;

/**
 * 询价书明细业务逻辑接口类
 */
public interface EnquireDetailService {
    /**
     * 添加询价书明细
     * @param enquireDetail
     * @return
     */
    public int addEnquireDetail(EnquireDetail enquireDetail);


}
