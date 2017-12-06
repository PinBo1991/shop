package com.bjpowernode.cms.cms.advert.dao.impl;

import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bjpowernode.cms.cms.advert.dao.IAdvertDao;
import com.bjpowernode.cms.cms.advert.model.Advert;
import com.bjpowernode.cms.cms.advert.model.AdvertCond;
import com.common.dao.BaseDao;
import com.common.util.DataUtil;
import com.common.util.Util;

@Repository
public class AdvertDaoImpl extends BaseDao<Advert> implements IAdvertDao {

	@Override
	public int insert(Advert vo) {
		String sql = "insert into cms_advert (name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts) values(?,?,?,?,?,?,?,?,?)";
		Object obj[] = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs() };
		return jdbcTemplate.update(sql, obj);
	}

	@Override
	public int update(Advert vo) {
		String sql = "update cms_advert set name=?,order_num=?,picture_path=?,picture_url=?,jump_url=?,remark=?,status=?,type=?,ts=? where id=?";
		Object obj[] = new Object[] { vo.getName(), vo.getOrder_num(), vo.getPicture_path(), vo.getPicture_url(),
				vo.getJump_url(), vo.getRemark(), vo.getStatus(), vo.getType(), vo.getTs(), vo.getId() };
		return jdbcTemplate.update(sql, obj);
	}

	@Override
	public int delete(Integer[] ids) {
		String sql = "delete from cms_advert where id  " + Util.ArrayToInNum(ids);
		return jdbcTemplate.update(sql);
	}

	@Override
	public Advert findById(Integer id) {
		String sql = "select id,name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts from cms_advert where id=?";
		Object obj[] = new Object[] { id };
		return jdbcTemplate.queryForObject(sql, obj, new BeanPropertyRowMapper<>(Advert.class));
	}

	@Override
	public void queryList(AdvertCond cond, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append("select id,name,order_num,picture_path,picture_url,jump_url,remark,status,type,ts");
		sb.append(" from cms_advert where 1=1");
		sb.append(cond.getCondition());
		sb.append(" order by id");
		logger.info(DataUtil.showSql(sb.toString(), cond.getArray()));
		queryPage(map, sb.toString(), cond, Advert.class);

	}

	@Override
	public int queryCount(AdvertCond cond) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(id) from cms_advert where 1=1");
		sb.append(cond.getCondition());
		return jdbcTemplate.queryForObject(sb.toString(), cond.getArray(), Integer.class);
	}

	@Override
	public int updateStatus(Advert vo) {
		String sql = "update cms_advert set status=? where id=?";
		Object obj[] = new Object[] { vo.getStatus(), vo.getId() };
		return jdbcTemplate.update(sql, obj);
	}

}
