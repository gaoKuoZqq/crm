package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.OrderItemMapper;
import com.crm.pojo.OrderItem;
import com.crm.pojo.OrderItemExample;
import com.crm.pojo.OrderItemExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.IOrderItemService;
@Service
public class Order_itemServiceImpl implements IOrderItemService{
	@Autowired
	OrderItemMapper orderItemMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, OrderItem orderItem) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		OrderItemExample orderItemExample = new OrderItemExample();
		Criteria criteria = orderItemExample.createCriteria();
/*		if (startTime != null && endTime != null) {
			criteria.andTimeBetween(startTime, endTime);
		}
		if (StringUtil.isNotEmpty(orderItem.getAddress())) {
			criteria.andAddressLike(Util.LikeFormat(orderItem.getAddress()));
		}
		if (StringUtil.isNotEmpty(orderItem.getOverview())) {
			criteria.andOverviewLike(Util.LikeFormat(orderItem.getOverview()));
		}*/
		List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
		result.setRows(orderItems);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			orderItemMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(OrderItem orderItem) {
		if (orderItemMapper.insert(orderItem) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(OrderItem orderItem) {
		if (orderItemMapper.updateByPrimaryKeySelective(orderItem) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

}
