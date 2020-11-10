package com.turling.purchasing.controller;

import com.turling.purchasing.dao.EmployeeMapper;
import com.turling.purchasing.entity.*;
import com.turling.purchasing.service.ContractApplyService;
import com.turling.purchasing.service.IdMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ContractApplyController {
    @Autowired
    private ContractApplyService contractApplyService;

    @Autowired
    private IdMappingService idMappingService;


    @Resource
    private EmployeeMapper employeeMapper;
    /**
     * 添加合同申请表
     * @return
     */
    @RequestMapping("/addContractApply")
    public String addContractApply(ContractApply contractApply, HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("quoteId"));
        ContractApply c = contractApplyService.findStockById(id);
        //添加合同申请
        int contractRow = contractApplyService.addContractApply(contractApply);
        //C001-110：合同申请未报批
        IdMapping idMapping = new IdMapping();
        idMapping.setQuoteId(new Long(id));
        idMapping.setContAppId(contractApplyService.getLastId());
        idMapping.setStatus("C001-110");
        //更改状态
        int idmappingRow = idMappingService.updateMappingStatusByQuoteId(idMapping);
        return "contractmanager/jieshilist";
    }

    /**
     * 查询合同申请
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findContract")
    @ResponseBody
    public EasyUIDataGrid findContract(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                       @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return contractApplyService.findContractApply(curPage,pageSize);
    }

    /**
     * 根据合同申请id修改状态
     * @param id
     * @return
     */
    @RequestMapping("/updateContractStatus")
    public String updateContractStatus(long id){
        //System.out.println(id);
        IdMapping idMapping = new IdMapping();
        idMapping.setContAppId(id);
        //合同已确定
        idMapping.setStatus("C001-115");
        int row = idMappingService.updateContractStatus(idMapping);
        //System.out.println(row);
        return "contractmanager/Apply_querenList";
    }


    /**
     * 查询合同申请
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findContractByStatus")
    @ResponseBody
    public EasyUIDataGrid findContractByStatus(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                       @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return contractApplyService.findContractByStatus(curPage,pageSize);
    }

    /**
     * 根据合同申请id修改状态为未审核
     * @param id
     * @return
     */
    @RequestMapping("/updateIdMppingByContract")
    public String updateIdMppingByContract(long id){
        //System.out.println(id);
        IdMapping idMapping = new IdMapping();
        idMapping.setContAppId(id);
        idMapping.setStatus("C001-120");
        int row = idMappingService.updateContractStatus(idMapping);
        //System.out.println(row);
        return "contractmanager/Apply_querenList";
    }


    /**
     * 查询合同申请
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findContractByContractStatus")
    @ResponseBody
    public EasyUIDataGrid findContractByContractStatus(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                               @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return contractApplyService.findContractByContractStatus(curPage,pageSize);
    }

    /**
     * 查询合同申请（计划员已审核）
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findContractByContractStatus2")
    @ResponseBody
    public EasyUIDataGrid findContractByContractStatus2(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                                       @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return contractApplyService.findContractByContractStatus2(curPage,pageSize);
    }

    /**
     * 查询合同申请（部长已审批）
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findContractByContractStatus3")
    @ResponseBody
    public EasyUIDataGrid findContractByContractStatus3(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                                        @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return contractApplyService.findContractByContractStatus3(curPage,pageSize);
    }

    /**
     * 查询合同申请（合同已签订）
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/findContractByContractStatus4")
    @ResponseBody
    public EasyUIDataGrid findContractByContractStatus4(@RequestParam(name = "page",defaultValue = "1") Integer curPage,
                                                        @RequestParam(name = "rows",defaultValue = "4") Integer pageSize){
        return contractApplyService.findContractByContractStatus4(curPage,pageSize);
    }


    /**
     * 根据id查询合同申请信息（跳转至计划部长审批）
     * @param id
     * @return
     */
    @RequestMapping("/findContractById")
    public String findContractById(long id, Model m, HttpSession session){
        //System.out.println(id);
        ContractApply contractApply = contractApplyService.findContractById(id);
        m.addAttribute("contractApply",contractApply);
        m.addAttribute("nowDate",new Date());
        SysUsers sysUsers = (SysUsers)session.getAttribute("users");
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        m.addAttribute("employee",employeeList.get(0));
        return "contractmanager/Apply_jihuashenpi";
    }

    /**
     * 根据id查询合同申请信息（跳转至财务部长审批）
     * @param id
     * @return
     */
    @RequestMapping("/findContractById2")
    public String findContractById2(long id, Model m,HttpSession session){
        //System.out.println(id);
        ContractApply contractApply = contractApplyService.findContractById(id);
        m.addAttribute("contractApply",contractApply);
        m.addAttribute("nowDate",new Date());
        SysUsers sysUsers = (SysUsers)session.getAttribute("users");
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        m.addAttribute("employee",employeeList.get(0));
        return "contractmanager/Apply_caiwushenpihetong";
    }

    /**
     * 根据id修改合同申请信息（计划部长审批）
     * @param contractApply
     * @return
     */
    @RequestMapping("/updateContractById")
    public String updateContractById(ContractApply contractApply){
        //System.out.println(contractApply.getId()+"--"+contractApply.getLeadAgree()+"--"+contractApply.getLeadOpinion());
        IdMapping idMapping = new IdMapping();
        idMapping.setContAppId(contractApply.getId());
        if(contractApply.getPlanAgree().equals("S002-1")){
            idMapping.setStatus("C001-130");
        }else {
            idMapping.setStatus("C001-131");
        }
        contractApply.setLeadDate(new Date());
        int contractRow = contractApplyService.updateContractById(contractApply);
        int idmappingRow = idMappingService.updateContractStatus(idMapping);
        //System.out.println(contractRow+"--"+idmappingRow);
        return "contractmanager/Apply_daishenjihua";
    }

    /**
     * 根据id修改合同申请信息（财务部长审批）
     * @param contractApply
     * @return
     */
    @RequestMapping("/updateContractById2")
    public String updateContractById2(ContractApply contractApply){
        //System.out.println(contractApply.getId()+"--"+contractApply.getLeadAgree()+"--"+contractApply.getLeadOpinion());
        IdMapping idMapping = new IdMapping();
        idMapping.setContAppId(contractApply.getId());
        if(contractApply.getLeadAgree().equals("S002-1")){
            idMapping.setStatus("C001-140");
        }else {
            idMapping.setStatus("C001-141");
        }
        contractApply.setLeadDate(new Date());
        int contractRow = contractApplyService.updateContractById(contractApply);
        int idmappingRow = idMappingService.updateContractStatus(idMapping);
        //System.out.println(contractRow+"--"+idmappingRow);
        return "contractmanager/Apply_daishencaiwuhetong";
    }

    /**
     * 根据id查询合同申请信息
     * @param id
     * @return
     */
    @RequestMapping("/findContractById3")
    public String findContractById3(long id, Model m,HttpSession session){
        //System.out.println(id);
        ContractApply contractApply = contractApplyService.findContractById(id);
        m.addAttribute("contractApply",contractApply);
        m.addAttribute("nowDate",new Date());
        SysUsers sysUsers = (SysUsers)session.getAttribute("users");
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        m.addAttribute("employee",employeeList.get(0));
        return "contractmanager/Apply_changzhangshenpi";
    }

    /**
     * 根据id修改合同申请信息（厂长审批）
     * @param contractApply
     * @return
     */
    @RequestMapping("/updateContractById3")
    public String updateContractById3(ContractApply contractApply){
        IdMapping idMapping = new IdMapping();
        idMapping.setContAppId(contractApply.getId());
        idMapping.setStatus("C001-150");
        int idmappingRow = idMappingService.updateContractStatus(idMapping);
        //System.out.println(idmappingRow);
        return "contractmanager/Apply_daishencaiwuhetong";
    }

    /**
     * 根据id修改合同申请信息（合同归档）
     * @param id
     * @return
     */
    @RequestMapping("/updateContractById4")
    public String updateContractById4(long id){
        IdMapping idMapping = new IdMapping();
        idMapping.setContAppId(id);
        idMapping.setStatus("C001-160");
        int idmappingRow = idMappingService.updateContractStatus(idMapping);
        //System.out.println(idmappingRow);
        return "supplyman/ProviderConsignment";
    }
}
