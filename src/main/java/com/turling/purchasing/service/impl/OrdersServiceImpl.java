package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.OrdersMapper;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 需求计划表业务逻辑实现类
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    /**
     * 分页查询
     * @param curPage 页码
     * @param pageSize 大小
     * @return
     */
    @Override
    public EasyUIDataGrid  findOrdersAll(Integer curPage,Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(ordersMapper.getCount());
        easyUIDataGrid.setRows(ordersMapper.findOrdersAll(curPage,pageSize));
        return easyUIDataGrid;
    }
}
