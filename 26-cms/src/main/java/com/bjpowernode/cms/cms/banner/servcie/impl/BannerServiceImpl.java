package com.bjpowernode.cms.cms.banner.servcie.impl;

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

import com.bjpowernode.cms.cms.banner.dao.IBannerDao;
import com.bjpowernode.cms.cms.banner.model.Banner;
import com.bjpowernode.cms.cms.banner.model.BannerCond;
import com.bjpowernode.cms.cms.banner.servcie.IBannerService;
import com.bjpowernode.cms.cms.home.util.AutoStatic;
import com.common.dao.DictParamUtil;
import com.common.redis.RedisUtil;
import com.common.util.FileUtil;

/**
 * @功能描述：轮播图service实现类
 * @author gzz
 * @date : 2016-10-13
 */
@Service
public class BannerServiceImpl implements IBannerService {
	private final Log logger = LogFactory.getLog(BannerServiceImpl.class);// 日志类
	@Autowired
	private DictParamUtil util;// 字典参数工具类
	@Autowired
	private IBannerDao dao;// 注入轮播图dao接口
	@Autowired
	private RedisUtil redisUtil;// redis工具类
	@Override
	@AutoStatic // 当这个方法执行结束之后要去执首页静态化
	public int insert(Banner banner, MultipartFile image) {
		if (!image.isEmpty()) {
			saveFile(banner, image);// 调用保存文件方法
		}
		banner.setTs(new Date());
		banner.setStatus(1);// 新增记录默认状态是可用
		clearRedis(banner.getType());// 调用清reids
		return dao.insert(banner);
	}
 
	private void clearRedis(int type) {/// 清reids
		redisUtil.clearRedis("banner_" + type);
	}
	/// 保存文件 的方法
	private void saveFile(Banner banner, MultipartFile image) {
		String oriName = image.getOriginalFilename();// 原始文件名
		String extName = oriName.substring(oriName.lastIndexOf("."));// 扩展名包扩.
		long longTime = new Date().getTime();// 取时间长整型
		String fileRoot = util.findValue("fileRoot");// 从数据取出文件取储根路径
		String banner_path = "banner/";// 指定轮播图存放路径
		String fullPath = fileRoot + banner_path + longTime + extName;// 文件存放的完整路径
		FileUtil.createDir(fullPath);// 创建上级目录
		banner.setPicture_path(fullPath);
		String fileUrl = util.findValue("fileUrl");// 从数据库取出文件展示前缀
		banner.setPicture_url(fileUrl + banner_path + longTime + extName);

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
		Banner banner = dao.findById(id);
		clearRedis(banner.getType());// 调用清reids
		if (banner.getPicture_path() != null && !banner.getPicture_path().equals("")) {
			new File(banner.getPicture_path()).delete();/// 删除文件
		}
	}

	@Override
	@AutoStatic // 当这个方法执行结束之后要去执首页静态化
	public int update(Banner banner, MultipartFile image) {
		if (!image.isEmpty()) {
			deleteFile(banner.getId());// 调用删除文件方法
			saveFile(banner, image);// 调用保存文件方法
		}
		banner.setTs(new Date());
		/// 如果终端类型允许修改 改前改后的终端都需要清
		clearRedis(1);// 调用清reids
		clearRedis(2);// 调用清reids
		clearRedis(3);// 调用清reids
		/// 如果终端类型不允许修改 只清当前类型就可以
		// clearRedis(banner.getType());// 调用清reids
		return dao.update(banner);
	}

	@Override
	@AutoStatic // 当这个方法执行结束之后要去执首页静态化
	public int delete(String ids) {
		String id[] = ids.split(",");
		for (String rid : id) {
			deleteFile(new Integer(rid));// 调用删除文件方法
		}

		return dao.delete(ids);
	}

	@Override
	public Banner findById(int id) {
		Banner banner = dao.findById(id);
		banner.setType_name(util.findDictValue(10, banner.getType()));/// 翻译终端类型的显示值
		banner.setStatus_name(util.findDictValue(11, banner.getStatus()));/// 翻译轮播图状态的显示值
		return banner;
	}

	@Override
	public void queryList(BannerCond cond, Map<String, Object> map) {
		dao.queryList(cond, map);
		@SuppressWarnings("unchecked")
		List<Banner> list = (List<Banner>) map.get("dataList");
		for (Banner banner : list) {
			banner.setType_name(util.findDictValue(10, banner.getType()));/// 翻译终端类型的显示值
			banner.setStatus_name(util.findDictValue(11, banner.getStatus()));/// 翻译轮播图状态的显示值
		}
	}

	@Override
	public int queryCount(BannerCond cond) {
		return dao.queryCount(cond);
	}

	@Override
	@AutoStatic // 当这个方法执行结束之后要去执首页静态化
	public int updateStatus(Banner banner) {
		clearRedis(banner.getType());// 调用清reids
		return dao.updateStatus(banner);
	}
}
