package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerOrderMapper;
import com.crm.pojo.CustomerOrder;
import com.crm.pojo.CustomerOrderExample;
import com.crm.pojo.CustomerOrderExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerOrderService;
@Service
public class CustomerOrderServiceImpl implements ICustomerOrderService{
	@Autowired
	CustomerOrderMapper customerOrderMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerOrder customerOrder) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerOrderExample customerOrderExample = new CustomerOrderExample();
		Criteria criteria = customerOrderExample.createCriteria();
		if (customerOrder != null && customerOrder.getCustomerId() != null) {
			criteria.andCustomerIdEqualTo(customerOrder.getCustomerId());
		}
/*		if (startTime != null && endTime != null) {
			criteria.andTimeBetween(startTime, endTime);
		}
		if (StringUtil.isNotEmpty(customerOrder.getAddress())) {
			criteria.andAddressLike(Util.LikeFormat(customerOrder.getAddress()));
		}
		if (StringUtil.isNotEmpty(customerOrder.getOverview())) {
			criteria.andOverviewLike(Util.LikeFormat(customerOrder.getOverview()));
		}*/
		List<CustomerOrder> customerOrders = customerOrderMapper.selectByExample(customerOrderExample);
		result.setRows(customerOrders);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerOrderMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(CustomerOrder customerOrder) {
		if (customerOrderMapper.insert(customerOrder) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(CustomerOrder customerOrder) {
		if (customerOrderMapper.updateByPrimaryKeySelective(customerOrder) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

}
