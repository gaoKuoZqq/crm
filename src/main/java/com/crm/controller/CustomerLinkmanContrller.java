package com.crm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerLinkman;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerLinkmanService;

import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.util.AddAliasesVisitor;

@Controller
@RequestMapping("customerLinkman")
public class CustomerLinkmanContrller {
	@Autowired
	ICustomerLinkmanService customerLinkmanService;
	
	@RequestMapping("index")
	public String index(){
		return "customer_linkman";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLinkman customerLinkman) {
		return customerLinkmanService.find(page,rows,customerLinkman);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return customerLinkmanService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(CustomerLinkman customerLinkman){
		return customerLinkmanService.add(customerLinkman);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(CustomerLinkman customerLinkman){
		return customerLinkmanService.update(customerLinkman);
	}
	
}
