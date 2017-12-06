package com.bjpowernode.cms.cms.home.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.cms.cms.home.service.IHomeService;
import com.common.dao.DictParamUtil;
import com.common.http.HttpUtil;
import com.common.util.FileUtil;

@Service
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private HttpUtil httpUtil;// http请求工具
	@Autowired
	private DictParamUtil util;// 字典参数工具类
	private final Log logger = LogFactory.getLog(HomeServiceImpl.class);// 日志类

	@Override
	public void runStatic() {
		String html = httpUtil.get("home");// 请求首页
		String webshop_pc_app_root = util.findValue("webshop_pc_app_root");// 从参数中取pc应用的根路径
		html = html.replaceAll(webshop_pc_app_root + "resources/", "http://static.power.com/resources/");
		String home_path = util.findValue("home_path");// 从参数中取首页文件保存的路径
		FileUtil.writeFile(home_path + "index.html", html);/// 往指定目录写文本文件
		logger.info("---------------");

	}

}
