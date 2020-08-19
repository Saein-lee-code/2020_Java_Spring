package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {
	// select
	public static List<BoardVO> selBoardList(){
		// 주소값 고정, 값을 추가하거나 바꿀순 있음.
		List<BoardVO> list = new ArrayList();
		String sql = " SELECT I_BOARD, TITLE, HITS, I_USER, R_DT FROM T_BOARD_4 "
				+ " ORDER BY I_BOARD ";
		// ? 가없기때문에 prepared는 필요없지만 구현은 해야됌(비워둠)
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");					
					int hits = rs.getInt("hits");
					int i_user = rs.getInt("i_user");
					String r_dt = rs.getNString("r_dt");
					
					BoardVO vo = new BoardVO();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);
					vo.setI_user(i_user);
					vo.setR_dt(r_dt);
					
					list.add(vo);
				}
				return 1;
			}			
		});		
		return list;
	}
	public static BoardVO detailBoard(final BoardVO param) {
		BoardVO vo = null;
		return vo;
	}
	// insert
	public static int insBoard(BoardVO param) {
		String sql = " INSERT INTO t_board_4 "
				+ " (i_board, title, ctnt, i_user) "
				+ " VALUES "
				+ " (seq_board_4.nextval, ?,?,?)";
		
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				//emoji 넣을때 n'' 됌. setString 하면 이모지 안들어감
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());								
			}			
		});		
	}
}
