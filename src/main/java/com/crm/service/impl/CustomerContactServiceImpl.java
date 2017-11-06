package com.crm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerContactMapper;
import com.crm.pojo.CustomerContact;
import com.crm.pojo.CustomerContactExample;
import com.crm.pojo.CustomerContactExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerContactService;
import com.crm.util.Util;
import com.github.pagehelper.util.StringUtil;
@Service
public class CustomerContactServiceImpl implements ICustomerContactService{
	@Autowired
	CustomerContactMapper customerContactMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerContact customerContact,Date startTime,Date endTime) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerContactExample customerContactExample = new CustomerContactExample();
		Criteria criteria = customerContactExample.createCriteria();
		if (customerContact != null && customerContact.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customerContact.getCustomerId());
		}
		if (customerContact != null && customerContact.getId() != null) {
			criteria.andIdEqualTo(customerContact.getId());
		}
		if (startTime != null && endTime != null) {
			criteria.andTimeBetween(startTime, endTime);
		}
		if (StringUtil.isNotEmpty(customerContact.getAddress())) {
			criteria.andAddressLike(Util.LikeFormat(customerContact.getAddress()));
		}
		if (StringUtil.isNotEmpty(customerContact.getOverview())) {
			criteria.andOverviewLike(Util.LikeFormat(customerContact.getOverview()));
		}
		List<CustomerContact> customerContacts = customerContactMapper.selectByExample(customerContactExample);
		result.setRows(customerContacts);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerContactMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(CustomerContact customerContact) {
		if (customerContactMapper.insert(customerContact) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(CustomerContact customerContact) {
		if (customerContactMapper.updateByPrimaryKeySelective(customerContact) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

}
