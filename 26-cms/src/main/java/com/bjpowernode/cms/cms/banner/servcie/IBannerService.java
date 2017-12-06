package com.bjpowernode.cms.cms.banner.servcie;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.banner.model.Banner;
import com.bjpowernode.cms.cms.banner.model.BannerCond;

/**
 * @功能描述：轮播图service接口类
 * @author gzz
 * @date : 2016-10-13
 */
public interface IBannerService {

	/**
	 * @功能描述：新增记录方法
	 */
	int insert(Banner banner,MultipartFile image);

	/**
	 * @功能描述：修改记录方法
	 */
	int update(Banner banner,MultipartFile image);

	/**
	 * @功能描述：删除多条记录
	 */
	int delete(String ids);

	/**
	 * @功能描述：查询单条轮播图记录
	 */
	Banner findById(int id);

	/**
	 * @功能描述：按条件查询分页列表
	 */
	void queryList(BannerCond cond, Map<String, Object> map);
	/**
	 * @功能描述：按条件查询记录个数
	 */
	int queryCount(BannerCond cond);
	
	/**
	 * @功能描述：修改轮播图状态
	 */
	int updateStatus(Banner banner);
}
