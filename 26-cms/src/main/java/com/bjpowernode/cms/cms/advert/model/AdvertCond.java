package com.bjpowernode.cms.cms.advert.model;

import java.util.Date;

import com.common.condition.BaseCondition;

/**
 * @功能描述：广告位查询条件的实体类
 * @author gzz
 * @date : 2016-10-17
 */

public class AdvertCond extends BaseCondition {

	private Integer id_c;// 主键
	private String name_c;// 名称
	private Integer order_num_c;// 顺序
	private String picture_path_c;// 存储路径
	private String picture_url_c;// 访问地址
	private String jump_url_c;// 跳转地址
	private String remark_c;// 备注
	private Integer status_c;// 状态
	private Integer type_c;// 所属终端
	private Date ts_c;// 时间戳
	
	private String name_v;// 名称
	
	private Date startTime;// 时间戳-开始
	
	private Date endTime;// 时间戳-结束
	
	@Override
	public void addCondition() {
		add(name_c, "and name like ?", 3);
		add(order_num_c, "and order_num=?");
		add(status_c, "and status=?");
		add(type_c, "and type=?");
		
		add(startTime, "and ts>=?");
		add(endTime, "and ts<=?");
		
		add(name_v, "and name=?");
		add(id_c, "and id<>?");
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName_v() {
		return name_v;
	}

	public void setName_v(String name_v) {
		this.name_v = name_v;
	}

	public Integer getId_c() {
		return id_c;
	}

	public void setId_c(Integer id_c) {
		this.id_c = id_c;
	}

	public String getName_c() {
		return name_c;
	}

	public void setName_c(String name_c) {
		this.name_c = name_c;
	}

	public Integer getOrder_num_c() {
		return order_num_c;
	}

	public void setOrder_num_c(Integer order_num_c) {
		this.order_num_c = order_num_c;
	}

	public String getPicture_path_c() {
		return picture_path_c;
	}

	public void setPicture_path_c(String picture_path_c) {
		this.picture_path_c = picture_path_c;
	}

	public String getPicture_url_c() {
		return picture_url_c;
	}

	public void setPicture_url_c(String picture_url_c) {
		this.picture_url_c = picture_url_c;
	}

	public String getJump_url_c() {
		return jump_url_c;
	}

	public void setJump_url_c(String jump_url_c) {
		this.jump_url_c = jump_url_c;
	}

	public String getRemark_c() {
		return remark_c;
	}

	public void setRemark_c(String remark_c) {
		this.remark_c = remark_c;
	}

	public Integer getStatus_c() {
		return status_c;
	}

	public void setStatus_c(Integer status_c) {
		this.status_c = status_c;
	}

	public Integer getType_c() {
		return type_c;
	}

	public void setType_c(Integer type_c) {
		this.type_c = type_c;
	}

	public Date getTs_c() {
		return ts_c;
	}

	public void setTs_c(Date ts_c) {
		this.ts_c = ts_c;
	}

}
