package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.SupplierMapper;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Supplier;
import com.turling.purchasing.entity.SupplierExample;
import com.turling.purchasing.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * 供应商基本信息业务逻辑实现类
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    @Override
    public int addSupplier(Supplier supplier) {
        return supplierMapper.insertSelective(supplier);
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findSupplier(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(supplierMapper.findSupplier((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(supplierMapper.getCount());
        return easyUIDataGrid;
    }

    /**
     * 获取最后添加的id
     * @return
     */
    @Override
    public Long getLastId() {
        return supplierMapper.getLastId();
    }

    /**
     * 根据用户id查询供应商
     * @param id
     * @return
     */
    @Override
    public Supplier findSupplierByUsersId(long id) {
        SupplierExample supplierExample = new SupplierExample();
        supplierExample.createCriteria().andUserIdEqualTo(id);
        return supplierMapper.selectByExample(supplierExample).get(0);
    }
}
