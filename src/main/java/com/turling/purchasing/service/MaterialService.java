package com.turling.purchasing.service;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Material;


/**
 * 物资信息业务逻辑接口类
 */
public interface MaterialService {
    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    public EasyUIDataGrid findMaterialAll(Integer curPage, Integer pageSize);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Material findMaterialById(Long id);
}
