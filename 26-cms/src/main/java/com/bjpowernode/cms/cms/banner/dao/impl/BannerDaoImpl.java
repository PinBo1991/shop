package com.bjpowernode.cms.cms.banner.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bjpowernode.cms.cms.banner.dao.IBannerDao;
import com.bjpowernode.cms.cms.banner.model.Banner;
import com.bjpowernode.cms.cms.banner.model.BannerCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.Util;

/**
 * @功能描述：轮播图dao实现类
 * @author gzz
 * @date : 2016-10-13
 */
@Repository
public class BannerDaoImpl extends BaseDao<Banner> implements IBannerDao {

	@Override
	public int insert(Banner vo) {// vo value object
		String sql = "insert into cms_banner (name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts) values(?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs() };
		return jdbcTemplate.update(sql, obj);
	}

	@Override
	public int update(Banner vo) {
		String sql = "update cms_banner set name=?,order_num=?,picture_path=?,picture_url=?,jump_url=?,remark=?,status=?,type=?,ts=? where id=?";
		Object[] obj = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs(), vo.getId() };
		return jdbcTemplate.update(sql, obj);
	}

	@Override
	public int delete(String ids) {
		String sql = "delete from cms_banner where id " + Util.ArrayToInNum(ids);
		return jdbcTemplate.update(sql);
	}

	@Override
	public Banner findById(int id) {
		String sql = "select id,name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts from cms_banner where id=?";
		Object[] obj = new Object[] { id };
		return jdbcTemplate.queryForObject(sql, obj, new BeanPropertyRowMapper<>(Banner.class));
	}

	@Override
	public void queryList(BannerCond cond, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id,name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts ");
		sb.append(" from cms_banner where 1=1");
		sb.append(cond.getCondition());
		// sb.append(" order by id");
		logger.debug(DataUtil.showSql(sb.toString(), cond.getArray()));
		queryPage(map, sb.toString(), cond, Banner.class);
	}

	@Override
	public int queryCount(BannerCond cond) {
		String sql = "select count(1) from cms_banner where 1=1" + cond.getCondition();
		logger.debug(DataUtil.showSql(sql, cond.getArray()));
		return jdbcTemplate.queryForObject(sql, cond.getArray(), Integer.class);
	}

	@Override
	public int updateStatus(Banner vo) {
		String sql = "update cms_banner set status=?  where id=?";
		Object[] obj = new Object[] { vo.getStatus(), vo.getId() };
		return jdbcTemplate.update(sql, obj);
	}

}
