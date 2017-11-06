package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.DataDicMapper;
import com.crm.pojo.DataDic;
import com.crm.pojo.DataDicExample;
import com.crm.pojo.DataDicExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.IDataDicService;
import com.crm.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class DataDicServiceImpl implements IDataDicService{
	@Autowired
	DataDicMapper dataDicMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, DataDic dataDic) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		DataDicExample dataDicExample = new DataDicExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = dataDicExample.createCriteria();
		if (StringUtil.isNotEmpty(dataDic.getDataDicName())) {
			criteria.andDataDicNameLike(Util.LikeFormat(dataDic.getDataDicName()));
		}
		if (StringUtil.isNotEmpty(dataDic.getDataDicValue())) {
			criteria.andDataDicValueLike(Util.LikeFormat(dataDic.getDataDicValue()));
		}
		List<DataDic> dataDics = dataDicMapper.selectByExample(dataDicExample);
		PageInfo<DataDic> pageInfo = new PageInfo<>(dataDics);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(dataDics);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			dataDicMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(DataDic dataDic) {
		if (dataDicMapper.insert(dataDic) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(DataDic dataDic) {
		if (dataDicMapper.updateByPrimaryKey(dataDic) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public List<DataDic> findDataDicName() {
		return dataDicMapper.selectDistinct();
	}

	@Override
	public List<DataDic> getDataDicValueList(DataDic dataDic) {
		DataDicExample dataDicExample = new DataDicExample();
		Criteria criteria = dataDicExample.createCriteria();
		if (StringUtil.isNotEmpty(dataDic.getDataDicName())) {
			criteria.andDataDicNameEqualTo(dataDic.getDataDicName());
		}
		return dataDicMapper.selectByExample(dataDicExample);
	}

	@Override
	public List<String> findValueByName(String name) {
		return dataDicMapper.findValueByName(name);
	}

}
