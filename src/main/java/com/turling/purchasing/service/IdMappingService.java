package com.turling.purchasing.service;

import com.turling.purchasing.entity.IdMapping;

import java.util.List;

/**
 * 编号对照业务逻辑接口类
 */
public interface IdMappingService {
    /**
     * 根据需求计划id添加状态
     * @param idMapping
     * @return
     */
    public int addIdMapping(IdMapping idMapping);

    /**
     * 根据需求计划id修改状态
     * @param idMapping
     * @return
     */
    public int updateIdMapping(IdMapping idMapping);


    /**
     * 根据采购计划id修改状态
     * @return
     */
    public int updateIdMappingByStock(IdMapping idMapping);

    /**
     * 根据需求计划id删除
     * @param id
     * @return
     */
    public int delIdMappingByOrderId(long id);

    /**
     * 根据采购计划id查询需求计划id
     * @param id
     * @return
     */
    public List<IdMapping> findIdMappingByStockId(long id);

    /**
     * 根据询价书id添加报价书状态
     * @param idMapping
     * @return
     */
    public int updateMappingStatusByEnquireId(IdMapping idMapping);

    /**
     * 根据报价书id添加合同状态
     * @param idMapping
     * @return
     */
    public int updateMappingStatusByQuoteId(IdMapping idMapping);

    /**
     * 根据
     * @param idMapping
     * @return
     */
    public int updateContractStatus(IdMapping idMapping);
}
