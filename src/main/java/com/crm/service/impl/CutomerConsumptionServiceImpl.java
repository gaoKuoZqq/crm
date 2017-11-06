package com.crm.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.mapper.CustomerMapper;
import com.crm.service.ICutomerConsumptionService;
import com.crm.util.Util;
import com.crm.vo.CutomerConsumption;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.util.StringUtil;

@Service
public class CutomerConsumptionServiceImpl implements ICutomerConsumptionService{
	@Autowired
	CustomerMapper customerMapper;
	@Override
	public List<CutomerConsumption> find(String name) {
		 if (name == null || name.trim().equals("")) {
			name = "";
		}
		 List<CutomerConsumption> consumptionList = customerMapper.findCutomerConsumption(Util.LikeFormat(name));
		 Set<String> customerNameSet = new HashSet<>();
		 for (int i = 0;i < consumptionList.size();) {
			if (customerNameSet.contains(consumptionList.get(i).getCustomerName())) {
				consumptionList.get(i - 1).setConsumption(consumptionList.get(i - 1).getConsumption() + consumptionList.get(i).getConsumption());
				consumptionList.remove(i);
			}else{
				customerNameSet.add(consumptionList.get(i).getCustomerName());
				i = i + 1;
			}
		}
		 return consumptionList;
	}

	public List<String> findCustomerLevel() {
		return customerMapper.findCustomerLevel();
	}

	@Override
	public List<Integer> findCustomerCount(List<String> customerLevels) {
		List<Integer> customerCounts = new ArrayList<Integer>();
		for (String level : customerLevels) {
			customerCounts.add(customerMapper.findCountByLevel(level));
		}
		return customerCounts;
	}
}
