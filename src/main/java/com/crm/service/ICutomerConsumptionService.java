package com.crm.service;

import java.util.List;

import com.crm.vo.CutomerConsumption;

public interface ICutomerConsumptionService {

	List<CutomerConsumption> find(String name);

	public List<String> findCustomerLevel();

	List<Integer> findCustomerCount(List<String> customerLevels);
}
