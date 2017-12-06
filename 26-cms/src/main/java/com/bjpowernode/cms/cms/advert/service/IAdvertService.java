package com.bjpowernode.cms.cms.advert.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.advert.model.Advert;
import com.bjpowernode.cms.cms.advert.model.AdvertCond;

/**
 * @功能描述：广告位的Service接口
 * @author gzz
 * @date : 2016-10-17
 */
public interface IAdvertService {
	/**
	 * @功能描述：新增广告位
	 */
	int insert(Advert advert, MultipartFile image);

	/**
	 * @功能描述：修改广告位
	 */
	int update(Advert advert, MultipartFile image);

	/**
	 * @功能描述：删除广告位 (多条)
	 */
	int delete(Integer[] ids);

	/**
	 * @功能描述：按主键查找单个广告位
	 */
	Advert findById(Integer id);

	/**
	 * @功能描述：按条件查询广告位 分页列表
	 */
	void queryList(AdvertCond cond, Map<String, Object> map);

	/**
	 * @功能描述：按条件查询记录个数
	 */
	int queryCount(AdvertCond cond);
	
	/**
	 * @功能描述：修改广告位状态
	 */
	int updateStatus(Advert advert);
}
