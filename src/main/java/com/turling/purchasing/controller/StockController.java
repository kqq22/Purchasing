package com.turling.purchasing.controller;

import com.turling.purchasing.dao.EmployeeMapper;
import com.turling.purchasing.entity.*;
import com.turling.purchasing.service.IdMappingService;
import com.turling.purchasing.service.OrdersService;
import com.turling.purchasing.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StockController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private StockService stockService;

    @Autowired
    private IdMappingService idMappingService;

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 查询需求计划（跳转至编制采购计划）
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/EditStockcompilePlan")
    public String EditStockcompilePlan(@RequestParam("id") int id, Model m, HttpSession session){
        //200+当前日期+5位流水号
        //采购计划编号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String stockNum = "";
        if(stockService.getLastId()==null){
            stockNum = "200"+formatter.format(date)+"00001";
        }else{
            stockNum = "200"+formatter.format(date)+"0000"+(stockService.getLastId()+1);
        }
        m.addAttribute("stockNum",stockNum);
        //System.out.println(stockNum);
        //System.out.println(id);
        //查询需求计划
        Orders orders = ordersService.selectOrdersById(new Long(id));
        //BigDecimal转int
        BigDecimal price=new BigDecimal(String.valueOf(orders.getUnitPrice()));
        //总价格
        int sumPrice = (Integer.parseInt(orders.getAmount())*price.intValue());
        //查询满足条件的供应商
        List<Supplier> supplierList = stockService.findSupplier(orders.getMaterialCode());
        m.addAttribute("sumPrice",sumPrice);
        m.addAttribute("orders",orders);
        m.addAttribute("supplierList",supplierList);
        SysUsers sysUsers = (SysUsers)session.getAttribute("users");
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        m.addAttribute("employee",employeeList.get(0));
        return "planman/bianzhicaigoujihua";
    }


    /**
     * 添加采购计划
     * @param stock
     * @param id
     * @return
     */
    @RequestMapping("/addStock")
    @ResponseBody
    public String addStock(Stock stock,long id){
        //C001-30：采购计划未报批
        //System.out.println(id);
        //添加采购计划
        stock.setStockType("C000-1");
        stock.setId(stockService.getLastId()+1);
        int sRow = stockService.addStock(stock);
        //修改状态
        IdMapping idMapping = new IdMapping();
        idMapping.setOrderId(id);
        idMapping.setStockId(stockService.getLastId());
        idMapping.setStatus("C001-30");
        int iRow = idMappingService.updateIdMapping(idMapping);
        //System.out.println(iRow+"--"+sRow);
        return "success";
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findStockAll")
    @ResponseBody
    public EasyUIDataGrid findStockAll(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                       @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
     return stockService.findStockAll(curPage,pageSize);

    }

    /**
     * 修改状态（采购计划未审批）
     * @param id
     * @return
     */
    @RequestMapping("/updateStockStatus")
    public String updateStockStatus(long id){
        //System.out.println(id);
        IdMapping idMapping = new IdMapping();
        idMapping.setStockId(id);
        idMapping.setStatus("C001-40");
        int row = idMappingService.updateIdMappingByStock(idMapping);
        //System.out.println(row);
        return "planman/Project_list";
    }

    /**
     * 查询采购详情
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/showStockDetail")
    public String showStockDetail(long id,Model m){
        //System.out.println(id);
        Stock stock = stockService.findStockById(id);
        List<IdMapping> idMappingList = idMappingService.findIdMappingByStockId(stock.getId());
        Orders orders =  ordersService.selectOrdersById(idMappingList.get(0).getOrderId());
        m.addAttribute("stock",stock);
        m.addAttribute("orders",orders);
        return "planman/xjfatz_xjfamx";
    }

    /**
     * 根据状态查询采购计划（C001-40）
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findStockByStatus")
    @ResponseBody
    public EasyUIDataGrid findStockByStatus(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                            @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
           return stockService.findStockByStatus(curPage,pageSize);
    }

    /**
     * 根据状态查询采购计划（C001-50）
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findStockByStatus3")
    @ResponseBody
    public EasyUIDataGrid findStockByStatus3(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                             @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return stockService.findStockByStatus3(curPage,pageSize);
    }

    /**
     * 根据状态查询采购计划（C001-60）
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findStockByStatus2")
    @ResponseBody
    public EasyUIDataGrid findStockByStatus2(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                            @RequestParam(name = "rows",defaultValue = "2") Integer pageSize){
        return stockService.findStockByStatus2(curPage,pageSize);
    }

    /**
     * 根据选择的采购计划查询（审批）
     * @return
     */
    @RequestMapping("/approvalStock")
    public String approvalStock(long id,Model m,HttpSession session){
        //System.out.println(id);
        Stock stock = stockService.findStockById(id);
        m.addAttribute("stock",stock);
        m.addAttribute("date",new Date());
        SysUsers sysUsers = (SysUsers)session.getAttribute("users");
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        m.addAttribute("employee",employeeList.get(0));
        session.setAttribute("empname",employeeList.get(0).getEmpName());
        return "contractmanager/Apply_caiwushenpi";
    }

    /**
     * 审批 修改状态和采购计划信息
     * @param request
     * @return
     */
    @RequestMapping("/approvalStockResult")
    public String approvalStockResult(HttpServletRequest request){
        long id = new Long(request.getParameter("id"));
        String agree = request.getParameter("p_agree");
        String idea = request.getParameter("p_idea");
        //System.out.println(id+"--"+agree+"--"+idea);
        //修改采购计划，1.部长是否同意 2.审批时间 3.审批意见
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Stock stock = new Stock();
        stock.setId(id);
        stock.setLeadDate(new Date());
        IdMapping idMapping = new IdMapping();
        idMapping.setStockId(id);
        //判断是否同意
        if(agree.equals("1")){
            stock.setLeadAgree("S002-1");
            idMapping.setStatus("C001-50");
        }else{
            stock.setLeadAgree("S002-0");
            idMapping.setStatus("C001-51");
        }
        if(idea!=""||idea==null){
            stock.setLeadOpinion(idea);
        }
        int stockRow = stockService.updateStockById(stock);
        //修改状态  1：C001-50：采购计划未下达，234：C001-51：采购计划审批不通过
        int idmappingRow = idMappingService.updateIdMappingByStock(idMapping);
        //System.out.println(stockRow+"--"+idmappingRow);
        return "/contractmanager/Apply_daishencaiwu";
    }

    /**
     * 下达采购计划
     * @param id
     * @return
     */
    @RequestMapping("/issuedStock")
    @ResponseBody
    public String issuedStock(long id){
        //System.out.println(id);
        //修改状态 C001-60
        IdMapping idMapping = new IdMapping();
        idMapping.setStockId(id);
        idMapping.setStatus("C001-60");
        int idmppingRow = idMappingService.updateIdMappingByStock(idMapping);
        //添加下达时间
        Stock stock = new Stock();
        stock.setId(id);
        stock.setSubmitDate(new Date());
        int stockRow = stockService.updateStockById(stock);
        //System.out.println(idmppingRow+"--"+stockRow);
        return "success";
    }
}
