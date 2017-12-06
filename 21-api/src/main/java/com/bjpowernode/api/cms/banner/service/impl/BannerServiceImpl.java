package com.bjpowernode.api.cms.banner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.api.cms.banner.dao.IbannerDao;
import com.bjpowernode.api.cms.banner.model.Banner;
import com.bjpowernode.api.cms.banner.service.IbannerService;

@Service
public class BannerServiceImpl implements IbannerService {
	@Autowired
	private IbannerDao dao;// 注入轮播图DAO接口

	@Override
	public List<Banner> queryList(Banner banner) {
		return dao.queryList(banner);
	}

}
