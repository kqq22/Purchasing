package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
/**
 * 需求计划表业务逻辑接口类
 */
public interface OrdersService {
    /**
     * 分页查询
     * @param curPage 页码
     * @param pageSize 大小
     * @return
     */
    public EasyUIDataGrid findOrdersAll(Integer curPage, Integer pageSize);
}
