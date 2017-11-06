package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.mapper.UserMapper;
import com.crm.pojo.User;
import com.crm.pojo.UserExample;
import com.crm.pojo.UserExample.Criteria;
import com.crm.responce.ServerResponse;
import com.crm.service.IUserService;
import com.crm.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	UserMapper userMapper;

	@Override
	public EasyUIDataGrideResult find(Integer page, Integer rows, User user) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		PageHelper.startPage(page, rows);
		Criteria criteria = userExample.createCriteria();
		if (user != null && user.getId() != null) {
			criteria.andIdEqualTo(user.getId());
		}
		if (StringUtil.isNotEmpty(user.getName())) {
			criteria.andNameLike(Util.LikeFormat(user.getName()));
		}
		if (StringUtil.isNotEmpty(user.getTrueName())) {
			criteria.andTrueNameLike(Util.LikeFormat(user.getTrueName()));
		}
		if (StringUtil.isNotEmpty(user.getRoleName())) {
			criteria.andRoleNameLike(Util.LikeFormat(user.getRoleName()));
		}
		if (StringUtil.isNotEmpty(user.getEmail())) {
			criteria.andEmailLike(Util.LikeFormat(user.getEmail()));
		}
		if (StringUtil.isNotEmpty(user.getPhone())) {
			criteria.andPhoneLike(Util.LikeFormat(user.getPhone()));
		}
		List<User> users = userMapper.selectByExample(userExample);
		PageInfo<User> pageInfo = new PageInfo<>(users);
		int total = (int) pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(users);
		return result;
	}

	@Override
	public ServerResponse<?> delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("删除成功");
	}

	@Override
	public ServerResponse<?> add(User user) {
		if (userMapper.insert(user) > 0) {
			return ServerResponse.createSuccess("添加成功");
		}else {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse<?> update(User user) {
		if (userMapper.updateByPrimaryKey(user) > 0) {
			return ServerResponse.createSuccess("更新成功");
		}else {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public List<User> getTrueNameList() {
		List<User> users = userMapper.DistinctTrueNameByRoleName("客户经理");
		return users;
	}

	@Override
	public boolean checkLogin(User user) {
		if (StringUtil.isEmpty(user.getName()) || StringUtil.isEmpty(user.getPassword())) {
			return false;
		}
		return userMapper.checkLogin(user) > 0;
	}

	@Override
	public ServerResponse<?> modifyPassword(String name, String newPassword) {
		User user = new User();
		user.setName(name);
		user.setPassword(newPassword);
		if (userMapper.updatePasswordByName(user) > 0) {
			return ServerResponse.createSuccess("密码已经更改");
		}else {
			return ServerResponse.createError("无法预知的错误,重新登录后重试");
		}
	}

}
