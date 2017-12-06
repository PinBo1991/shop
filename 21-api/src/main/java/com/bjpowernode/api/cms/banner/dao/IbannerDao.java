package com.bjpowernode.api.cms.banner.dao;

import java.util.List;

import com.bjpowernode.api.cms.banner.model.Banner;

public interface IbannerDao {

	List<Banner> queryList(Banner banner);

}
