package com.bjpowernode.cms.sys.param.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.dao.DictParamUtil;

@Component
@Aspect
public class ParamIntercepter {
	@Autowired
	private DictParamUtil util;// 字典参数工具类
	protected final Log logger = LogFactory.getLog(ParamIntercepter.class);// 日志类

	@After("within(com.bjpowernode.cms.sys.param.service.impl..*) && @annotation(initParam)")
	public void initParam(InitParam initParam) {
		util.initParam();// 调有参数初始化方法
		logger.info("参数初始化执行完成");
	}

}
