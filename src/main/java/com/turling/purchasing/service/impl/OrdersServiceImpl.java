package com.turling.purchasing.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.turling.purchasing.dao.OrdersMapper;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.IdMapping;
import com.turling.purchasing.entity.Orders;
import com.turling.purchasing.entity.OrdersExample;
import com.turling.purchasing.service.OrdersService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public EasyUIDataGrid  findOrdersAll(Integer curPage, Integer pageSize, Orders orders) {
        //Page<Object> objects = PageHelper.startPage(curPage,pageSize);
        OrdersExample ordersExample=null;
        if(orders!=null){
            ordersExample = new OrdersExample();
            OrdersExample.Criteria criteria = ordersExample.createCriteria();
            if(orders.getMaterialCode()!=null&&orders.getMaterialCode()!=""){
                criteria.andMaterialCodeLike("%"+orders.getMaterialCode().trim()+"%");
            }else {
                ordersExample=null;
            }
            if(orders.getMaterialName()!=null&&orders.getMaterialName()!=""){
                criteria.andMaterialNameLike("%"+orders.getMaterialName().trim()+"%");
            }else{
                ordersExample=null;
            }
        }
        //根据条件查询
        List<Orders> ordersList ;
        if(ordersExample!=null){
            ordersList = ordersMapper.selectByExample(ordersExample);
        }else{
            ordersList= ordersMapper.selectByExample(null);
        }
        //List<Orders> ordersList = ordersMapper.selectordersshow(curPage,pageSize, orders.getMaterialName(),orders.getMaterialCode(),idMapping.getStatus());
        List ids = new ArrayList<>();
        for(int i=0;i<ordersList.size();i++){
            ids.add(ordersList.get(i).getId());
        }
        //放入easyui中
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(ordersList.size());
        easyUIDataGrid.setRows( ordersMapper.findOrdersById(curPage,pageSize,ids));
        return easyUIDataGrid;
    }

    /**
     * 根据需求计划id添加
     * @param orders
     * @return
     */
    @Override
    public int addOrders(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }

    /**
     * 根据需求计划id修改
     * @param orders
     * @return
     */
    @Override
    public int updateOrders(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    /**
     * 获取最后添加的id
     * @return
     */
    @Override
    public Long selectLastId() {
        return ordersMapper.getLastId();
    }

    /**
     * 根据id查询需求计划
     * @param id
     * @return
     */
    @Override
    public Orders selectOrdersById(long id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id修改需求计划
     * @param orders
     * @return
     */
    @Override
    public int updateOrdersById(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    /**
     * 根据id删除需求计划
     * @param id
     * @return
     */
    @Override
    public int delOrdersById(long id) {
        return ordersMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findOrdersByStatus(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(ordersMapper.getCountByStatus());
        easyUIDataGrid.setRows(ordersMapper.selectOrdersByStatus(curPage,pageSize));
        return easyUIDataGrid;
    }
}
