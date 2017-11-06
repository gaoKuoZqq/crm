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
import com.crm.pojo.CustomerContact;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerContactService;

@Controller
@RequestMapping("customerContact")
public class CustomerContactController {
	@Autowired
	ICustomerContactService customerContactService;
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("index")
	public String index(){
		return "customer_contact";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerContact customerContact,Date startTime,Date endTime) {
		return customerContactService.find(page,rows,customerContact,startTime,endTime);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return customerContactService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(CustomerContact customerContact){
		return customerContactService.add(customerContact);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(CustomerContact customerContact){
		return customerContactService.update(customerContact);
	}
	
}
