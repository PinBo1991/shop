package com.bjpowernode.api.cms.advert.service;

import java.util.List;

import com.bjpowernode.api.cms.advert.model.Advert;

public interface IAdvertService {

	List<Advert> queryList(Advert advert);

}
