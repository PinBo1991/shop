package com.bjpowernode.cms.cms.point.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.bjpowernode.cms.cms.point.model.Point;
import com.bjpowernode.cms.cms.point.model.PointCond;
import com.bjpowernode.cms.cms.point.service.IPointService;
import com.common.dao.DictParamUtil;

/**
 * @类说明:卖点图控制器类
 *
 * @author:gzz_gzz@163.com
 * @date:2016-10-18 14:48:24
 **/
@Controller
@RequestMapping("point")
public class PointAction {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private IPointService service; // 卖点图Service

	@Autowired
	private DictParamUtil util;// 字典参数工具类

	/**
	 * @方法说明:跳转到新增页面
	 **/
	@RequestMapping("toInsert")
	public String toInsert(Map<String, Object> map, @ModelAttribute("point") Point point, HttpSession session) {
		map.put("typeMap", util.getDictMap(10, false));// 生成终端类型下拉列表
		return "/cms/point/insert";
	}

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("insert")
	public String insert(Map<String, Object> map, @ModelAttribute("point") Point point, BindingResult result,
			MultipartFile image) {
		validate(point, result, 1, image);// 调用新增验证方法
		if (result.hasErrors()) {
			map.put("typeMap", util.getDictMap(10, false));// 生成终端类型下拉列表
			return "/cms/point/insert";
		}
		service.insert(point, image);
		return "redirect:/point/list";
	}

	/**
	 * @方法说明:新增/修改后台验证
	 **/
	private void validate(Point point, BindingResult result, int method, MultipartFile image) {// method:1新增2修改
		PointCond cond = new PointCond();
		cond.setName_v(point.getName());
		if (method == 2) {
			cond.setId_c(point.getId());
		}
		if (service.findCountByCond(cond) > 0) {
			result.rejectValue("name", "", "[卖点图名称]不能重复!");
		}
		if (!image.isEmpty()) {
			String standard = util.findDictValue(16, point.getType());// 从内存取出指定终端验证标准
			String stand[] = standard.split(",");
			try {
				BufferedImage bi = ImageIO.read(image.getInputStream());
				if (bi.getWidth() != new Integer(stand[0])) {// 验证宽度
					result.rejectValue("name", "", "卖点图宽度必须为" + stand[0] + "px!");
				}

				if (bi.getHeight() != new Integer(stand[1])) {// 验证宽度
					result.rejectValue("name", "", "卖点图高度必须为" + stand[1] + "px!");
				}

				if (image.getSize() / 1024 > new Integer(stand[2])) {// 验证宽度
					result.rejectValue("name", "", "卖点图大小不超过" + stand[2] + "K!");
				}
			} catch (IOException e) {
				logger.error("图片验证时出现IOException一场!!!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @方法说明:删除记录(多条)
	 **/
	@RequestMapping("delete")
	public String delete(Map<String, Object> map, String id) {
		service.delete(id);
		return "redirect:/point/list";
	}

	/**
	 * @方法说明:跳转到修改页面
	 **/
	@RequestMapping("toUpdate")
	public String toUpdate(Map<String, Object> map, Integer id) {
		map.put("point", service.findById(id));
		map.put("typeMap", util.getDictMap(10, false));// 生成终端类型下拉列表
		return "/cms/point/update";
	}

	/**
	 * @方法说明:修改记录
	 **/
	@RequestMapping("update")
	public String update(Map<String, Object> map, @ModelAttribute("point") Point point, BindingResult result,
			MultipartFile image) {
		validate(point, result, 2, image);// 调用修改验证方法
		if (result.hasErrors()) {
			map.put("typeMap", util.getDictMap(10, false));// 生成终端类型下拉列表
			return "/cms/point/update";
		}
		service.update(point, image);
		return "redirect:/point/list";
	}

	/**
	 * @方法说明:按条件查询分页列表页面
	 **/
	@RequestMapping("list")
	public String queryList(Map<String, Object> map, @ModelAttribute("cond") PointCond cond) {
		service.queryList(cond, map);
		map.put("typeMap", util.getDictMap(10, true));// 生成终端类型下拉列表
		map.put("statusMap", util.getDictMap(15, true));// 生成卖点图状态下拉列表
		return "/cms/point/list";
	}

	/**
	 * @方法说明:跳转到详细页面
	 **/
	@RequestMapping("detail")
	public String detail(Map<String, Object> map, Integer id) {
		map.put("point", service.findById(id));
		return "/cms/point/detail";
	}

	/**
	 * @方法说明:修改卖点图状态
	 **/
	@RequestMapping("updateStatus")
	public String updateStatus(Point point) {
		service.updateStatus(point);
		return "redirect:/point/list";
	}

	/**
	 * @方法说明:日期属性编辑器(新增/修改/查询条件中String自动转换成Date)
	 **/
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// true允许为空
	}

	/**
	 * @方法说明:按条件查询分页列表(参照选择页)
	 **/
	@RequestMapping("ref")
	public String queryRef(Map<String, Object> map, @ModelAttribute("cond") PointCond cond) {
		service.queryList(cond, map);
		return "/cms/point/listRef";
	}

	/**
	 * @方法说明:简单列表;带查询,无增删改,分页可选
	 **/
	@RequestMapping("listSimple")
	public String queryListSimple(Map<String, Object> map, @ModelAttribute("cond") PointCond cond) {
		// map.put("dataList", service.queryAllMap(cond));//不分页
		service.queryList(cond, map);// 分页
		return "/cms/point/listSimple";
	}
}