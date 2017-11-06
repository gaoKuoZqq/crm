package com.crm.service;

import java.util.Date;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerOrder;
import com.crm.responce.ServerResponse;

public interface ICustomerOrderService {

	EasyUIDataGrideResult find(Integer page, Integer rows, CustomerOrder customerOrder);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(CustomerOrder customerOrder);

	ServerResponse<?> update(CustomerOrder customerOrder);


}
