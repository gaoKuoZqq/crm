package com.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerLoss;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerLossService;

@Controller
@RequestMapping("customerLoss")
public class CustomerLossController {
	@Autowired
	ICustomerLossService customerLossService;
	
	@RequestMapping("index")
	public String index(){
		return "customer_loss";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLoss customerLoss) {
		return customerLossService.find(page,rows,customerLoss);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return customerLossService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(CustomerLoss customerLoss){
		return customerLossService.add(customerLoss);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(CustomerLoss customerLoss){
		return customerLossService.update(customerLoss);
	}
	
	@RequestMapping("findById")
	@ResponseBody
	public CustomerLoss findById(Integer id){
		return customerLossService.findById(id);
	}
	
	@RequestMapping("updateConfirmLoss")
	@ResponseBody
	public ServerResponse<?> updateConfirmLoss(Integer id){
		return customerLossService.updateConfirmLoss(id);
	}
}
