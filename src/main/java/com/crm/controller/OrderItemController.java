package com.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.OrderItem;
import com.crm.responce.ServerResponse;
import com.crm.service.IOrderItemService;

@Controller
@RequestMapping("orderItem")
public class OrderItemController {
	@Autowired
	IOrderItemService orderItemService;
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, OrderItem orderItem) {
		return orderItemService.find(page,rows,orderItem);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return orderItemService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(OrderItem orderItem){
		return orderItemService.add(orderItem);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(OrderItem orderItem){
		return orderItemService.update(orderItem);
	}
	
}
