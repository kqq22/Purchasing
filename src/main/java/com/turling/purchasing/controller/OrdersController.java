package com.turling.purchasing.controller;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Orders;
import com.turling.purchasing.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/findOrdersAll")
    @ResponseBody
    public EasyUIDataGrid findOrdersAll(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                        @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return ordersService.findOrdersAll((curPage-1)*pageSize,pageSize);
    }

    @PostMapping("/findOrders")
    @ResponseBody
    public String findOrders(int [] ids){
        for (int i : ids) {
            System.out.println(i);
        }
        return "success";
    }

    @RequestMapping("/addOrders")
    @ResponseBody
    public String addOrders(Orders orders){
        System.out.println(orders.getMaterialCode());
        return "success";
    }
}
