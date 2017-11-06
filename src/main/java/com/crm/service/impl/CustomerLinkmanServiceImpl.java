package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerLinkmanMapper;
import com.crm.pojo.CustomerLinkman;
import com.crm.pojo.CustomerLinkmanExample;
import com.crm.pojo.CustomerLinkmanExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerLinkmanService;
import com.crm.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class CustomerLinkmanServiceImpl implements ICustomerLinkmanService{
	@Autowired
	CustomerLinkmanMapper customerLinkmanMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLinkman customerLinkman) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerLinkmanExample customerLinkmanExample = new CustomerLinkmanExample();
		Criteria criteria = customerLinkmanExample.createCriteria();
		if (customerLinkman != null && customerLinkman.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customerLinkman.getCustomerId());
		}
		if (customerLinkman != null && customerLinkman.getId() != null) {
			criteria.andIdEqualTo(customerLinkman.getId());
		}
		if (StringUtil.isNotEmpty(customerLinkman.getLinkName())) {
			criteria.andLinkNameEqualTo(Util.LikeFormat(customerLinkman.getLinkName()));
		}
		if (StringUtil.isNotEmpty(customerLinkman.getGender())) {
			criteria.andGenderEqualTo(customerLinkman.getGender());
		}
		if (StringUtil.isNotEmpty(customerLinkman.getPosition())) {
			criteria.andPositionLike(Util.LikeFormat(customerLinkman.getPosition()));
		}
		if (StringUtil.isNotEmpty(customerLinkman.getOfficePhone())) {
			criteria.andOfficePhoneLike(Util.LikeFormat(customerLinkman.getOfficePhone()));
		}
		if (StringUtil.isNotEmpty(customerLinkman.getPhone())) {
			criteria.andPhoneLike(Util.LikeFormat(customerLinkman.getPhone()));
		}
		List<CustomerLinkman> customerLinkmans = customerLinkmanMapper.selectByExample(customerLinkmanExample);
		result.setRows(customerLinkmans);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerLinkmanMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(CustomerLinkman customerLinkman) {
		if (customerLinkmanMapper.insert(customerLinkman) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(CustomerLinkman customerLinkman) {
		if (customerLinkmanMapper.updateByPrimaryKeySelective(customerLinkman) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

}
