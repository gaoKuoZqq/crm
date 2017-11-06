package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.Customer;
import com.crm.responce.ServerResponse;

public interface ICustomerService {

	EasyUIDataGrideResult find(Integer page, Integer rows, Customer customer);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(Customer customer);

	ServerResponse<?> update(Customer customer);

}
