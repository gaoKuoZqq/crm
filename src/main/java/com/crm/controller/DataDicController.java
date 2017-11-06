package com.crm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.DataDic;
import com.crm.responce.ServerResponse;
import com.crm.service.IDataDicService;

@Controller
@RequestMapping("dataDic")
public class DataDicController {
	@Autowired
	IDataDicService dataDicService;
	
	@RequestMapping("index")
	public String index(){
		return "data_dic";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, DataDic dataDic) {
		return dataDicService.find(page,rows,dataDic);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return dataDicService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(DataDic dataDic){
		return dataDicService.add(dataDic);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(DataDic dataDic){
		return dataDicService.update(dataDic);
	}
	
	@RequestMapping("findDataDicName")
	@ResponseBody
	public List<DataDic> findDataDicName(){
		return dataDicService.findDataDicName();
	}
	
	@RequestMapping("getDataDicValueList")
	@ResponseBody
	public List<DataDic> getDataDicValueList(DataDic dataDic){
		return dataDicService.getDataDicValueList(dataDic);
	}
}
