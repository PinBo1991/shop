package com.bjpowernode.cms.cms.point.model;
import java.util.Date;
import com.common.condition.BaseCondition;

/**
 * @类说明:卖点图查询条件类
 *
 * @author:gzz_gzz@163.com
 * @date:2016-10-18 14:48:24
 **/
public class PointCond extends BaseCondition  {

	/**
	 * @方法说明:拼加自定义条件;可添加新条件、属性字段,删除不用的条件、属性字段
	 **/
	@Override
	public void addCondition() { 
		add(name_c, " AND T.NAME LIKE ?", 3);
		add(order_num_c, " AND T.ORDER_NUM = ?");
		add(title_c, " AND T.TITLE LIKE ?", 3);
		add(jump_url_c, " AND T.JUMP_URL LIKE ?", 3);
		add(remark_c, " AND T.REMARK LIKE ?", 3);
		add(status_c, " AND T.STATUS = ?");
		add(type_c, " AND T.TYPE = ?");
		add(ts_c, " AND T.TS = ?");
		add(startTime, " AND T.TS >= ?");
		add(endTime, " AND T.TS <= ?");
		
		
		add(id_c, " AND T.ID <> ?");
		add(name_v, " AND T.NAME=?");
	}

	//页面查询条件的ID名称、查询条可以自行增减、把不用条件清理掉!!!!!!
	//@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//页面日期类型自动转换
	private Integer id_c;// 主键
	private String name_c;// 名称
	private String name_v;// 名称
	private Integer order_num_c;// 顺序
	private String title_c;// 标题
	private String picture_path_c;// 存储路径
	private String picture_url_c;// 访问地址
	private String jump_url_c;// 跳转地址
	private String remark_c;// 备注
	private Integer status_c;// 状态
	private Integer type_c;// 所属终端
	private Date ts_c;// 时间戳
	private Date startTime;// 时间戳
	private Date endTime;// 时间戳
	
 
	public String getName_v() {
		return name_v;
	}
	public void setName_v(String name_v) {
		this.name_v = name_v;
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
	public String getTitle_c() {
		return title_c;
	}
	public void setTitle_c(String title_c) {
		this.title_c = title_c;
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

	//new 条件对象时用构造方法赋值:适用只有少量查询条件并且不需要重复使用的情况
	public PointCond(Object[][] obj) {super(obj);}
	public PointCond(){}
}