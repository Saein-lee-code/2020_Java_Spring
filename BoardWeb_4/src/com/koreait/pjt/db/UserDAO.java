package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.koreait.pjt.vo.UserVO;

public class UserDAO {
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
				ps.executeUpdate();
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
}
