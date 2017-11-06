package com.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CusDevPlan;
import com.crm.responce.ServerResponse;
import com.crm.service.ICusDevPlanService;

@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController {
	@Autowired
	private ICusDevPlanService cusDevPlanService;
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping("/index")
	public String index() {
		return "cus_dev_plan_item";
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public EasyUIDataGrideResult findAll(CusDevPlan cusDevPlan) {
		return cusDevPlanService.findAll(cusDevPlan);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse<?> delete(Integer id) {
		return cusDevPlanService.delete(id);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse<?> add(CusDevPlan cusDevPlan) {
		return cusDevPlanService.add(cusDevPlan);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse<?> update(CusDevPlan cusDevPlan) {
		return cusDevPlanService.update(cusDevPlan);
	}
	
}
