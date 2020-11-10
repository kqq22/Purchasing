package com.turling.purchasing.controller;

import com.turling.purchasing.entity.*;
import com.turling.purchasing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private QuoteDetailService quoteDetailService;

    @Autowired
    private IdMappingService idMappingService;

    @Autowired
    private ContractApplyService contractApplyService;

    /**
     * 添加报价书
     * @param quote
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping("/addQuote")
    public String addQuote(Quote quote, HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //其他
        int OTHER_PRICE = Integer.parseInt(request.getParameter("OTHER_PRICE"));
        //总金额
        BigDecimal sumPrice=new BigDecimal(request.getParameter("sumPrice"));
        //运杂费
        int MIX_PRICE = Integer.parseInt(request.getParameter("MIX_PRICE"));
        //单价
        BigDecimal UNIT_PRICE=new BigDecimal(request.getParameter("UNIT_PRICE"));
        //总计
        int TOTAL_PRICE = OTHER_PRICE+MIX_PRICE+sumPrice.intValue();
        //开始交货日期
        String starDate = request.getParameter("starDate");
        //结束交货日期
        String overDate = request.getParameter("overDate");
        long oid = new Long(request.getParameter("oid"));
        Orders orders = ordersService.selectOrdersById(oid);
        quote.setQueDate(new Date());
        //询价书序号
        long eid = new Long(request.getParameter("eid"));
        quote.setEnquireId(eid);
        quote.setEndDate(sdf.parse(overDate));
        quote.setOverallPrice(BigDecimal.valueOf(TOTAL_PRICE));
        //状态 B001-1：结果未发(默认)
        quote.setStatus("B001-1");
        quote.setId(quoteService.getLastId()+1);
        //添加报价书
        int quoteRow = quoteService.addQuote(quote);
        QuoteDetail quoteDetail = new QuoteDetail();
        quoteDetail.setQuoteId(quoteService.getLastId());
        quoteDetail.setOrderId(oid);
        quoteDetail.setMaterialCode(orders.getMaterialCode());
        quoteDetail.setMaterialName(orders.getMaterialName());
        quoteDetail.setMeasureUnit(orders.getMeasureUnit());
        quoteDetail.setAmount(quote.getSumAmount().toString());
        quoteDetail.setUnitPrice(UNIT_PRICE);
        quoteDetail.setSumPrice(sumPrice);
        quoteDetail.setMixPrice(new BigDecimal(MIX_PRICE));
        quoteDetail.setOtherPrice(new BigDecimal(OTHER_PRICE));
        quoteDetail.setTotalPrice(new BigDecimal(TOTAL_PRICE));
        quoteDetail.setStartDate(sdf.parse(starDate));
        quoteDetail.setEndDate(sdf.parse(overDate));
        //添加报价明细 C001-100：已揭示报价
        int quoteDetailRow = quoteDetailService.addQuoteDetail(quoteDetail);
        //修改状态
        IdMapping idMapping = new IdMapping();
        idMapping.setEnquireId(eid);
        idMapping.setQuoteId(quoteService.getLastId());
        idMapping.setStatus("C001-100");
        int idmappingRow = idMappingService.updateMappingStatusByEnquireId(idMapping);
        //System.out.println(quote.getQuoteNum()+"--"+quote.getQueTitle()+"--"+quote.getQuoRemark()+"--"+quote.getSumAmount());
        //System.out.println(quoteRow+"--"+quoteDetailRow+"--"+idmappingRow);
        return "supplyman/Project_list";
    }

    /**
     * 分页查询
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findQuoteAll")
    @ResponseBody
    public EasyUIDataGrid findQuoteAll(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                       @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return quoteService.findQuoteAll(curPage,pageSize);
    }

    /**
     * 根据id查询Quote
     * @param id
     * @return
     */
    @RequestMapping("/findQuoteById")
    public String findQuoteById(long id, Model m){
        //System.out.println(id);
        Quote quote = quoteService.findQuoteById(id);
        m.addAttribute("quote",quote);
        return "supplyman/updateQuote";
    }

    /**
     * 根据id修改报价书
     * @param quote
     * @return
     */
    @RequestMapping("/updateQuoteById")
    public String updateQuoteById(Quote quote){
        //System.out.println(quote.getId());
        int row = quoteService.updateQuoteById(quote);
        //System.out.println(row);
        return "supplyman/Project_list";
    }

    /**
     * 查询报价，状态为已揭示
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findQuoteByStatus")
    @ResponseBody
    public EasyUIDataGrid findQuoteByStatus(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                            @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return quoteService.findQuoteToContract(curPage,pageSize);
    }

    /**
     * 根据id查询报价书及相关信息
     * @param id
     * @param m
     * @return
     */
    @RequestMapping("/findQuoteByIdToContract")
    public String findQuoteByIdToContract(long id,Model m){
        //合同申请编号  400+当前日期+5位流水号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String contractNum = "";
        if(contractApplyService.getLastId()==null){
            contractNum = "400"+formatter.format(date)+"00001";
        }else{
            contractNum = "400"+formatter.format(date)+"0000"+(contractApplyService.getLastId()+1);
        }
        m.addAttribute("contractNum",contractNum);
        m.addAttribute("nowDate",new Date());
        //查询报价书
        Quote quote = quoteService.findQuoteByIdToContract(id);
        m.addAttribute("quote",quote);
        return "contractmanager/bianzhihetong";
    }
}

