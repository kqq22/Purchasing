package com.turling.purchasing.service.impl;

import com.turling.purchasing.dao.MaterialMapper;
import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Material;
import com.turling.purchasing.entity.MaterialExample;
import com.turling.purchasing.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物资信息业务逻辑实现类
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Resource
    private MaterialMapper materialMapper;

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public EasyUIDataGrid findMaterialAll(Integer curPage, Integer pageSize) {
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(materialMapper.getCount());
        easyUIDataGrid.setRows(materialMapper.findAll(curPage,pageSize));
        return easyUIDataGrid;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Material findMaterialById(Long id) {
        return materialMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据物资编码查询物资id
     * @param code
     * @return
     */
    @Override
    public Long findMaterialIdByCode(String code) {
        return materialMapper.getId(code);
    }
}
