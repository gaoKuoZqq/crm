package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.ProductMapper;
import com.crm.pojo.Product;
import com.crm.pojo.ProductExample;
import com.crm.pojo.ProductExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.IProductService;
import com.crm.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class ProductServiceImpl implements IProductService{
	@Autowired
	ProductMapper productMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, Product product) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		ProductExample productExample = new ProductExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = productExample.createCriteria();
		if (product != null && product.getId() != null) {
			criteria.andIdEqualTo(product.getId());
		}
		if (StringUtil.isNotEmpty(product.getName())) {
			criteria.andNameLike(Util.LikeFormat(product.getName()));
		}
/*		if (StringUtil.isNotEmpty(product.getTrueName())) {
			criteria.andTrueNameLike(Util.LikeFormat(product.getTrueName()));
		}
		if (StringUtil.isNotEmpty(product.getRoleName())) {
			criteria.andRoleNameLike(Util.LikeFormat(product.getRoleName()));
		}
		if (StringUtil.isNotEmpty(product.getEmail())) {
			criteria.andEmailLike(Util.LikeFormat(product.getEmail()));
		}
		if (StringUtil.isNotEmpty(product.getPhone())) {
			criteria.andPhoneLike(Util.LikeFormat(product.getPhone()));
		}*/
		List<Product> products = productMapper.selectByExample(productExample);
		PageInfo<Product> pageInfo = new PageInfo<>(products);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(products);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			productMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(Product product) {
		if (productMapper.insert(product) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(Product product) {
		if (productMapper.updateByPrimaryKey(product) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}


}
