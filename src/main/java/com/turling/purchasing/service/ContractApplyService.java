package com.turling.purchasing.service;

import com.turling.purchasing.entity.ContractApply;
import com.turling.purchasing.entity.EasyUIDataGrid;

/**
 * 合同申请业务逻辑接口类
 */
public interface ContractApplyService {
    /**
     * 获取最后添加的id
     * @return
     */
    public Long getLastId();

    /**
     * 添加合同申请
     * @param contractApply
     * @return
     */
    public int addContractApply(ContractApply contractApply);

    /**
     * 根据quote的id查询stock
     * @param id
     * @return
     */
    public ContractApply findStockById(long id);

    /**
     * 查询合同申请
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findContractApply(Integer curPage,Integer pageSize);

    /**
     * 查询合同申请（未报批）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findContractByStatus(Integer curPage,Integer pageSize);

    /**
     * 查询合同申请（计划部长未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findContractByContractStatus(Integer curPage,Integer pageSize);

    /**
     * 根据id查询合同申请相关信息
     * @param id
     * @return
     */
    public ContractApply findContractById(long id);

    /**
     * 根据id修改合同申请信息（审批）
     * @param contractApply
     * @return
     */
    public int updateContractById(ContractApply contractApply);

    /**
     * 查询合同申请（财务部长未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findContractByContractStatus2(Integer curPage,Integer pageSize);

    /**
     * 查询合同申请（财务部长未审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findContractByContractStatus3(Integer curPage,Integer pageSize);

    /**
     * 查询合同申请（可维护合同）
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findContractByContractStatus4(Integer curPage,Integer pageSize);
}
