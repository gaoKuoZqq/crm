package com.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.SaleChanceMapper;
import com.crm.pojo.SaleChance;
import com.crm.pojo.SaleChanceExample;
import com.crm.pojo.SaleChanceExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.ISaleChanceService;
import com.crm.util.Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class SaleChanceImpl implements ISaleChanceService{
	@Autowired
	SaleChanceMapper saleChanceMapper;

	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	}
	
	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, SaleChance saleChance) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		SaleChanceExample saleChanceExample = new SaleChanceExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = saleChanceExample.createCriteria();
		if (saleChance != null && saleChance.getId() != null) {
			criteria.andIdEqualTo(saleChance.getId());
		}
		if (StringUtil.isNotEmpty(saleChance.getCustomerName())) {
			criteria.andCustomerNameLike(Util.LikeFormat(saleChance.getCustomerName()));
		}
		if (StringUtil.isNotEmpty(saleChance.getOverview())) {
			criteria.andOverviewLike(Util.LikeFormat(saleChance.getOverview()));
		}
		if (StringUtil.isNotEmpty(saleChance.getCreateMan())) {
			criteria.andCreateManLike(Util.LikeFormat(saleChance.getCreateMan()));
		}
		if (saleChance.getStatus() != null) {
			criteria.andStatusEqualTo(saleChance.getStatus());
		}
		if (saleChance.getDevResult() != null) {
			criteria.andDevResultEqualTo(saleChance.getDevResult());
		}
		List<SaleChance> saleChances = saleChanceMapper.selectByExample(saleChanceExample);
		PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChances);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(saleChances);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(SaleChance saleChance) {
		if (saleChance.getAssignMan() == null) {
			saleChance.setStatus(0);
		}else {
			saleChance.setStatus(1);
			saleChance.setDevResult(0);
		}
		if (saleChanceMapper.insert(saleChance) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(SaleChance saleChance) {
		if (saleChance.getAssignMan() == null) {
			saleChance.setStatus(0);
		}else {
			saleChance.setStatus(1);
			if (saleChance.getDevResult() == null) {
				saleChance.setDevResult(0);
			}
		}
		if (saleChanceMapper.updateByPrimaryKey(saleChance) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public SaleChance findById(Integer id) {
		return saleChanceMapper.selectByPrimaryKey(id);
	}

	@Override
	public ServerResponse<?> updateDevResult(SaleChance saleChance) {
		if (saleChanceMapper.updateByPrimaryKeySelective(saleChance) > 0) {
			return ServerResponse.createSuccess("操作成功");
		}else {
			return ServerResponse.createError("操作失败");
		}
	}

}
