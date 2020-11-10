package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Supplier;

/**
 * 供应商基本信息业务逻辑接口类
 */
public interface SupplierService {
    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    public int addSupplier(Supplier supplier);

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findSupplier(Integer curPage,Integer pageSize);

    /**
     * 获取最后添加的id
     * @return
     */
    public Long getLastId();

    /**
     * 根据用户id查询供应商
     * @param id
     * @return
     */
    public Supplier findSupplierByUsersId(long id);
}
