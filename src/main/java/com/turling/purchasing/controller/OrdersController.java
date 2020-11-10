package com.turling.purchasing.controller;

import com.turling.purchasing.dao.EmployeeMapper;
import com.turling.purchasing.entity.*;
import com.turling.purchasing.service.EnquireService;
import com.turling.purchasing.service.IdMappingService;
import com.turling.purchasing.service.OrdersService;
import com.turling.purchasing.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private IdMappingService idMappingService;

    @Autowired
    private EnquireService enquireService;

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 分页查询需求计划
     * @param curPage
     * @param pageSize
     * @return
     */
    @GetMapping("/findOrdersAll")
    @ResponseBody
    public EasyUIDataGrid findOrdersAll(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                        @RequestParam(name = "rows",defaultValue = "4") Integer pageSize,
                                        Orders orders){
        return ordersService.findOrdersAll((curPage-1)*pageSize,pageSize,orders);
    }

    /**
     * 需求计划添加
     * @param orders
     * @param idMapping
     * @return
     */
    @RequestMapping("/addOrders")
    @ResponseBody
    public String addOrders(Orders orders, IdMapping idMapping, HttpSession session){
        int ordersRow = 0;
        int idmappingRow = 0;
        //设置状态
        idMapping.setStatus("C001-10");
        List<IdMapping> ordersIdList = new ArrayList<>();
        ordersIdList.add(idMapping);
        session.setAttribute("ordersIdList",ordersIdList);
        if(orders.getId()==null){
            //需求计划编号
            SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(System.currentTimeMillis());
            orders.setOrderNum("100"+formatter.format(date)+"0000"+(ordersService.selectLastId()+1));
            //总价格  小计
            BigDecimal price=new BigDecimal(String.valueOf(orders.getUnitPrice()));
            orders.setSumPrice(new BigDecimal(Integer.parseInt(orders.getAmount())*price.intValue()));
            //添加需求计划
            ordersRow = ordersService.addOrders(orders);
            //添加状态
            idMapping.setOrderId(ordersService.selectLastId());
            idmappingRow = idMappingService.addIdMapping(idMapping);
        }else{
            //修改需求计划
             ordersRow = ordersService.updateOrders(orders);
        }
        if(ordersRow>0&&idmappingRow>0){
            return "success";
        }else{
            return "error";
        }
    }

    /**
     * 修改状态为C001-20
     * @param id
     * @return
     */
    @RequestMapping("updateOrdersStatusById")
    @ResponseBody
    public String updateOrdersStatusById(int [] id){
        int row = 0;
        //循环修改状态
        for (int i=0;i<id.length;i++){
            IdMapping idMapping = new IdMapping();
            idMapping.setStatus("C001-20");
            idMapping.setOrderId(new Long(id[i]));
            //调用修改方法
            row = idMappingService.updateIdMapping(idMapping);
        }
        return "success";
    }

    /**
     * 根据id查询需求计划
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findOrdersById")
    public String findOrdersById(int id, Model m){
        Orders orders = ordersService.selectOrdersById(new Long(id));
        m.addAttribute("orders",orders);
        return "planman/update_xuqiujihua";
    }

    /**
     * 根据id查询需求计划
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findOrdersByIdTodetail")
    public String findOrdersByIdTodetail(int id, Model m){
        Orders orders = ordersService.selectOrdersById(new Long(id));
        m.addAttribute("orders",orders);
        return "planman/print_order_detail";
    }

    /**
     * 根据id查询需求计划跳转至Equire_bianzhi页面
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findOrdersToEnquire")
    public String findOrdersToEnquire(int id, Model m,HttpSession session){
        //查询ordersid
        List<IdMapping> list = idMappingService.findIdMappingByStockId(id);
        //通过ordersid查询orders
        Orders ordersEnquire = ordersService.selectOrdersById(list.get(0).getOrderId());
        //询价书编号  300+当前日期+5位流水号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String enquireNum = "";
        if(enquireService.getLastId()==null){
            enquireNum = "300"+formatter.format(date)+"00001";
        }else{
            enquireNum = "300"+formatter.format(date)+"0000"+(enquireService.getLastId()+1);
        }
        SysUsers sysUsers = (SysUsers)session.getAttribute("users");
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        //System.out.println(enquireNum);
        m.addAttribute("enquireNum",enquireNum);
        m.addAttribute("ordersEnquire",ordersEnquire);
        m.addAttribute("employee",employeeList.get(0));
        session.setAttribute("empname",employeeList.get(0).getEmpName());
        return "queryandqueto/Enquire_bianzhi";
    }

    /**
     * 根据id修改需求计划
     * @return
     */
    @RequestMapping("/updateOrdersById")
    @ResponseBody
    public String updateOrdersById(Orders orders){
        int row = ordersService.updateOrdersById(orders);
        return "success";
    }

    /**
     * 根据id删除需求计划
     * @param id
     * @return
     */
    @RequestMapping("/delOrdersById")
    @ResponseBody
    public String delOrdersById(int id []){
        int idmappingRow = 0;
        int ordersRow = 0;
        for(int i=0;i<id.length;i++){
            idmappingRow = idMappingService.delIdMappingByOrderId(new Long(id[i]));
            ordersRow = ordersService.delOrdersById(new Long(id[i]));
        }
        //System.out.println(idmappingRow+"--"+ordersRow);
        return "success";
    }

    /**
     * 分页查询需求计划（状态为C001-20）
     * @param curPage
     * @param pageSize
     * @return
     */
    @GetMapping("/findOrdersByStatus")
    @ResponseBody
    public EasyUIDataGrid findOrdersByStatus(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                        @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return ordersService.findOrdersByStatus((curPage-1)*pageSize,pageSize);
    }
}
