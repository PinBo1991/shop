package com.common.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.dao.DictParamUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Component
public class RedisUtil {
	@Autowired
	private DictParamUtil util;// 字典参数工具类

	private Log logger = LogFactory.getLog(getClass());

	public void clearRedis(String key) {
		Jedis jedis = null;
		try {
			jedis = new Jedis(util.findValue("redis_host"), new Integer(util.findValue("redis_port")));// 建连接
			jedis.del(key);// 到redis 中取轮播图数据
		} catch (JedisConnectionException e) {
			logger.error("redis连接异常");
		} finally {
			jedis.close();// 关闭连接
		}
	}
}
