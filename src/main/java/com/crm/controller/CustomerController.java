package com.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.Customer;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	ICustomerService customerService;
	
	@RequestMapping("index")
	public String index(){
		return "customer";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, Customer customer) {
		return customerService.find(page,rows,customer);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return customerService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(Customer customer){
		return customerService.add(customer);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(Customer customer){
		return customerService.update(customer);
	}
	
}
