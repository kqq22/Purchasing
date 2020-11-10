package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.IdMapping;
import com.turling.purchasing.entity.Orders;

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
    public EasyUIDataGrid findOrdersAll(Integer curPage, Integer pageSize, Orders orders);

    /**
     * 根据id修改（添加）
     * @param orders
     * @return
     */
    public int addOrders(Orders orders);

    /**
     * 根据id修改（添加）
     * @param orders
     * @return
     */
    public int updateOrders(Orders orders);

    /**
     * 获取最后添加的id
     * @return
     */
    public Long selectLastId();

    /**
     * 根据id查询需求计划
     * @param id
     * @return
     */
    public Orders selectOrdersById(long id);

    /**
     * 根据id修改需求计划
     * @param orders
     * @return
     */
    public int updateOrdersById(Orders orders);

    /**
     * 根据id删除需求计划
     * @param id
     * @return
     */
    public int delOrdersById(long id);

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findOrdersByStatus(Integer curPage, Integer pageSize);
}
