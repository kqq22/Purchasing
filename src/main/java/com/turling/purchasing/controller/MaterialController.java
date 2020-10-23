package com.turling.purchasing.controller;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Material;
import com.turling.purchasing.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MaterialController {
    @Autowired
    private MaterialService materialService;
    @GetMapping("/findMaterialAll")
    @ResponseBody
    public EasyUIDataGrid findMaterialAll(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                        @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return materialService.findMaterialAll((curPage-1)*pageSize,pageSize);
    }

    @RequestMapping("/findMaterial")
    public String  findMaterial(@RequestParam("ids") int [] ids, Model m){
        List<Material> materials = new ArrayList<>();
        //循环查询
        for (int i : ids) {
            //存入集合
            materials.add(materialService.findMaterialById(new Long(i)));
        }
        //将数据存入Model
        m.addAttribute("materials",materials);
        return  "planman/test";
    }

}
