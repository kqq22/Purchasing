package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.IdMappingMapper;
import com.turling.purchasing.entity.IdMapping;
import com.turling.purchasing.entity.IdMappingExample;
import com.turling.purchasing.service.IdMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 编号对照业务逻辑实现类
 */
@Service
public class IdMappingServiceImpl implements IdMappingService {
    @Resource
    private IdMappingMapper idMappingMapper;

    /**
     * 根据需求计划id添加状态
     * @param idMapping
     * @return
     */
    @Override
    public int addIdMapping(IdMapping idMapping) {
        return idMappingMapper.insertSelective(idMapping);
    }

    /**
     * 根据id修改需求计划
     * @param idMapping
     * @return
     */
    @Override
    public int updateIdMapping(IdMapping idMapping) {
        //System.out.println(idMapping.getOrderId()+"---"+idMapping.getStockId()+"--"+idMapping.getStatus());
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andOrderIdEqualTo(idMapping.getOrderId());
        return idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
    }

    /**
     * 根据采购计划id修改状态
     * @return
     */
    @Override
    public int updateIdMappingByStock(IdMapping idMapping) {
        //System.out.println(idMapping.getStockId()+"--"+idMapping.getStatus());
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andStockIdEqualTo(idMapping.getStockId());
        return idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
    }

    /**
     * 根据需求计划id删除
     * @param id
     * @return
     */
    @Override
    public int delIdMappingByOrderId(long id) {
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andOrderIdEqualTo(id);
        return idMappingMapper.deleteByExample(idMappingExample);
    }

    /**
     * 根据采购计划id查询需求计划id
     * @param id
     * @return
     */
    @Override
    public List<IdMapping> findIdMappingByStockId(long id ) {
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andStockIdEqualTo(id);
        return idMappingMapper.selectByExample(idMappingExample);
    }

    /**
     * 根据询价书id添加报价书状态
     * @param idMapping
     * @return
     */
    @Override
    public int updateMappingStatusByEnquireId(IdMapping idMapping) {
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andEnquireIdEqualTo(idMapping.getEnquireId());
        return idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
    }

    /**
     * 根据报价书id添加合同状态
     * @param idMapping
     * @return
     */
    @Override
    public int updateMappingStatusByQuoteId(IdMapping idMapping) {
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andQuoteIdEqualTo(idMapping.getQuoteId());
        return idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
    }

    /**
     * 根据合同申请id修改状态
     * @param idMapping
     * @return
     */
    @Override
    public int updateContractStatus(IdMapping idMapping) {
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andContAppIdEqualTo(idMapping.getContAppId());
        return idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
    }
}
