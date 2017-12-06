package com.bjpowernode.cms.cms.banner.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.banner.model.Banner;
import com.bjpowernode.cms.cms.banner.model.BannerCond;
import com.bjpowernode.cms.cms.banner.servcie.IBannerService;
import com.common.dao.DictParamUtil;

/**
 * @功能描述：轮播图控制器类
 * @author gzz
 * @date : 2016-10-13
 */
@Controller
@RequestMapping("banner")
public class BannerAction {

	@Autowired
	private IBannerService service;// 轮播图service接口

	@Autowired
	private DictParamUtil util;// 字典参数工具类

	/**
	 * @功能描述：转到列表页面
	 */
	@RequestMapping("list")
	public String queryList(@ModelAttribute("cond") BannerCond cond, Map<String, Object> map) {
		map.put("typeMap", util.getDictMap(10, true));// 生成终端类型的下拉列表
		map.put("statusMap", util.getDictMap(11, true));// 生成终端类型的下拉列表
		service.queryList(cond, map);
		return "cms/banner/list";
	}

	/**
	 * @功能描述：转到新增页面
	 */
	@RequestMapping("toinsert")
	public String toinsert(@ModelAttribute("banner") Banner banner, Map<String, Object> map) {
		map.put("typeMap", util.getDictMap(10, false));// 生成终端类型的下拉列表
		return "cms/banner/insert";
	}

	/**
	 * @功能描述：新增--保存数据
	 */
	@RequestMapping("insert")
	// BindingResult result 绑定验证信息 跟<form:errors path="name" />配套使用
	public String insert(Banner banner, BindingResult result, Map<String, Object> map ,MultipartFile image) {
		validate(banner, result,1);// 调用共用的验证方法
		if (result.hasErrors()) {// 存在验证未通的项的时候
			map.put("typeMap", util.getDictMap(10, false));// 生成终端类型的下拉列表
			return "cms/banner/insert";
		}
		service.insert(banner,  image);
		return "redirect:list";// 重定向到本类的方法
	}

	/**
	 * @功能描述:验证轮播图名称不能重复
	 */
	private void validate(Banner banner, BindingResult result,int method) {
		BannerCond cond = new BannerCond();
		cond.setName_v(banner.getName());
//		if(banner.getId()!=null){//修改方法中调用时
		if(method==2){//修改方法中调用时
			cond.setId_c(banner.getId());
		}
		int count = service.queryCount(cond);
		if (count > 0) {
			result.rejectValue("name", "", "轮播图名称不能重复!!");
		}
	}

	/**
	 * @功能描述：删除数据
	 */
	@RequestMapping("delete")
	public String delete(String id) {
		service.delete(id);
		return "redirect:list";// 重定向到本类的方法
	}

	/**
	 * @功能描述：转到修改页面
	 */
	@RequestMapping("toupdate")
	public String toupdate(Map<String, Object> map, int id) {
		map.put("banner", service.findById(id));
		map.put("typeMap", util.getDictMap(10, false));// 生成终端类型的下拉列表
		return "cms/banner/update";
	}

	/**
	 * @功能描述：转到修改页面
	 */
	@RequestMapping("update")
	public String update(@ModelAttribute("banner") Banner banner, BindingResult result, Map<String, Object> map,MultipartFile image) {
		validate(banner, result,2);// 调用共用的验证方法
		if (result.hasErrors()) {// 存在验证未通的项的时候
			map.put("typeMap", util.getDictMap(10, false));// 生成终端类型的下拉列表
			return "cms/banner/insert";
		}
		service.update(banner,  image);
		return "redirect:list";// 重定向到本类的方法
	}

	/**
	 * @功能描述：转到详细页面
	 */
	@RequestMapping("detail")
	public String detail(Map<String, Object> map, int id) {
		map.put("banner", service.findById(id));
		return "cms/banner/detail";
	}
	
	/**
	 * @功能描述：修改轮播图状态
	 */
	@RequestMapping("updatestatus")
	public String updatestatus(Banner banner) {
		service.updateStatus(banner);
		return "redirect:list";// 重定向到本类的方法
	}
}
