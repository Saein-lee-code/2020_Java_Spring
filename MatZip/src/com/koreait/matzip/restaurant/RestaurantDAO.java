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
				ps.setDouble(3, param.getLat());
				ps.setDouble(4, param.getLng());
				ps.setInt(5, param.getI_user());
				ps.setInt(6, param.getCd_category());
				ps.setNString(7, param.getM_dt());
			}			
		});
	}
	public RestaurantDomain restDetail(RestaurantDomain param) {
		RestaurantDomain result = new RestaurantDomain();		
		String sql = " SELECT A.I_REST, A.NM, A.ADDR, A.I_USER, A.hits as cntHits, B.VAL AS CD_CATEGORY_NM, IFNULL(C.cnt, 0) AS cntFavorite "
				+ " FROM t_restaurant A "
				+ " LEFT JOIN c_code_d B "
				+ " ON A.cd_category = B.cd "
				+ " AND B.i_m = 1 "
				+ " LEFT JOIN( "
				+ " 	SELECT i_rest, COUNT(i_rest) AS cnt "
				+ " 	FROM t_user_favorite "
				+ "		WHERE i_rest = ? "
				+ " 	GROUP BY i_rest "
				+ " ) C "
				+ " ON A.i_rest = C.i_rest "
				+ " WHERE A.i_rest = ? ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_rest());
				ps.setInt(2, param.getI_rest());
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setI_user(rs.getInt("i_user"));
					result.setNm(rs.getNString("nm"));
					result.setAddr(rs.getNString("addr"));
					result.setCntHits(rs.getInt("cntHits"));
					result.setCdCategoryName(rs.getNString("cd_category_nm"));
					result.setCntFavorite(rs.getInt("cntFavorite"));
				}
			}
		});
		return result;
	}
	
	public List<RestaurantDomain> selRestList(){
		List<RestaurantDomain> list = new ArrayList();
		String sql = " SELECT i_rest, i_user, cd_category, nm, lat, lng FROM t_restaurant ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					RestaurantDomain vo = new RestaurantDomain();
					vo.setI_rest(rs.getInt("i_rest"));
					vo.setI_user(rs.getInt("i_user"));
					vo.setCd_category(rs.getInt("cd_category"));
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
