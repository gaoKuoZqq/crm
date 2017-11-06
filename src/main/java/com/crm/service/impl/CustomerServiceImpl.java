package com.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerMapper;
import com.crm.pojo.Customer;
import com.crm.pojo.CustomerExample;
import com.crm.pojo.CustomerExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerService;
import com.crm.util.Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	CustomerMapper customerMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, Customer customer) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerExample customerExample = new CustomerExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = customerExample.createCriteria();
		if (customer != null && customer.getId() != null) {
			criteria.andIdEqualTo(customer.getId());
		}
		if (StringUtil.isNotEmpty(customer.getName())) {
			criteria.andNameLike(Util.LikeFormat(customer.getName()));
		}
		List<Customer> customers = customerMapper.selectByExample(customerExample);
		PageInfo<Customer> pageInfo = new PageInfo<>(customers);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customers);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(Customer customer) {
		if (customer != null) {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String num = "KH" + dateFormat.format(date);
			customer.setNum(num);
		}
		if (customerMapper.insert(customer) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(Customer customer) {
		if (customerMapper.updateByPrimaryKeySelective(customer) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

}
