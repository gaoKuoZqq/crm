package com.crm.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CusDevPlan;
import com.crm.pojo.SaleChance;
import com.crm.responce.ServerResponse;
import com.crm.service.ICusDevPlanService;
import com.crm.service.ISaleChanceService;

@Controller
@RequestMapping("saleChance")
public class SaleChanceController {
	@Autowired
	ISaleChanceService saleChanceService;
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("index")
	public String index(){
		return "sale_chance";
	}
	
	@RequestMapping("cusDevPlan")
	public String cusDevPlan() {
		return "cus_dev_plan";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, SaleChance saleChance) {
		return saleChanceService.find(page,rows,saleChance);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return saleChanceService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(SaleChance saleChance){
		return saleChanceService.add(saleChance);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(SaleChance saleChance){
		return saleChanceService.update(saleChance);
	}
	
	@RequestMapping("findById")
	@ResponseBody
	public SaleChance findById(Integer id){
		return saleChanceService.findById(id);
	}
	
	@RequestMapping("updateDevResult")
	@ResponseBody
	public ServerResponse<?> updateDevResult(SaleChance saleChance){
		return saleChanceService.updateDevResult(saleChance);
	}
}
