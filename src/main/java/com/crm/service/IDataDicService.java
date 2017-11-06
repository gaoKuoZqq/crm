package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.DataDic;
import com.crm.responce.ServerResponse;

public interface IDataDicService {

	EasyUIDataGrideResult find(Integer page, Integer rows, DataDic dataDic);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(DataDic dataDic);

	ServerResponse<?> update(DataDic dataDic);

	List<DataDic> findDataDicName();

	List<DataDic> getDataDicValueList(DataDic dataDic);

	List<String> findValueByName(String string);

}
