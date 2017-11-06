package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerLoss;
import com.crm.responce.ServerResponse;

public interface ICustomerLossService {

	EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLoss customerLoss);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(CustomerLoss customerLoss);

	ServerResponse<?> update(CustomerLoss customerLoss);

	CustomerLoss findById(Integer id);

	ServerResponse<?> updateConfirmLoss(Integer id);
	
	void customerAutoLoss();

}
