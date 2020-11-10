package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.*;
import com.turling.purchasing.entity.*;
import com.turling.purchasing.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Resource
    private StockMapper stockMapper;

    @Resource
    private MaterialMapper materialMapper;

    @Resource
    private SuppMaterialMapper suppMaterialMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @Resource
    private IdMappingMapper idMappingMapper;

    /**
     * 获取最后添加的id
     * @return
     */
    @Override
    public Long getLastId() {
        return stockMapper.getLastId();
    }

    /**
     * 查询满足条件的供应商
     * @param code
     * @return
     */
    @Override
    public List<Supplier> findSupplier(String code) {
        List<Supplier> list = new ArrayList<>();
        //查询物资id
        long mid = materialMapper.getId(code);
        //System.out.println(mid);
        //查询供应商id
        long [] sid = suppMaterialMapper.getId(mid);
        if(sid==null){
          return null;
        }else{
            for(int i=0;i<sid.length;i++){
                //System.out.println(sid[i]);
                //根据供应商id查询满足条件的供应商
                Supplier supplier = supplierMapper.selectByPrimaryKey(sid[i]);
                list.add(supplier);
            }
        }
        return list;
    }

    /**
     * 添加采购计划
     * @param stock
     * @return
     */
    @Override
    public int addStock(Stock stock) {
        return stockMapper.insertSelective(stock);
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findStockAll(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(stockMapper.getCount());
        easyUIDataGrid.setRows(stockMapper.selectstockshow((curPage-1)*pageSize,pageSize));
        return easyUIDataGrid;
    }

    /**
     * 获取最后添加的id
     * @return
     */
    @Override
    public Long selectLastId() {
        return stockMapper.getLastId();
    }

    /**
     * 根据id查询采购计划
     * @param id
     * @return
     */
    @Override
    public Stock findStockById(long id) {
        return stockMapper.selectStockById(id);
    }

    /**
     * 根据状态分页查询采购计划（C001-40）待审批
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findStockByStatus(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(stockMapper.getCountByStatus());
        easyUIDataGrid.setRows(stockMapper.selectStockByStatus((curPage-1)*pageSize,pageSize));
        return easyUIDataGrid;
    }

    /**
     * 根据状态分页查询采购计划（C001-50）（未下达）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findStockByStatus3(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(stockMapper.getCountByStatus3());
        easyUIDataGrid.setRows(stockMapper.selectStockByStatus3((curPage-1)*pageSize,pageSize));
        return easyUIDataGrid;
    }

    /**
     * 根据状态分页查询采购计划（C001-60）（未编制询价书）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findStockByStatus2(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(stockMapper.getCountByStatus());
        easyUIDataGrid.setRows(stockMapper.selectStockByStatus2((curPage-1)*pageSize,pageSize));
        return easyUIDataGrid;
    }

    /**
     * 根据采购计划id修改状态
     * @return
     */
    @Override
    public int updateStockStatus(IdMapping idMapping) {
        IdMappingExample idMappingExample = new IdMappingExample();
        idMappingExample.createCriteria().andStockIdEqualTo(idMapping.getId());
        return idMappingMapper.updateByExampleSelective(idMapping,idMappingExample);
    }

    /**
     * 根据采购计划id修改采购计划信息
     * @param stock
     * @return
     */
    @Override
    public int updateStockById(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

}
