package com.koreait.matzip.restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.db.JdbcUpdateInterface;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantVO;

public class RestaurantDAO {
	public int regRest (RestaurantVO param) {
		String sql = " INSERT INTO T_RESTAURANT "
				+ " (NM, ADDR, LAT, LNG, I_USER, CD_CATEGORY, M_DT)"
				+ " VALUES (?,?,?,?,?,?,?) ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getNm());
				ps.setNString(2, param.getAddr());
				ps.setDouble(3, param.getLng());
				ps.setDouble(4, param.getLat());
				ps.setInt(5, param.getI_user());
				ps.setInt(6, param.getCd_category());
				ps.setNString(7, param.getM_dt());
			}			
		});
	}
	public List<RestaurantDomain> selRestList(){
		List<RestaurantDomain> list = new ArrayList();
		String sql = " SELECT i_rest, nm, lat, lng FROM t_restaurant ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					RestaurantDomain vo = new RestaurantDomain();
					vo.setNm(rs.getNString("nm"));
					vo.setLat(rs.getDouble("lat"));
					vo.setLng(rs.getDouble("lng"));
					list.add(vo);
				}
			}
		});
		return list;
	}	
}
