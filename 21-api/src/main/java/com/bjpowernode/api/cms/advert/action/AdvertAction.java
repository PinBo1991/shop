package com.bjpowernode.api.cms.advert.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjpowernode.api.cms.advert.model.Advert;
import com.bjpowernode.api.cms.advert.service.IAdvertService;

@Controller
@RequestMapping("advert")
public class AdvertAction {

	@Autowired
	private IAdvertService service;// 注入广告位service接口

	@RequestMapping("list")
	@ResponseBody
	public List<Advert> queryList(Advert advert) {
		return service.queryList(advert);
	}

}
