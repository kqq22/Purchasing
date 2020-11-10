package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.EnquireDetailMapper;
import com.turling.purchasing.entity.EnquireDetail;
import com.turling.purchasing.service.EnquireDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 询价书明细业务逻辑实现类
 */
@Service
public class EnquireDetailServiceImpl implements EnquireDetailService {
    @Resource
    private EnquireDetailMapper enquireDetailMapper;

    /**
     * 添加询价书明细
     * @param enquireDetail
     * @return
     */
    @Override
    public int addEnquireDetail(EnquireDetail enquireDetail) {
        return enquireDetailMapper.insertSelective(enquireDetail);
    }
}
