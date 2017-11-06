package com.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerLossMeasure;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerLossMeasureService;

@Controller
@RequestMapping("customerLossMeasure")
public class CustomerLossMeasureController {
	@Autowired
	ICustomerLossMeasureService customerLossMeasureService;
	
	@RequestMapping("index")
	public String index(){
		return "cus_loss_measure";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLossMeasure customerLossMeasure) {
		return customerLossMeasureService.find(page,rows,customerLossMeasure);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String id){
		return customerLossMeasureService.delete(id);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(CustomerLossMeasure customerLossMeasure){
		return customerLossMeasureService.add(customerLossMeasure);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(CustomerLossMeasure customerLossMeasure){
		return customerLossMeasureService.update(customerLossMeasure);
	}
	
}
