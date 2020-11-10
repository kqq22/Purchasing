package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.ContractApplyMapper;
import com.turling.purchasing.entity.ContractApply;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.service.ContractApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 合同申请业务逻辑实现类
 */
@Service
public class ContractApplyServiceImpl implements ContractApplyService {
    @Resource
    private ContractApplyMapper contractApplyMapper;

    /**
     * 获取最后添加的id
     * @return
     */
    @Override
    public Long getLastId() {
        return contractApplyMapper.getLastId();
    }

    /**
     * 添加合同申请
     * @param contractApply
     * @return
     */
    @Override
    public int addContractApply(ContractApply contractApply) {
        return contractApplyMapper.insertSelective(contractApply);
    }

    /**
     * 根据quote的id查询stock
     * @param id
     * @return
     */
    @Override
    public ContractApply findStockById(long id) {
        return contractApplyMapper.selectStock(id);
    }

    /**
     * 查询合同申请
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findContractApply(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(contractApplyMapper.selectContract((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(contractApplyMapper.getCount());
        return easyUIDataGrid;
    }

    /**
     * 查询合同申请（未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findContractByStatus(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(contractApplyMapper.selectContractByStatus((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(contractApplyMapper.getCountByStatus());
        return easyUIDataGrid;
    }

    /**
     * 查询合同申请（未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findContractByContractStatus(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(contractApplyMapper.selectContractByContractStatus((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(contractApplyMapper.getCountByContractStatus());
        return easyUIDataGrid;
    }

    /**
     * 根据id查询合同申请相关信息
     * @param id
     * @return
     */
    @Override
    public ContractApply findContractById(long id) {
        return contractApplyMapper.selectContractById(id);
    }

    /**
     * 根据id修改合同申请信息（审批）
     * @param contractApply
     * @return
     */
    @Override
    public int updateContractById(ContractApply contractApply) {
        return contractApplyMapper.updateByPrimaryKeySelective(contractApply);
    }

    /**
     * 查询合同申请（财务部长未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findContractByContractStatus2(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(contractApplyMapper.selectContractByContractStatus2((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(contractApplyMapper.getCountByContractStatus2());
        return easyUIDataGrid;
    }

    /**
     * 查询合同申请（厂长未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findContractByContractStatus3(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(contractApplyMapper.selectContractByContractStatus3((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(contractApplyMapper.getCountByContractStatus3());
        return easyUIDataGrid;
    }

    /**
     * 查询合同申请（可维护合同）
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findContractByContractStatus4(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(contractApplyMapper.selectContractByContractStatus4((curPage-1)*pageSize,pageSize));
        easyUIDataGrid.setTotal(contractApplyMapper.getCountByContractStatus4());
        return easyUIDataGrid;
    }
}
