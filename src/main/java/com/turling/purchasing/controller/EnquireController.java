package com.turling.purchasing.controller;

import com.turling.purchasing.entity.EasyUIDataGrid;
import com.turling.purchasing.entity.Enquire;
import com.turling.purchasing.entity.EnquireDetail;
import com.turling.purchasing.entity.IdMapping;
import com.turling.purchasing.service.EnquireDetailService;
import com.turling.purchasing.service.EnquireService;
import com.turling.purchasing.service.IdMappingService;
import com.turling.purchasing.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EnquireController {
    @Autowired
    private EnquireService enquireService;

    @Autowired
    private EnquireDetailService enquireDetailService;

    @Autowired
    private IdMappingService idMappingService;

    @Autowired
    private QuoteService quoteService;

    /**
     * 添加询价书
     * @param enquire
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/addEnquire")
    public String addEnquire(Enquire enquire,HttpServletRequest request) throws ParseException {
        //System.out.println(enquire.getEnquireNum()+"--"+enquire.getEnquireName()+"--"+materialCode+"--"+materialName+"--"+measureUnit);
        //添加询价书信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        enquire.setEndDate(sdf.parse(request.getParameter("endDate")));
        int enquireRow = enquireService.addEnquire(enquire);
        EnquireDetail enquireDetail = new EnquireDetail();
        //C001-80：询价书已发出
        IdMapping idMapping = new IdMapping();
        idMapping.setOrderId(new Long(request.getParameter("orderId")));
        idMapping.setEnquireId(enquireService.getLastId());
        //设置状态
        idMapping.setStatus("C001-80");
        int idmappingRow = idMappingService.updateIdMapping(idMapping);
        //设置询价书明细信息
        enquireDetail.setEnquireId(enquireService.getLastId());
        enquireDetail.setOrderId(new Long(request.getParameter("orderId")));
        enquireDetail.setMaterialCode(request.getParameter("materialCode"));
        enquireDetail.setMaterialName(request.getParameter("materialName"));
        enquireDetail.setAmount(request.getParameter("amount"));
        enquireDetail.setMeasureUnit(request.getParameter("measureUnit"));
        enquireDetail.setStartDate(sdf.parse(request.getParameter("startDate")));
        enquireDetail.setEndDate(sdf.parse(request.getParameter("endDate")));
        enquireDetail.setRemark(enquire.getRemark());
        //添加询价书明细信息
        int detailRow =  enquireDetailService.addEnquireDetail(enquireDetail);
        //System.out.println("--"+detailRow+"--"+idmappingRow);
        return "queryandqueto/Project_list";
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findEnquireAll")
    @ResponseBody
    public EasyUIDataGrid findEnquireAll(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                         @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return enquireService.findEnquireAll(curPage,pageSize);
    }

    /**
     * 根据id查询询价书信息
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findEnquireById")
    public String findEnquireById(int id, Model m){
        Enquire enquire = enquireService.findEnquireById(id);
        m.addAttribute("enquire",enquire);
        return "queryandqueto/Enquire_update";
    }

    /**
     * 注册一个类型解析器（设置日期）
     * @param binder
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 根据id修改询价书及询价书明细
     * @param enquire
     * @param enquireDetail
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/updateEnquireById")
    public String updateEnquireById(Enquire enquire,EnquireDetail enquireDetail,HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //设置参数
        enquire.setId(new Long(Integer.parseInt(request.getParameter("id"))));
        enquire.setRemark(request.getParameter("remark"));
        enquire.setEndDate(sdf.parse(request.getParameter("endDate")));
        enquireDetail.setStartDate(sdf.parse(request.getParameter("startDate")));
        enquireDetail.setEndDate(sdf.parse(request.getParameter("endDate")));
        //调用方法
        int row = enquireService.updateEnquireById(enquire,enquireDetail);
        //System.out.println(row);
        return "queryandqueto/ask1";
    }

    /**
     * 查询询价书信息（跳转至添加报价页面）
     * @return
     */
    @RequestMapping("/findEnquireToQuote")
    public String findEnquireToQuote(long id,Model m){
        //System.out.println(id);
        Enquire enquire = enquireService.findEnquireToQuote(id);
        //报价书编号 500+当前日期+5位流水号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String quoteNum = "";
        if(quoteService.getLastId()==null){
            quoteNum = "500"+formatter.format(date)+"00001";
        }else{
            quoteNum = "500"+formatter.format(date)+"0000"+(quoteService.getLastId()+1);
        }
        m.addAttribute("quoteNum",quoteNum);
        //报价书标题
        String enquireTitle =  "对"+enquire.getEnquireName()+"的报价";
        m.addAttribute("enquireTitle",enquireTitle);
        m.addAttribute("enquire",enquire);
        return "supplyman/addQuote";
    }
}
