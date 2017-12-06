package com.bjpowernode.cms.cms.point.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.point.model.Point;
import com.bjpowernode.cms.cms.point.model.PointCond;

/**
 * @类说明:卖点图Service接口类
 *
 * @author:gzz_gzz@163.com
 * @date:2016-10-18 14:48:24
 **/
public interface IPointService {

	/**
	 * @方法说明:新增记录
	 **/
	int insert(Point point,MultipartFile image);

	/**
	 * @方法说明:删除记录(多条)
	 **/
	int delete(String id);

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	Point findById(Integer id);

	/**
	 * @方法说明:更新记录
	 **/
	int update(Point point,MultipartFile image);

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	void queryList(PointCond cond, Map<String, Object> map);

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	int findCountByCond(PointCond cond);
	
	/**
	 * @方法说明:更新记录状态
	 **/
	int updateStatus(Point point);

}