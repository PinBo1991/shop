package com.bjpowernode.api.cms.banner.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjpowernode.api.cms.banner.model.Banner;
import com.bjpowernode.api.cms.banner.service.IbannerService;

@Controller
@RequestMapping("banner")
public class BannerAction {

	@Autowired
	private IbannerService service;

	@RequestMapping("list")
	@ResponseBody
	public List<Banner> queryList(Banner banner) {
		return service.queryList(banner);
	}

}
