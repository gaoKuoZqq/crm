package com.crm.service;

import java.util.List;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.User;
import com.crm.responce.ServerResponse;

public interface IUserService {

	EasyUIDataGrideResult find(Integer page, Integer rows, User user);

	ServerResponse<?> delete(String ids);

	ServerResponse<?> add(User user);

	ServerResponse<?> update(User user);

	List<User> getTrueNameList();

	boolean checkLogin(User user);

	ServerResponse<?> modifyPassword(String name, String newPassword);

}
