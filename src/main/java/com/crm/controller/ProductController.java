package com.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.Product;
import com.crm.responce.ServerResponse;
import com.crm.service.IProductService;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	IProductService productService;
	
	@RequestMapping("index")
	public String index(){
		return "product";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, Product product) {
		return productService.find(page,rows,product);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return productService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(Product product){
		return productService.add(product);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(Product product){
		return productService.update(product);
	}
	
}
