package com.bjpowernode.cms.cms.point.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @类说明:卖点图实体类
 *
 * @author:gzz_gzz@163.com
 * @date:2016-10-18 14:48:24
 **/
public class Point {

	//数据库中的字段
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//页面日期类型自动转换
	private Integer id;// 主键
	private String name;// 名称
	private Integer order_num;// 顺序
	private String title;// 标题
	private String picture_path;// 存储路径
	private String picture_url;// 访问地址
	private String jump_url;// 跳转地址
	private String remark;// 备注
	private Integer status;// 状态
	private Integer type;// 所属终端
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")//页面日期类型自动转换
	private Date ts;// 时间戳

	//此处可添加查询显示辅助字段
	private String status_text;// 状态
	private String type_text;// 所属终端
	
	public String getStatus_text() {
		return status_text;
	}
	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}
	public String getType_text() {
		return type_text;
	}
	public void setType_text(String type_text) {
		this.type_text = type_text;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getJump_url() {
		return jump_url;
	}
	public void setJump_url(String jump_url) {
		this.jump_url = jump_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
}