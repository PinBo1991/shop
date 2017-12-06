package com.bjpowernode.api.cms.advert.dao;

import java.util.List;

import com.bjpowernode.api.cms.advert.model.Advert;

public interface IAdvertDao {

	List<Advert> queryList(Advert advert);

}
