package com.common.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bjpowernode.cms.sys.dict.model.Dict;
import com.bjpowernode.cms.sys.dict.model.DictCond;
import com.bjpowernode.cms.sys.dict.service.IDictService;
import com.bjpowernode.cms.sys.dicttype.model.DictType;
import com.bjpowernode.cms.sys.dicttype.model.DictTypeCond;
import com.bjpowernode.cms.sys.dicttype.service.IDictTypeService;
import com.bjpowernode.cms.sys.param.model.Param;
import com.bjpowernode.cms.sys.param.model.ParamCond;
import com.bjpowernode.cms.sys.param.service.IParamService;

/**
 * @类说明:字典/参数辅助工具类
 * @author GZZ
 */
@Component
public class DictParamUtil {
	@Autowired
	private IDictService dictService;// 字典表的service接口
	@Autowired
	private IDictTypeService typeService;// 字典类型表的service接口
	@Autowired
	private IParamService paramService;// 注入参数表service接口

	private Map<String, String> paramMap = new HashMap<>();// 用来存放系统参数

	private Map<Integer, Map<Integer, String>> typeMap = new HashMap<Integer, Map<Integer, String>>();// 用来存放数据字典

	private Log logger = LogFactory.getLog(DictParamUtil.class);

	/**
	 * @功能描述: 把所有系统参数放到paramMap中
	 */
	@PostConstruct // spring扫描完成之后就执行这个方法
	public void initParam() {
		paramMap.clear();// 清空map
		List<Param> list = paramService.queryAllObj(new ParamCond());// 到数据库查所有记录
		for (Param param : list) {
			paramMap.put(param.getParam_key(), param.getParam_value());
		}
		logger.info("参数初始化完成");
		logger.info(paramMap);

	}

	/**
	 * @功能描述: 把所有数据字典放到typeMap中
	 */
	@PostConstruct // spring扫描完成之后就执行这个方法
	public void initDict() {
		typeMap.clear();//清空字典map
		List<DictType> typeList = typeService.queryAllObj(new DictTypeCond());// 到数据库中去查询所有字典类型（一共有多少组值）
		Map<Integer, String> dictMap;// 内层map 一组值
		DictCond cond;// 查询字典条件引用
		List<Dict> dictList;// 每次查询出一组字典值
		for (DictType dictType : typeList) {
			dictMap = new HashMap<>();// 内层map 一组值
			typeMap.put(dictType.getType_code(), dictMap);
			cond = new DictCond();
			cond.setType_code_c(dictType.getType_code());// 构造查询条件
			dictList = dictService.queryAllObj(cond);// 到数据库表中取一组值
			for (Dict dict : dictList) {
				dictMap.put(dict.getData_key(), dict.getData_value());
			}
		}
		logger.info("字典初始化完成");
		logger.info(typeMap);
	}

	/**
	 * @功能描述: 用字典类型编码构建下拉框Map
	 */
	public Map<Integer, String> getDictMap(Integer typeCode, boolean addBlank) {
		Map<Integer, String> map = new LinkedHashMap<>();
		if (addBlank) {
			map.put(null, "--请选择--");
		}
		Map<Integer, String> dictMap = typeMap.get(typeCode);// 取出一组值
		for (Map.Entry<Integer, String> entry : dictMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	/**
	 * @功能描述: 按字典类型与字典关键字查找字典值
	 */
	public String findDictValue(Integer typecode, Integer datakey) {
		return typeMap.get(typecode).get(datakey);
	}

	/**
	 * @功能描述: 参数表按键找值
	 */
	public String findValue(String key) {
		return paramMap.get(key);
	}
}
