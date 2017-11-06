package com.crm.service;

import java.util.Date;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerContact;
import com.crm.responce.ServerResponse;

public interface ICustomerContactService {

	EasyUIDataGrideResult find(Integer page, Integer rows, CustomerContact customerContact,Date startTime,Date endTime);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(CustomerContact customerContact);

	ServerResponse<?> update(CustomerContact customerContact);


}
