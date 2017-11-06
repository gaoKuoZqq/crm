package com.crm.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerLossMapper;
import com.crm.pojo.CustomerLoss;
import com.crm.pojo.CustomerLossExample;
import com.crm.pojo.CustomerLossExample.Criteria;
import com.crm.pojo.CustomerLossMeasure;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerLossService;
import com.crm.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class CustomerLossServiceImpl implements ICustomerLossService{
	@Autowired
	CustomerLossMapper customerLossMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerLoss customerLoss) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerLossExample customerLossExample = new CustomerLossExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = customerLossExample.createCriteria();
		if (customerLoss != null && customerLoss.getId() != null) {
			criteria.andIdEqualTo(customerLoss.getId());
		}
		if (StringUtil.isNotEmpty(customerLoss.getCustomerName())) {
			criteria.andCustomerNameLike(Util.LikeFormat(customerLoss.getCustomerName()));
		}
		if (StringUtil.isNotEmpty(customerLoss.getCustomerManager())) {
			criteria.andCustomerManagerLike(Util.LikeFormat(customerLoss.getCustomerManager()));
		}
		if (customerLoss != null && customerLoss.getStatus() != null) {
			criteria.andStatusEqualTo(customerLoss.getStatus());
		}
		List<CustomerLoss> customerLosss = customerLossMapper.selectByExample(customerLossExample);
		PageInfo<CustomerLoss> pageInfo = new PageInfo<>(customerLosss);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerLosss);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerLossMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(CustomerLoss customerLoss) {
		if (customerLossMapper.insert(customerLoss) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(CustomerLoss customerLoss) {
		if (customerLossMapper.updateByPrimaryKeySelective(customerLoss) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public CustomerLoss findById(Integer id) {
		return customerLossMapper.selectByPrimaryKey(id);
	}

	@Override
	public ServerResponse<?> updateConfirmLoss(Integer id) {
		CustomerLoss customerLoss = new CustomerLoss();
		customerLoss.setId(id);
		customerLoss.setStatus(1);//确认流失
		if (customerLossMapper.updateByPrimaryKeySelective(customerLoss) > 0) {
			return ServerResponse.createSuccess("操作成功");
		}else {
			return ServerResponse.createError("下机");
		}
	}

	@Override
	public void customerAutoLoss() {
		List<CustomerLoss> customerLosses = customerLossMapper.findCustomerAutoLossList();
		Set<String> set = new HashSet<>();
		for (int i = 0,j = 0;i < (customerLosses.size() - j);i++) {
			if (!set.contains(customerLosses.get(i).getCustomerNo())) {
				set.add(customerLosses.get(i).getCustomerNo());
			}else{
				customerLosses.remove(i);
				j = j + 1;
			}
		}
		for (CustomerLoss customerLoss : customerLosses) {
			customerLoss.setStatus(0);
			customerLoss.setLossReason("半年未下单");
			if (customerLossMapper.addAutoLossCustomer(customerLoss) > 0) {
				System.out.println(customerLoss.getCustomerName() + "已暂缓流失");
			}
		}
	}


}
