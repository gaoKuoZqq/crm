package com.crm.service;

import java.util.Date;
import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerService;
import com.crm.responce.ServerResponse;

public interface ICustomerServiceService {

	EasyUIDataGrideResult find(Integer page, Integer rows, CustomerService customerService,String startTime,String endTime);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(CustomerService customerService);

	ServerResponse<?> update(CustomerService customerService);

	ServerResponse<?> assign(String ids,String assigner, String assignTime);

	List<Integer> findCountByTypes(List<String> serveTypes);


}
