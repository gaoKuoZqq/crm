package com.crm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.crm.service.ICustomerServiceService;
import com.crm.service.ICutomerConsumptionService;
import com.crm.service.IDataDicService;
import com.crm.vo.Cake;
import com.crm.vo.CutomerConsumption;
import com.fasterxml.jackson.annotation.JsonFormat;

@RequestMapping("customerConsumption")
@Controller
public class CutomerConsumptionController {
	@Autowired
	ICutomerConsumptionService cutomerConsumptionService;
	@Autowired
	IDataDicService dataDicService;
	@Autowired
	ICustomerServiceService customerServiceService;
	
	@RequestMapping("customerContribution")
	public String customerContribution(){
		return "customer_contribution";
	}
	
	@RequestMapping("getCakes")
	@ResponseBody
	public List<Cake> getServeType(){
		List<String> serveTypes = dataDicService.findValueByName("服务类型");
		List<Integer> serveTypesCount = customerServiceService.findCountByTypes(serveTypes);
		List<Cake> cakes = new ArrayList<Cake>();
		for (int i = 0;i < serveTypes.size();i++) {
			Cake cake = new Cake(serveTypes.get(i),serveTypesCount.get(i));
			cakes.add(cake);
		}
		return cakes;
	}
	
	@RequestMapping("customerServe")
	@ResponseBody
	public ModelAndView customerServe(){
		ModelAndView modelAndView = new ModelAndView("customer_serve");
		List<String> serveTypes = dataDicService.findValueByName("服务类型");
		modelAndView.addObject("serveTypes",serveTypes);
		return modelAndView;
	}
	
	@RequestMapping("customerConstitute")
	@ResponseBody
	public ModelAndView customerConstitute(){
		ModelAndView modelAndView = new ModelAndView("customer_constitute");
		List<String> customerLevels = cutomerConsumptionService.findCustomerLevel();
		List<Integer> customerCounts = cutomerConsumptionService.findCustomerCount(customerLevels);
		modelAndView.addObject("customerLevels",customerLevels);
		modelAndView.addObject("customerCounts",customerCounts);
		return modelAndView;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public List<CutomerConsumption> find(String name) {
		return cutomerConsumptionService.find(name);
	}
}
