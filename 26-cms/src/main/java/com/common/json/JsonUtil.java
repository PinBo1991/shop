package com.common.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json操作工具类
 */
public class JsonUtil {
	private static Log logger = LogFactory.getLog(JsonUtil.class);// 日志工具

	/**
	 * json文本转成java对象
	 */
	public static <T> List<T> jsonToObj(String json, Class<T[]> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		T[] banner = null;
		try {
			banner = mapper.readValue(json, clazz);
		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("把json文本转成java对象时出现JsonMappingException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("把json文本转成java对象时出现IOException异常");
			e.printStackTrace();
		}
		List<T> list = Arrays.asList(banner);
		return list;
	}

}
