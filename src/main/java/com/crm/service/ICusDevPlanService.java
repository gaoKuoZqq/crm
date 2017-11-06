package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.CusDevPlan;
import com.crm.responce.ServerResponse;

public interface ICusDevPlanService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page 当前页
	 * @param rows 一页多少数据
	 * @return
	 */
	EasyUIDataGrideResult findAll(CusDevPlan cusDevPlan);

	ServerResponse<?> delete(Integer id);

	ServerResponse<?> add(CusDevPlan cusDevPlan);

	ServerResponse<?> update(CusDevPlan cusDevPlan);

	List<CusDevPlan> findCusDevPlansBySaleChanceId(Integer saleChanceId);
}
