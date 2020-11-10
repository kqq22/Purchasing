package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.IdMapping;
import com.turling.purchasing.entity.Stock;
import com.turling.purchasing.entity.Supplier;

import java.util.List;

/**
 * 采购计划业务逻辑接口类
 */
public interface StockService {
    /**
     * 获取最后添加的id
     * @return
     */
    public Long getLastId();

    /**
     * 查询满足条件的供应商
     * @param code
     * @return
     */
    public List<Supplier> findSupplier(String code);

    /**
     * 添加采购计划
     * @param stock
     * @return
     */
    public int addStock(Stock stock);

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findStockAll(Integer curPage,Integer pageSize);

    /**
     * 获取最后添加的id
     * @return
     */
    public Long selectLastId();

    /**
     * 根据id查询采购计划
     * @param id
     * @return
     */
    public Stock findStockById(long id);

    /**
     * 根据状态分页查询采购计划（C001-40）待审批
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findStockByStatus(Integer curPage,Integer pageSize);

    /**
     * 根据状态分页查询采购计划（C001-50）（未下达）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findStockByStatus3(Integer curPage,Integer pageSize);

    /**
     * 根据状态分页查询采购计划（C001-60）（未编制询价书）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findStockByStatus2(Integer curPage,Integer pageSize);

    /**
     * 根据采购计划id修改状态
     * @param idMapping
     * @return
     */
    public int updateStockStatus(IdMapping idMapping);

    /**
     * 根据采购计划id修改采购计划信息
     * @param stock
     * @return
     */
    public int updateStockById(Stock stock);
}
