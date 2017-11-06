package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerLinkman;
import com.crm.responce.ServerResponse;

public interface ICustomerLinkmanService {

	EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLinkman customerLinkman);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(CustomerLinkman customerLinkman);

	ServerResponse<?> update(CustomerLinkman customerLinkman);


}
