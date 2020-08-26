package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardCmtVO;

public class BoardCmtDAO {
	public static int insCmt(BoardCmtVO param) {
		String sql = " INSERT INTO T_BOARD4_CMT"
				+ " (i_cmt, i_user, i_board, ctnt)"
				+ " VALUES"
				+ " (seq_board4_cmt.nextval, ?, ?, ?) ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
				ps.setNString(3, param.getCtnt());
			}			
		});
	}
	public static int updCmt() {
		String sql = " UPDATE  ";
		return 0;
	}
	public static int delCmt(BoardCmtVO param) {
		String sql = " DELETE FROM T_BOARD4_CMT WHERE I_CMT = ? AND I_USER = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {

			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1,  param.getI_cmt());
				ps.setInt(2, param.getI_user());
			}			
		});
	}
	
	public static List<BoardCmtDomain> selCmt(int i_board){
		List<BoardCmtDomain> list = new ArrayList();		
		// i_user 값은 수정/삭제를위해 필요함 
		// orderby 는 string 으로하면 느려서 i_cmt 숫자로 해줘야함
		String sql = " SELECT A.I_CMT, A.I_BOARD, A.CTNT, A.I_USER, B.NM AS NAME, A.R_DT "
				+ " FROM T_BOARD4_CMT A"
				+ " INNER JOIN T_USER B"
				+ " ON (A.I_USER = B.I_USER) "
				+ " WHERE A.I_BOARD = ? ORDER BY A.I_CMT ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {					
					BoardCmtDomain vo = new BoardCmtDomain();
					vo.setNm(rs.getNString("name"));
					vo.setI_cmt(rs.getInt("i_cmt"));
					vo.setI_board(rs.getInt("i_board"));
					vo.setCtnt(rs.getNString("ctnt"));
					vo.setI_user(rs.getInt("i_user"));
					vo.setR_dt(rs.getNString("r_dt"));
					list.add(vo);
				}
				return 1;
			}			
		});
		return list;
	}
}
