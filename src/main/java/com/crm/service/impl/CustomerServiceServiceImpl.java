package com.crm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CustomerServiceMapper;
import com.crm.pojo.CustomerService;
import com.crm.pojo.CustomerServiceExample;
import com.crm.pojo.CustomerServiceExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICustomerServiceService;
import com.crm.util.Util;
import com.github.pagehelper.util.StringUtil;
@Service
public class CustomerServiceServiceImpl implements ICustomerServiceService{
	@Autowired
	CustomerServiceMapper customerServiceMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, CustomerService customerService,String startTime,String endTime) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerServiceExample customerServiceExample = new CustomerServiceExample();
		Criteria criteria = customerServiceExample.createCriteria();
		if (customerService != null && customerService.getStatus() != null) {
			criteria.andStatusEqualTo(customerService.getStatus());
		}
		if (customerService != null && customerService.getId() != null) {
			criteria.andIdEqualTo(customerService.getId());
		}
		if (StringUtil.isNotEmpty(customerService.getCustomer())) {
			criteria.andCustomerLike(Util.LikeFormat(customerService.getCustomer()));
		}
		if (StringUtil.isNotEmpty(customerService.getServiceType())) {
			criteria.andServiceTypeLike(Util.LikeFormat(customerService.getServiceType()));
		}
		if (StringUtil.isNotEmpty(customerService.getServiceRequest())) {
			criteria.andServiceRequestLike(Util.LikeFormat(customerService.getServiceRequest()));
		}
		if (startTime != null && endTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date startTimeDate = null, endTimeDate = null;
			try {
				startTimeDate = sdf.parse(startTime);
				endTimeDate = sdf.parse(endTime);
			} catch (ParseException e) {
				System.out.println("日期转换出了问题");
				e.printStackTrace();
			}  
			criteria.andCreateTimeBetween(startTimeDate, endTimeDate);
		}
		List<CustomerService> customerServices = customerServiceMapper.selectByExample(customerServiceExample);
		result.setRows(customerServices);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerServiceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(CustomerService customerService) {
		customerService.setStatus("1");//新创建
		if (customerServiceMapper.insert(customerService) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(CustomerService customerService) {
		if (customerServiceMapper.updateByPrimaryKeySelective(customerService) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public ServerResponse<?> assign(String ids,String assigner, String assignTime) {
		String[] idArray = ids.split(",");
		CustomerService customerService = new CustomerService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date assignTimeDate = null;
		try {
			assignTimeDate = sdf.parse(assignTime);
		} catch (ParseException e) {
			System.out.println("日期转换出了问题");
			e.printStackTrace();
		} 
		if (assignTimeDate != null) {
			customerService.setAssignTime(assignTimeDate);
		}
		customerService.setStatus("2");//已分配
		for (String id : idArray) {
			customerService.setId(Integer.parseInt(id));
			customerService.setAssigner(assigner);
			customerServiceMapper.updateByPrimaryKeySelective(customerService);
		}
		return ServerResponse.createSuccess("分配成功");
	}

	@Override
	public List<Integer> findCountByTypes(List<String> serveTypes) {
		List<Integer> serveTypesCount = new ArrayList<Integer>();
		for (String serveType : serveTypes) {
			serveTypesCount.add(customerServiceMapper.findCountByType(serveType));
		}
		return serveTypesCount;
	}

}
