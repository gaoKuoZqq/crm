package com.crm.service;

import javax.servlet.ServletOutputStream;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.SaleChance;
import com.crm.responce.ServerResponse;

public interface ISaleChanceService {

	EasyUIDataGrideResult find(Integer page, Integer rows, SaleChance saleChance);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(SaleChance saleChance);

	ServerResponse<?> update(SaleChance saleChance);

	SaleChance findById(Integer id);

	ServerResponse<?> updateDevResult(SaleChance saleChance);

	void exportExcel(ServletOutputStream outputStream);

}
