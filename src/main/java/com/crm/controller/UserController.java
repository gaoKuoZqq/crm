package com.crm.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.easyui.EasyUIDataGrideResult;
import com.crm.pojo.User;
import com.crm.responce.ServerResponse;
import com.crm.service.IUserService;

import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.util.AddAliasesVisitor;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	IUserService userService;
	
	@RequestMapping("index")
	public String index(){
		return "user";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public EasyUIDataGrideResult find(Integer page, Integer rows, User user) {
		return userService.find(page,rows,user);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse<?> delete(String ids){
		return userService.delete(ids);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ServerResponse<?> add(User user){
		return userService.add(user);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ServerResponse<?> update(User user){
		return userService.update(user);
	}
	
	@RequestMapping("getTrueNameList")
	@ResponseBody
	public List<User> findCustomerManagerList(){
		return userService.getTrueNameList();
	}
	
	@RequestMapping("checkLogin")
	@ResponseBody
	public ServerResponse<?> checkLogin(User user,HttpServletRequest request){
		if (userService.checkLogin(user)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			return ServerResponse.createSuccess();
		}
		return ServerResponse.createError("用户名或密码错误");
	}
	
	@RequestMapping("logOut")
	@ResponseBody
	public ServerResponse<?> logout(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		session.setAttribute("user", null);
		return ServerResponse.createSuccess("已退出登录");
	}
	
	@RequestMapping("goLogin")
	public String goLogin(){
		return "login";
	}
	
	@RequestMapping("modifyPassword")
	@ResponseBody
	public ServerResponse<?> modifyPassword(String oldPassword, String newPassword, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if ( user != null && oldPassword != null && oldPassword.equals(user.getPassword())) {
			return userService.modifyPassword(user.getName(),newPassword);
		}else{
			return ServerResponse.createError("密码错误");
		}
		
	}
}
