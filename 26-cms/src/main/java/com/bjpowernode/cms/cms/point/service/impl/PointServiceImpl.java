package com.bjpowernode.cms.cms.point.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.home.util.AutoStatic;
import com.bjpowernode.cms.cms.point.dao.IPointDao;
import com.bjpowernode.cms.cms.point.model.Point;
import com.bjpowernode.cms.cms.point.model.PointCond;
import com.bjpowernode.cms.cms.point.service.IPointService;
import com.common.dao.DictParamUtil;
import com.common.redis.RedisUtil;
import com.common.util.FileUtil;

/**
 * @类说明:卖点图Service实现类
 *
 * @author:gzz_gzz@163.com
 * @date:2016-10-18 14:48:24
 **/
@Service
public class PointServiceImpl implements IPointService {
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IPointDao dao; // 卖点图Dao

	@Autowired
	private DictParamUtil util;// 字典参数工具类
	@Autowired
	private RedisUtil redisUtil;// redis工具类

	/**
	 * @方法说明:新增记录
	 **/
	@Override
	@AutoStatic // 当这个方法执行完成后去执行首页静态化方法
	public int insert(Point point, MultipartFile image) {
		if (!image.isEmpty()) {
			saveFile(point, image);// 调用
		}
		point.setTs(new Date());
		point.setStatus(1);
		clearRedis(point.getType());//调用清楚redis
		return dao.insert(point);
	}

	/**
	 * @方法说明:保存文件
	 **/
	private void saveFile(Point point, MultipartFile image) {
		String oriName = image.getOriginalFilename();
		String extName = oriName.substring(oriName.lastIndexOf("."));
		String point_path = "point/";
		String fileRoot = util.findValue("fileRoot");
		long longTime = new Date().getTime();
		String fullPath = fileRoot + point_path + longTime + extName;
		FileUtil.createDir(fullPath);
		point.setPicture_path(fullPath);
		String fileUrl = util.findValue("fileUrl");
		String fullUrl = fileUrl + point_path + longTime + extName;
		point.setPicture_url(fullUrl);

		try {
			image.transferTo(new File(fullPath));
		} catch (IllegalStateException e) {
			logger.error("保存文件 时出现IllegalStateException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("保存文件 时出现IOException异常");
			e.printStackTrace();
		}

	}
	private void clearRedis(int type) {/// 清reids
		redisUtil.clearRedis("banner_" + type);
	}
	/**
	 * @方法说明:删除记录(多条)
	 **/
	@Override
	@AutoStatic // 当这个方法执行完成后去执行首页静态化方法
	public int delete(String id) {
		String id_arr[] = id.split(",");
		for (String id_val : id_arr) {
			deleteFile(new Integer(id_val));
		}
		return dao.delete(id);// 物理删除
	}

	/**
	 * @方法说明:删除文件
	 **/
	private void deleteFile(int id) {
		Point point = dao.findById(id);
		clearRedis(point.getType());//调用清楚redis
		if (point.getPicture_path() != null && !point.getPicture_path().equals("")) {
			new File(point.getPicture_path()).delete();
		}
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	@Override
	public Point findById(Integer id) {
		Point point = dao.findById(id);
		point.setType_text(util.findDictValue(10, point.getType()));// 翻译终端类型显示值
		point.setStatus_text(util.findDictValue(15, point.getStatus()));// 翻译卖点图状态显示值
		return point;
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Override
	@AutoStatic // 当这个方法执行完成后去执行首页静态化方法
	public int update(Point point, MultipartFile image) {
		if (!image.isEmpty()) {
			deleteFile(point.getId());
			saveFile(point, image);// 调用
		}
		point.setTs(new Date());
		clearRedis(1);//调用清楚redis
		clearRedis(2);//调用清楚redis
		clearRedis(3);//调用清楚redis
		return dao.update(point);
	}

	/**
	 * @方法说明:按条件查询分页列表
	 **/
	@Override
	public void queryList(PointCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
		@SuppressWarnings("unchecked")
		List<Point> list = (List<Point>) map.get("dataList");
		for (Point point : list) {
			point.setType_text(util.findDictValue(10, point.getType()));// 翻译终端类型显示值
			point.setStatus_text(util.findDictValue(15, point.getStatus()));// 翻译卖点图状态显示值
		}
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	@Override
	public int findCountByCond(PointCond cond) {
		return dao.findCountByCond(cond);
	}

	/**
	 * @方法说明:更新记录状态
	 **/
	@Override
	@AutoStatic // 当这个方法执行完成后去执行首页静态化方法
	public int updateStatus(Point point) {
		clearRedis(point.getType());//调用清楚redis
		return dao.updateStatus(point);
	}
}