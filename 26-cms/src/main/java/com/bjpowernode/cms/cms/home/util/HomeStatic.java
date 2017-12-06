package com.bjpowernode.cms.cms.home.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjpowernode.cms.cms.home.service.IHomeService;
import com.common.dao.DictParamUtil;

@Component
@Aspect // 在这个类上启用切面技术
public class HomeStatic {
	private final Log logger = LogFactory.getLog(HomeStatic.class);// 日志类
	@Autowired
	private IHomeService service;// 注入首页service接口
	@Autowired
	private DictParamUtil util;// 字典参数工具类

	@After("within(com.bjpowernode.cms.cms..*) && @annotation(as)")
	public void runStatic(AutoStatic as) {
		if (util.findValue("autosatic").equals("1")) {
			service.runStatic();// 调用首页静态化方法
		}
		logger.info("执行首页静态化方法!!");
	}
}
