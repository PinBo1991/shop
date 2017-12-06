package com.common.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.dao.DictParamUtil;

/**
 * http请求工具类
 */
@Component
public class HttpUtil {
	@Autowired

	private static Log logger = LogFactory.getLog(HttpUtil.class);// 日志工具

	@Autowired
	private DictParamUtil util; // 字典参数工具类

	/**
	 * http中get请求方法
	 */
	public String get(String url) {
		HttpClient client = HttpClients.createDefault();// 创建http访问的客户端
		String fullUrl = util.findValue("webshop_pc_url") + util.findValue("webshop_pc_app_root") + url;
		HttpGet get = new HttpGet(fullUrl);// 创建get请求
		HttpResponse response;
		String text = "请求失败";
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();// 从响应中取得一个响应实体
			text = EntityUtils.toString(entity);// 把响应实体转成文本信息
		} catch (ClientProtocolException e) {
			logger.error("httputil发送get请求出现ClientProtocolException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("httputil发送get请求出现IOException异常");
			e.printStackTrace();
		} finally {
			get.releaseConnection();// 释放连接
		}

		return text;
	}

	/**
	 * http中post请求方法
	 */
	public String post(String url, Map<String, String> map) {
		HttpPost post = null;
		String text = "请求失败";
		try {
			HttpClient client = HttpClients.createDefault();// 创建http访问的客户端
			List<NameValuePair> param = new ArrayList<>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			HttpEntity postEntity = new UrlEncodedFormEntity(param, "utf-8");// 用参数构造请求实体
			String fullUrl = util.findValue("webshop_pc_url") + util.findValue("webshop_pc_app_root") + url;
			post = new HttpPost(fullUrl);// 创建post请求
			post.setEntity(postEntity);// 把请求实体放到post请求中
			HttpResponse response = client.execute(post); // 用客户端去执行post请求
			HttpEntity entity = response.getEntity();// 从响应中取得一个响应实体
			text = EntityUtils.toString(entity);// 把响应实体转成文本信息
		} catch (ClientProtocolException e) {
			logger.error("httputil发送post请求出现ClientProtocolException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("httputil发送post请求出现IOException异常");
			e.printStackTrace();
		} finally {
			post.releaseConnection();// 释放连接
		}
		return text;
	}
}
