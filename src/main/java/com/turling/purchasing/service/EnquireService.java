package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Enquire;
import com.turling.purchasing.entity.EnquireDetail;

/**
 * 询价书业务逻辑接口类
 */
public interface EnquireService {

    /**
     * 查询最后添加的id
     * @return
     */
    public Long getLastId();

    /**
     * 添加询价书
     * @param enquire
     * @return
     */
    public int addEnquire(Enquire enquire);

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findEnquireAll(Integer curPage,Integer pageSize);

    /**
     * 根据id查询询价书信息
     * @param id
     * @return
     */
    public Enquire findEnquireById(long id);

    /**
     * 根据id修改询价书及询价书明细
     * @param enquire
     * @param enquireDetail
     * @return
     */
    public int updateEnquireById(Enquire enquire, EnquireDetail enquireDetail);

    /**
     * 根据enquire表id查询相关信息
     * @param id
     * @return
     */
    public Enquire findEnquireToQuote(long id);
}
