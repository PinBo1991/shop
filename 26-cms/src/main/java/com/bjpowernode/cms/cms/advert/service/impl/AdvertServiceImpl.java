package com.bjpowernode.cms.cms.advert.service.impl;

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

import com.bjpowernode.cms.cms.advert.dao.IAdvertDao;
import com.bjpowernode.cms.cms.advert.model.Advert;
import com.bjpowernode.cms.cms.advert.model.AdvertCond;
import com.bjpowernode.cms.cms.advert.service.IAdvertService;
import com.bjpowernode.cms.cms.home.util.AutoStatic;
import com.common.dao.DictParamUtil;
import com.common.redis.RedisUtil;
import com.common.util.FileUtil;

/**
 * @功能描述：广告位的Service实现类
 * @author gzz
 * @date : 2016-10-17
 */
@Service
public class AdvertServiceImpl implements IAdvertService {
	private final Log logger = LogFactory.getLog(AdvertServiceImpl.class);// 日志类

	@Autowired
	private IAdvertDao dao;// 注入广告位dao接口
	@Autowired
	private DictParamUtil util;// 字典参数工具类
	@Autowired
	private RedisUtil redisUtil;// redis工具类

	@Override
	@AutoStatic // 有这个注解的方法被 执行的时间要去做首页静态化
	public int insert(Advert advert, MultipartFile image) {
		advert.setTs(new Date());
		advert.setStatus(1);
		if (!image.isEmpty()) {
			saveFile(advert, image);
		}
		clearRedis(advert.getType());// 调用清缓存方法
		return dao.insert(advert);
	}

	/// 保存文件 的方法
	private void saveFile(Advert advert, MultipartFile image) {
		String oriName = image.getOriginalFilename();// 原始文件名
		String extName = oriName.substring(oriName.lastIndexOf("."));// 扩展名包扩.
		long longTime = new Date().getTime();// 取时间长整型
		String fileRoot = util.findValue("fileRoot");// 从数据取出文件取储根路径
		String advert_path = "advert/";// 指定轮播图存放路径
		String fullPath = fileRoot + advert_path + longTime + extName;// 文件存放的完整路径
		FileUtil.createDir(fullPath);// 创建上级目录
		advert.setPicture_path(fullPath);
		String fileUrl = util.findValue("fileUrl");// 从数据库取出文件展示前缀
		advert.setPicture_url(fileUrl + advert_path + longTime + extName);

		try {
			image.transferTo(new File(fullPath));
		} catch (IllegalStateException e) {
			logger.info("在保存文件时出现IllegalStateException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("在保存文件时出现IOException异常");
			e.printStackTrace();
		}
	}

	/// 删除文件
	private void deleteFile(Integer id) {
		Advert advert = dao.findById(id);
		clearRedis(advert.getType());// 调用清reids
		if (advert.getPicture_path() != null && !advert.getPicture_path().equals("")) {
			new File(advert.getPicture_path()).delete();/// 删除文件
		}
	}

	private void clearRedis(int type) {/// 清reids
		redisUtil.clearRedis("advert_" + type);
	}

	@Override
	@AutoStatic // 有这个注解的方法被 执行的时间要去做首页静态化
	public int update(Advert advert, MultipartFile image) {
		if (!image.isEmpty()) {
			deleteFile(advert.getId());// 调用删除文件
			saveFile(advert, image);// 调用保存文件
		}
		advert.setTs(new Date());
		clearRedis(advert.getType());// 调用清缓存方法
		return dao.update(advert);
	}

	@Override
	@AutoStatic // 有这个注解的方法被 执行的时间要去做首页静态化
	public int delete(Integer[] ids) {
		for (Integer id : ids) {
			deleteFile(id);// 调用删除文件
		}
		return dao.delete(ids);
	}

	@Override
	public Advert findById(Integer id) {
		Advert advert = dao.findById(id);
		advert.setStatus_name(util.findDictValue(13, advert.getStatus()));// 翻译广告位状态
		advert.setType_name(util.findDictValue(10, advert.getType()));// 翻译广告位终端类型
		return advert;
	}

	@Override
	public void queryList(AdvertCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
		@SuppressWarnings("unchecked")
		List<Advert> list = (List<Advert>) map.get("dataList");
		for (Advert advert : list) {
			advert.setStatus_name(util.findDictValue(13, advert.getStatus()));// 翻译广告位状态
			advert.setType_name(util.findDictValue(10, advert.getType()));// 翻译广告位终端类型
		}

	}

	@Override
	public int queryCount(AdvertCond cond) {
		return dao.queryCount(cond);
	}

	@Override
	@AutoStatic // 有这个注解的方法被 执行的时间要去做首页静态化
	public int updateStatus(Advert advert) {
		clearRedis(advert.getType());// 调用清reids
		return dao.updateStatus(advert);
	}

}
