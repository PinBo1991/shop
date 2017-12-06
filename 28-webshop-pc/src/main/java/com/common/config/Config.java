package com.common.config;

public class Config {

	private String api_url;// 数据接口前缀

	private String redis_host;// redis主机名或IP

	private Integer redis_port;// redis端口号

	public String getRedis_host() {
		return redis_host;
	}

	public void setRedis_host(String redis_host) {
		this.redis_host = redis_host;
	}

	public Integer getRedis_port() {
		return redis_port;
	}

	public void setRedis_port(Integer redis_port) {
		this.redis_port = redis_port;
	}

	public String getApi_url() {
		return api_url;
	}

	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}

}
