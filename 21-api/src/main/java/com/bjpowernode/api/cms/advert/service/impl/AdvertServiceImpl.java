package com.bjpowernode.api.cms.advert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjpowernode.api.cms.advert.dao.IAdvertDao;
import com.bjpowernode.api.cms.advert.model.Advert;
import com.bjpowernode.api.cms.advert.service.IAdvertService;

@Service
public class AdvertServiceImpl implements IAdvertService {

	@Autowired
	private IAdvertDao dao;// 注入广告位的dao接口

	@Override
	public List<Advert> queryList(Advert advert) {
		return dao.queryList(advert);
	}

}
