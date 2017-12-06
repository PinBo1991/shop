package com.bjpowernode.shop.home.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjpowernode.shop.home.service.IHomeService;

@Controller
public class HomeAction {

	@Autowired
	private IHomeService service;

	@RequestMapping("home")
	public String toHome(Map<String, Object> map) {
		map.put("bannerList", service.queryBanners()); // 查询轮播图信息
		map.put("advertList", service.queryAdverts()); // 查询广告位信息
		map.put("pointList", service.queryPoints()); // 查询卖点图信息
		return "home/index";
	}

}
