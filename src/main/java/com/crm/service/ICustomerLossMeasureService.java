package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CustomerLossMeasure;
import com.crm.responce.ServerResponse;

public interface ICustomerLossMeasureService {

	EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLossMeasure customerLossMeasure);

	ServerResponse<?> delete(String id);

	ServerResponse<?> add(CustomerLossMeasure customerLossMeasure);

	ServerResponse<?> update(CustomerLossMeasure customerLossMeasure);

}
