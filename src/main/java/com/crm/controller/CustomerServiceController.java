package com.crm.controller;


import java.text.ParseException;
import java.text.ParsePosition;
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
import com.crm.pojo.CustomerService;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerServiceService;

@Controller
@RequestMapping("customerService")
public class CustomerServiceController {
	@Autowired
	ICustomerServiceService customerServiceService;
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	    binder.registerCustomEditor(Date.class,
		           new CustomDateEditor(dateFormat1, true));
	}
	
	@RequestMapping("indexAdd")
	public String indexAdd(){
		return "customer_service_add";
	}
	
	@RequestMapping("indexAssign")
	public String indexAssign(){
		return "customer_service_assign";
	}
	
	@RequestMapping("indexDeal")
	public String indexDeal(){
		return "customer_service_deal";
	}
	
	@RequestMapping("indexDealResult")
	public String indexDealResult(){
		return "customer_service_deal_result";
	}
	
	@RequestMapping("indexFinish")
	public String indexFinish(){
		return "customer_service_finish";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerService customerService,String startTime,String endTime) {
		return customerServiceService.find(page,rows,customerService,startTime,endTime);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return customerServiceService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(CustomerService customerService){
		return customerServiceService.add(customerService);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(CustomerService customerService){
		return customerServiceService.update(customerService);
	}
	
	@RequestMapping("assign")
	@ResponseBody
	public ServerResponse<?> assign(String ids,String assigner,String assignTime){
		return customerServiceService.assign(ids, assigner, assignTime);
	}
}
