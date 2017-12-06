package com.bjpowernode.cms.cms.home.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjpowernode.cms.cms.home.service.IHomeService;

@Controller
@RequestMapping("home")
public class HomeAction {
	@Autowired
	private IHomeService service;

	@RequestMapping("toStatic")
	// 跳转到执行静态化的页面
	public String toStatic() {
		return "cms/home/static";
	}

	@RequestMapping("runStatic")
	// 执行静态化操作
	public String runStatic() {
		service.runStatic();// 执行
		return "cms/home/static";
	}

}
