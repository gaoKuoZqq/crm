package com.crm.service;

import java.util.Date;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.OrderItem;
import com.crm.responce.ServerResponse;

public interface IOrderItemService {

	EasyUIDataGrideResult find(Integer page, Integer rows, OrderItem orderItem);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(OrderItem orderItem);

	ServerResponse<?> update(OrderItem orderItem);


}
