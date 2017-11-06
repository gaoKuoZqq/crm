package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.CusDevPlanMapper;
import com.crm.pojo.CusDevPlan;
import com.crm.pojo.CusDevPlanExample;
import com.crm.pojo.CusDevPlanExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ICusDevPlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CusDevPlanServiceImpl implements ICusDevPlanService{
	@Autowired
	private CusDevPlanMapper cusDevPlanMapper;

	@Override
	public EasyUIDataGrideResult findAll(CusDevPlan cusDevPlan) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CusDevPlanExample cusDevPlanExample = new CusDevPlanExample();
		Criteria criteria = cusDevPlanExample.createCriteria();
		criteria.andSaleChanceIdEqualTo(cusDevPlan.getSaleChanceId());
		List<CusDevPlan> cusDevPlanList = cusDevPlanMapper.selectByExample(cusDevPlanExample);
		result.setRows(cusDevPlanList);
		return result;
	}

	@Override
	public ServerResponse<?>  delete(Integer id) {
		cusDevPlanMapper.deleteByPrimaryKey(id);
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse<?>  add(CusDevPlan cusDevPlan) {
		if (cusDevPlanMapper.insert(cusDevPlan) > 0) {
			return ServerResponse.createSuccess("添加成功! ");
		}
		return ServerResponse.createError("添加失败!");
	}

	@Override
	public ServerResponse<?> update(CusDevPlan cusDevPlan) {
		if (cusDevPlanMapper.updateByPrimaryKey(cusDevPlan) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
		}
		return ServerResponse.createError("修改失败!");
	}

	@Override
	public List<CusDevPlan> findCusDevPlansBySaleChanceId(Integer saleChanceId) {
		CusDevPlanExample cusDevPlanExample = new CusDevPlanExample();
		Criteria criteria = cusDevPlanExample.createCriteria();
		criteria.andSaleChanceIdEqualTo(saleChanceId);
		return cusDevPlanMapper.selectByExample(cusDevPlanExample);
	}

}
