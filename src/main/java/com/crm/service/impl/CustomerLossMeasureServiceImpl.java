package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerLossMeasureMapper;
import com.crm.pojo.CustomerLossMeasure;
import com.crm.pojo.CustomerLossMeasureExample;
import com.crm.pojo.CustomerLossMeasureExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerLossMeasureService;
import com.crm.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class CustomerLossMeasureServiceImpl implements ICustomerLossMeasureService{
	@Autowired
	CustomerLossMeasureMapper customerLossMeasureMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLossMeasure customerLossMeasure) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerLossMeasureExample customerLossMeasureExample = new CustomerLossMeasureExample();
		Criteria criteria = customerLossMeasureExample.createCriteria();
		if (customerLossMeasure != null && customerLossMeasure.getLossId() != null) {
			criteria.andLossIdEqualTo(customerLossMeasure.getLossId());
		}
		List<CustomerLossMeasure> customerLossMeasures = customerLossMeasureMapper.selectByExample(customerLossMeasureExample);
		result.setRows(customerLossMeasures);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String id) {
		customerLossMeasureMapper.deleteByPrimaryKey(Integer.parseInt(id));
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(CustomerLossMeasure customerLossMeasure) {
		if (customerLossMeasureMapper.insert(customerLossMeasure) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(CustomerLossMeasure customerLossMeasure) {
		if (customerLossMeasureMapper.updateByPrimaryKey(customerLossMeasure) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}


}
