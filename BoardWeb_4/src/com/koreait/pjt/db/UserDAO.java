package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;

public class UserDAO {
	public static int insUserLoginHistory(UserLoginHistoryVO param) {
		String sql = " INSERT INTO T_USER_LOGINHISTORY "
				+ " (I_HISTORY, I_USER, IP_ADDR, OS, BROWSER) "
				+ " VALUES "
				+ " (SEQ_USERLOGINHISTORY.nextval, ?, ?, ?, ?) ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setNString(2, param.getIp_addr());
				ps.setNString(3, param.getOs());
				ps.setNString(4, param.getBrowser());
			}			
		});
	}
	public static int insProfileImg(UserVO param) {
		String sql = " UPDATE T_USER SET PROFILE_IMG = ? "
				+ " WHERE I_USER = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getProfile_img());
				ps.setInt(2, param.getI_user());
			}			
		});
	}
	public static int insUser(UserVO param) {		
		String sql = " INSERT INTO t_user "
				+ " (i_user, user_id, user_pw, nm, email) "
				+ " VALUES "
				+ " (seq_user.nextval, ?,?,?,?) ";
		// jdbc 익명클래스
		// 인터페이스를 객체화 한것이 아님
		// 자바는 override부분을 그냥보낼 수없어서 implements(익명클래스)로 보냄
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1,  param.getUser_id());
				ps.setNString(2,  param.getUser_pw());
				ps.setNString(3, param.getNm());
				ps.setNString(4, param.getEmail());
			}			
		});
	}

	public static int login(UserVO param) {
		String sql = " SELECT I_USER, USER_PW, NM FROM T_USER WHERE USER_ID = ? ";
		return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			// rs 에서 값 빼내는것 
			@Override
			public int executeQuery(ResultSet rs) throws SQLException{				
				if(rs.next()) {
					String dbPw = rs.getNString("user_pw");
					if(dbPw.equals(param.getUser_pw())) {
						int i_user = rs.getInt("i_user");
						String nm = rs.getNString("nm");
						param.setUser_pw(null);
						param.setI_user(i_user);
						param.setNm(nm);
						return 1; // 로그인 성공					
					}else // 비밀번호 틀림
						return 2;
				}else // 아이디 없음
					return 3;
			}
			
			// 물음표에 값 넣는것
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getUser_id());
				ps.executeQuery();
			}
		});
	}
	
	public static UserVO selUser(int i_user) {
		String sql = " SELECT USER_ID, NM, PROFILE_IMG, EMAIL, R_DT "
				+ " FROM T_USER WHERE I_USER = ? ";
		UserVO result = new UserVO();
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_user);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if(rs.next()) {
					result.setUser_id(rs.getNString("user_id"));
					result.setNm(rs.getNString("nm"));
					result.setProfile_img(rs.getNString("profile_img"));
					result.setEmail(rs.getNString("email"));
					result.setR_dt(rs.getNString("r_dt"));
				}				
				return 1;
			}			
		});
		return result;
	}
	
	public static int updUser(UserVO param) {
		StringBuilder sb = new StringBuilder();
//		String sql = " UPDATE T_USER "
//				+ " SET M_DT = SYSDATE ";
		sb.append("UPDATE t_user set i_user = ");
		sb.append(param.getI_user());
		if(param.getUser_pw() != null) {
			sb.append(" , user_pw = '");
			sb.append(param.getUser_pw());
			sb.append("' ");
		}
		if(param.getNm() != null) {
			sb.append(" , nm = '");
			sb.append(param.getNm());
			sb.append("' ");
		}
		if(param.getEmail() != null) {
			sb.append(" , email = '");
			sb.append(param.getEmail());
			sb.append("' ");
		}
		if(param.getProfile_img() != null) {
			sb.append(" , profile_img = '");
			sb.append(param.getProfile_img());
			sb.append("' ");
		}
		sb.append(" WHERE I_USER = ");
		sb.append(param.getI_user());
		
		
		System.out.println("sb: " + sb.toString());
		return JdbcTemplate.executeUpdate(sb.toString(), new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
			}			
		});
	}
}
