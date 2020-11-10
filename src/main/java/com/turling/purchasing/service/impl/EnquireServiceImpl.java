package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.EnquireDetailMapper;
import com.turling.purchasing.dao.EnquireMapper;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Enquire;
import com.turling.purchasing.entity.EnquireDetail;
import com.turling.purchasing.entity.EnquireDetailExample;
import com.turling.purchasing.service.EnquireService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 询价书业务逻辑实现类
 */
@Service
public class EnquireServiceImpl implements EnquireService {
    @Resource
    private EnquireMapper enquireMapper;

    @Resource
    private EnquireDetailMapper enquireDetailMapper;
    /**
     * 获取最后添加的id
     * @return
     */
    @Override
    public Long getLastId() {
        return enquireMapper.getLastId();
    }

    /**
     * 添加询价书
     * @param enquire
     * @return
     */
    @Override
    public int addEnquire(Enquire enquire) {
        return enquireMapper.insertSelective(enquire);
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findEnquireAll(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        List<Enquire> list = enquireMapper.selectenquire((curPage-1)*pageSize,pageSize);
        easyUIDataGrid.setTotal(enquireMapper.getCount());
        easyUIDataGrid.setRows(list);
        return easyUIDataGrid;
    }

    /**
     * 根据id查询询价书信息
     * @param id
     * @return
     */
    @Override
    public Enquire findEnquireById(long id) {
        return enquireMapper.selectEnquireById(id);
    }

    /**
     * 根据id修改询价书及询价书明细
     * @param enquire
     * @param enquireDetail
     * @return
     */
    @Override
    public int updateEnquireById(Enquire enquire, EnquireDetail enquireDetail) {
        EnquireDetailExample detailExample = new EnquireDetailExample();
        detailExample.createCriteria().andEnquireIdEqualTo(enquire.getId());
        int row = enquireDetailMapper.updateByExampleSelective(enquireDetail,detailExample);
        row = row+enquireMapper.updateByPrimaryKeySelective(enquire);
        return row;
    }

    /**
     * 根据enquire表id查询相关信息
     * @param id
     * @return
     */
    @Override
    public Enquire findEnquireToQuote(long id) {
        return enquireMapper.selectEnquireToQuote(id);
    }
}
