package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.Product;
import com.crm.responce.ServerResponse;

public interface IProductService {

	EasyUIDataGrideResult find(Integer page, Integer rows, Product product);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(Product product);

	ServerResponse<?> update(Product product);

}
