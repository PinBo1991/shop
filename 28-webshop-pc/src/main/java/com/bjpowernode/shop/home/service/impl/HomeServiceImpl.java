package com.bjpowernode.shop.home.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.shop.home.model.Advert;
import com.bjpowernode.shop.home.model.Banner;
import com.bjpowernode.shop.home.model.Point;
import com.bjpowernode.shop.home.service.IHomeService;
import com.common.config.Config;
import com.common.http.HttpUtil;
import com.common.json.JsonUtil;

import redis.clients.jedis.Jedis;

@Service
public class HomeServiceImpl implements IHomeService {
	@Autowired
	private HttpUtil httpUtil;// http请求工具
	private static Log logger = LogFactory.getLog(HomeServiceImpl.class);// 日志工具
	@Autowired
	private Config config;// 注入配置信息

	@Override
	/**
	 * 查询轮播图信息
	 */
	public List<Banner> queryBanners() {
		String json;
		Jedis jedis = new Jedis(config.getRedis_host(), config.getRedis_port());// 建连接
		String banner_key = "banner_1";
		json = jedis.get(banner_key);// 到redis 中取轮播图数据
		if (json == null || json.equals("")) {
			json = httpUtil.get("banner/list?type=1");// 利用工具到api请求轮播图数据
			jedis.set(banner_key, json);
			logger.info("从api取轮播图信息");
		}
		jedis.close();// 关闭连接
		List<Banner> list = JsonUtil.jsonToObj(json, Banner[].class);// 利用工具把json转java对像
		return list;
	}

	/**
	 * 查询 广告位信息
	 */
	@Override
	public List<Advert> queryAdverts() {
		String json;
		Jedis jedis = new Jedis(config.getRedis_host(), config.getRedis_port());// 建连接
		String advert_key = "advert_1";
		json = jedis.get(advert_key);// 到redis 中取广告位数据
		if (json == null || json.equals("")) {
			json = httpUtil.get("advert/list?type=1");// 利用工具到api请求广告位数据
			jedis.set(advert_key, json);
			logger.info("从api取广告位信息");
		}
		jedis.close();// 关闭连接
		List<Advert> list = JsonUtil.jsonToObj(json, Advert[].class);// 利用工具把json转java对像
		return list;
	}

	@Override
	public List<Point> queryPoints() {
		String json;
		Jedis jedis = new Jedis(config.getRedis_host(), config.getRedis_port());
		String point_key = "point_1";
		json = jedis.get(point_key);
		if (json == null || !json.equals("")) {
			json = httpUtil.get("point/list?type=1");
			jedis.set(point_key, json);
		}
		jedis.close();// 关闭连接
		List<Point> list = JsonUtil.jsonToObj(json, Point[].class);
		return list;
	}

}
