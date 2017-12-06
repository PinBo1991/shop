package com.bjpowernode.cms.cms.point.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bjpowernode.cms.cms.point.dao.IPointDao;
import com.bjpowernode.cms.cms.point.model.Point;
import com.bjpowernode.cms.cms.point.model.PointCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.Util;

/**
 * @类说明:卖点图Dao实现类
 *
 * @author:gzz_gzz@163.com
 * @date:2016-10-18 14:48:24
 **/
@Repository
public class PointDaoImpl extends BaseDao<Point> implements IPointDao {
	private final String insertSql = "INSERT INTO CMS_POINT (NAME,ORDER_NUM,TITLE,PICTURE_PATH,PICTURE_URL,JUMP_URL,REMARK,STATUS,TYPE,TS) VALUES (?,?,?,?,?,?,?,?,?,?) ";
	private final String updateSql = "UPDATE CMS_POINT SET NAME=?,ORDER_NUM=?,TITLE=?,PICTURE_PATH=?,PICTURE_URL=?,JUMP_URL=?,REMARK=?,STATUS=?,TYPE=?,TS=? WHERE ID=? ";
	private StringBuilder selectSql = new StringBuilder();

	/**
	 * @方法说明:构造方法,用于拼加SELECT-SQL及其它的初始化工作
	 **/
	public PointDaoImpl() {
		selectSql.append(
				"SELECT T.ID,T.NAME,T.ORDER_NUM,T.TITLE,T.PICTURE_PATH,T.PICTURE_URL,T.JUMP_URL,T.REMARK,T.STATUS,T.TYPE,T.TS FROM CMS_POINT T WHERE 1=1");
	}

	/**
	 * @方法说明:新增记录
	 **/
	@Override
	public int insert(Point vo) {
		// DataUtil.trim(vo);//去掉字符串型字段值前后的空格
		Object[] params = new Object[] { vo.getName(), vo.getOrder_num(), vo.getTitle(), vo.getPicture_path(),
				vo.getPicture_url(), vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs() };
		logger.debug(DataUtil.showSql(insertSql, params));// 显示SQL语句
		return jdbcTemplate.update(insertSql, params);
	}

	/**
	 * @方法说明:物理删除记录(多条)
	 **/
	@Override
	public int delete(String ids) {
		String updateStr = "DELETE FROM CMS_POINT WHERE ID" + Util.ArrayToIn(ids);// 数值型ID使用ArrayToInNum
		return jdbcTemplate.update(updateStr);
	}

	/**
	 * @方法说明:按ID查找单个实体
	 **/
	@Override
	public Point findById(Integer id) {
		StringBuilder sb = new StringBuilder(selectSql);
		sb.append(" AND T.ID=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[] { id },
				new BeanPropertyRowMapper<Point>(Point.class));
	}

	/**
	 * @方法说明:更新记录
	 **/
	@Override
	public int update(Point vo) {
		Object[] params = new Object[] { vo.getName(), vo.getOrder_num(), vo.getTitle(), vo.getPicture_path(),
				vo.getPicture_url(), vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs(),
				vo.getId() };
		return jdbcTemplate.update(updateSql, params);
	}

	/**
	 * @方法说明:按条件查询分页列表-根据需要替换成自己的SQL
	 **/
	@Override
	public void queryList(PointCond cond, Map<String, Object> map) {
		StringBuilder sb = new StringBuilder(selectSql);
		sb.append(cond.getCondition());
		// sb.append(" ORDER BY T.ID");//MS_SQL中不能加此句
		logger.debug(DataUtil.showSql(sb.toString(), cond.getArray()));// 显示SQL语句
		queryPage(map, sb.toString(), cond, Point.class);// (使用范型)
	}

	/**
	 * @方法说明:按条件查询记录个数
	 **/
	@Override
	public int findCountByCond(PointCond cond) {
		String countSql = "SELECT COUNT(T.ID) FROM CMS_POINT T WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Integer.class);
	}

	@Override
	public int updateStatus(Point vo) {
		String sql = "UPDATE CMS_POINT SET STATUS=? WHERE ID=? ";
		Object[] params = new Object[] { vo.getStatus(), vo.getId() };
		return jdbcTemplate.update(sql, params);
	}

}