package com.bjpowernode.cms.cms.advert.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.advert.model.Advert;
import com.bjpowernode.cms.cms.advert.model.AdvertCond;
import com.bjpowernode.cms.cms.advert.service.IAdvertService;
import com.common.dao.DictParamUtil;

/**
 * @功能描述：广告位的控制器类
 * @author gzz
 * @date : 2016-10-17
 */
@Controller
@RequestMapping("advert")
public class AdvertAction {

	@Autowired
	private IAdvertService service;// 广告位的service接口

	@Autowired
	private DictParamUtil util;// 字典参数工具类

	/**
	 * @功能描述：转到分页列表页面
	 */
	@RequestMapping("list")
	public String queryList(@ModelAttribute("cond") AdvertCond cond, Map<String, Object> map) {
		service.queryList(cond, map);
		map.put("typeMap", util.getDictMap(10, true));// 生成终端类型下拉列表
		map.put("statusMap", util.getDictMap(13, true));// 生成广告位的状态下拉列表
		return "cms/advert/list";
	}

	/**
	 * @功能描述：转到新增页面
	 */
	@RequestMapping("toinsert")
	public String toinsert(@ModelAttribute("advert") Advert advert, Map<String, Object> map) {
		map.put("typeMap", util.getDictMap(10, false));// 把终端类型的下拉列表放入request中
		return "cms/advert/insert";
	}

	/**
	 * @功能描述：新增页面--保存数据
	 */
	@RequestMapping("insert")
	public String insert(Advert advert, Map<String, Object> map, MultipartFile image, BindingResult result) {
		validate(advert, image, result, 1);// 调用验证方法
		if (result.hasErrors()) {
			map.put("typeMap", util.getDictMap(10, false));// 把终端类型的下拉列表放入request中
			return "cms/advert/insert";
		}
		service.insert(advert, image);
		return "redirect:list";
	}

	/**
	 * @功能描述：共用的验证方法 要求:
	 * @pc 1200,380,500k;
	 * @m 1200,280,400k;
	 * @app 1200,280,400k;
	 */
	private void validate(Advert advert, MultipartFile image, BindingResult result, int method) {
		try {
			if (!image.isEmpty()) {
				String standard = util.findDictValue(12, advert.getType());// 指定一个终端的验证标准
				String[] stand = standard.split(",");
				BufferedImage bi = ImageIO.read(image.getInputStream());
				if (bi.getWidth() != new Integer(stand[0])) {
					result.rejectValue("name", "", "广告位宽度必须是" + stand[0] + "px!!!");
				}
				if (bi.getHeight() != new Integer(stand[1])) {
					result.rejectValue("name", "", "广告位高度必须是" + stand[1] + "px!!!");
				}
				if (image.getSize() / 1024 > new Integer(stand[2])) {
					result.rejectValue("name", "", "广告位大小必须小于" + stand[2] + "k!!!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		AdvertCond cond = new AdvertCond();
		cond.setName_v(advert.getName());
		if (method == 2) {
			cond.setId_c(advert.getId());
		}
		int count = service.queryCount(cond);
		if (count > 0) {
			result.rejectValue("name", "", "广告位名称不能重复!!!");
		}
	}

	/**
	 * @功能描述：转到修改页面
	 */
	@RequestMapping("toupdate")
	public String toupdate(int id, Map<String, Object> map) {
		map.put("advert", service.findById(id));
		map.put("typeMap", util.getDictMap(10, false));// 把终端类型的下拉列表放入request中
		return "cms/advert/update";
	}

	/**
	 * @功能描述：修改页面--保存数据
	 */
	@RequestMapping("update")
	public String update(Advert advert, Map<String, Object> map, MultipartFile image, BindingResult result) {
		validate(advert, image, result, 2);// 调用验证方法
		if (result.hasErrors()) {
			map.put("typeMap", util.getDictMap(10, false));// 把终端类型的下拉列表放入request中
			return "cms/advert/update";
		}
		service.update(advert, image);
		return "redirect:list";
	}

	/**
	 * @功能描述：删除数据
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Integer delete(@RequestParam("ids[]") Integer ids[]) {
		return service.delete(ids);
	}

	/**
	 * @功能描述：转到详细页面
	 */
	@RequestMapping("detail")
	public String detail(int id, Map<String, Object> map) {
		map.put("advert", service.findById(id));
		return "cms/advert/detail";
	}
	/**
	 * @功能描述：修改状态
	 */
	@RequestMapping("updateStatus")
	public String updateStatus(Advert advert) {
		service.updateStatus(advert) ;
		return "redirect:list";
	}
	
	/**
	 * 日期属性编辑器
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许为空
	}
}
