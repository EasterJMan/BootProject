package com.itheima.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itheima.domain.User;
import com.itheima.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String usercode, String password,Model model,HttpSession session) {
		//通过账号和密码查询用户
		User user = userService.findUser(usercode, password);
		if(user !=null) {
			//将用户对象添加到session
			session.setAttribute("USER_SESSION", user);
			return "redirect:customer/list.action";
		}
		
		model.addAttribute("msg", "账号或密码错误，请重新输入！");
		return "login";
	}
	
	//验证是否成功拦截未登录用户
	@RequestMapping(value = "/toCustomer.action")
	public String toCustomer() {
		return "customer";
	}
	
	//退出登录
	@RequestMapping(value = "/logout.action")
	public String logout(HttpSession session) {
		//清除Session
		session.invalidate();
		//重定向到登录页面的跳转方法
		return "redirect:login.action";
	}
	
	//向用户登录页面跳转
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String tologin() {
		return "login";
	}
	
}
