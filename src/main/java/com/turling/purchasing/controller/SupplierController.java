package com.turling.purchasing.controller;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Supplier;
import com.turling.purchasing.entity.SysUsers;
import com.turling.purchasing.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    @RequestMapping("/addSupplier")
    public String addSupplier(Supplier supplier){
        if(supplierService.getLastId()!=null){
            supplier.setSupplierNum("400"+supplierService.getLastId()+1);
        }else {
            supplier.setSupplierNum("4001");
        }
        supplier.setStatus("S004-1");
        supplier.setUserId(new Long(5));
        int row = supplierService.addSupplier(supplier);
        //System.out.println(row);
        return "planman/provider_cx";
    }

    /**
     * 查询供应商基本信息
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findSupplier")
    @ResponseBody
    public EasyUIDataGrid findSupplier(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                                        @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return supplierService.findSupplier(curPage,pageSize);
    }

    /**
     * 根据用户id查询供应商
     * @param session
     * @param m
     * @return
     */
    @RequestMapping("/findSupplierByUserId")
    public String findSupplierByUserId(HttpSession session, Model m){
        SysUsers sysUsers = (SysUsers) session.getAttribute("users");
        Supplier supplier = supplierService.findSupplierByUsersId(sysUsers.getId());
        m.addAttribute("supplier",supplier);
        return "supplyman/jiffprov_look";
    }
}
