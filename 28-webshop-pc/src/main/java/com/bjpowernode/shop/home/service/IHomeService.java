package com.bjpowernode.shop.home.service;

import java.util.List;

import com.bjpowernode.shop.home.model.Advert;
import com.bjpowernode.shop.home.model.Banner;
import com.bjpowernode.shop.home.model.Point;

public interface IHomeService {

	// 查询轮播图信息
	List<Banner> queryBanners();
	
	// 查询广告位信息
	List<Advert> queryAdverts();
	
	// 查询卖点图信息
	List<Point> queryPoints();

}
