package com.koreait.pjt.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

public class BoardDAO {

	// select
	public static List<BoardDomain> selBoardList(int sIndex, int eIndex) {
		// 주소값 고정, 값을 추가하거나 바꿀순 있음.
		List<BoardDomain> list = new ArrayList();
//		String sql = " SELECT I_BOARD, TITLE, HITS, I_USER, R_DT FROM T_BOARD_4 " + " ORDER BY I_BOARD ";
		// ? 가없기때문에 prepared는 필요없지만 구현은 해야됌(비워둠)
		String sql = " SELECT * " 
			+ " FROM (SELECT A.*, ROWNUM RN "
			    + " FROM ("
			    	  + " SELECT A.I_BOARD, A.TITLE, A.HITS, A.R_DT, B.NM "
				          + " FROM T_BOARD_4 A "
				          + " INNER JOIN T_USER B"
				          + " ON A.I_USER = B.I_USER "
				          + " ORDER BY I_BOARD desc"
				 + " ) A "
			    + " WHERE ROWNUM <= ?) "
			+ " WHERE RN > ? ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, eIndex);
				ps.setInt(2, sIndex);
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				while (rs.next()) {
					int i_board = rs.getInt("i_board");
					String title = rs.getNString("title");
					int hits = rs.getInt("hits");					
					String nm = rs.getNString("nm");
					String r_dt = rs.getNString("r_dt");

					BoardDomain vo = new BoardDomain();
					vo.setI_board(i_board);
					vo.setTitle(title);
					vo.setHits(hits);					
					vo.setNm(nm);
					vo.setR_dt(r_dt);

					list.add(vo);
				}
				return 1;
			}
		});
		return list;
	}

	public static BoardDomain detailBoard(final BoardVO param) {
		final BoardDomain result = new BoardDomain();
		result.setI_board(param.getI_board());
		String sql = " SELECT A.TITLE, A.I_USER, B.NM as name, TO_CHAR(A.R_DT, 'YYYY/MM/DD HH24:MI') as formated_date, A.HITS, A.CTNT, "
				+ " DECODE(C.I_USER, NULL, 0, 1) AS YN_LIKE "
				+ " FROM T_BOARD_4 A"
				+ " INNER JOIN T_USER B" 
				+ " ON (A.I_USER = B.I_USER) "
				+ " LEFT JOIN T_BOARD4_LIKE C"
				+ " ON A.I_BOARD = C.I_BOARD "
				+ " AND C.I_USER = ? "
				+ " WHERE A.I_BOARD = ? ";
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {
			@Override
			public void prepared(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_user());
				ps.setInt(2, param.getI_board());
			}

			@Override
			public int executeQuery(ResultSet rs) throws SQLException {
				if (rs.next()) {
					result.setTitle(rs.getNString("title"));
					result.setI_user(rs.getInt("i_user"));
					result.setNm(rs.getNString("name"));
					result.setR_dt(rs.getNString("formated_date"));
					result.setHits(rs.getInt("hits"));
					result.setCtnt(rs.getNString("ctnt"));
					result.setYn_like(rs.getInt("yn_like"));
				}
				return 1;
			}
		});
		
		return result;
	}
	// 페이징 숫자 가져오기
		public static int selPagingCnt(final BoardDomain param) {
			String sql = " SELECT CEIL(COUNT(I_BOARD) / ?) FROM T_BOARD_4 ";
			return JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

				@Override
				public void prepared(PreparedStatement ps) throws SQLException {
					ps.setInt(1, param.getRecord_cnt());
				}
				// 스칼라 1행1열만 있는것
				@Override
				public int executeQuery(ResultSet rs) throws SQLException {
					if(rs.next()) {
						return rs.getInt(1);
					}
					return 0;
				}
				
			});		
		}
	// insert
	public static int insBoard(BoardVO param) {
		String sql = " INSERT INTO t_board_4 " + " (i_board, title, ctnt, i_user) " + " VALUES "
				+ " (seq_board_4.nextval, ?,?,?)";

		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				// emoji 넣을때 n'' 됌. setString 하면 이모지 안들어감
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_user());
			}
		});
	}

	public static int delBoard(final BoardVO param) {
		String sql = " DELETE FROM T_BOARD_4 WHERE I_BOARD = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
//				ps.setInt(2, param.getI_user());
			}
		});
	}

	public static int uptBoard(BoardVO param) {
		String sql = " UPDATE T_BOARD_4 SET TITLE = ?, CTNT = ? WHERE I_BOARD = ? AND I_USER =? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setNString(1, param.getTitle());
				ps.setNString(2, param.getCtnt());
				ps.setInt(3, param.getI_board());
				ps.setInt(4, param.getI_user());
			}
		});
	}

	public static void addHits(int i_board) {
		String sql = " UPDATE T_BOARD_4 SET HITS = HITS + 1 WHERE I_BOARD =? ";
		JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, i_board);
			}
		});
	}

	public static int insBoardLike(BoardVO param) {		
		String sql = " INSERT INTO T_BOARD4_LIKE (I_BOARD, I_USER) VALUES (?, ?) ";			
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {				
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}				
		});
		
			
	}
	public static int delBoardLike(BoardVO param) {
		String sql = " DELETE FROM T_BOARD4_LIKE WHERE I_BOARD = ? AND I_USER = ? ";
		return JdbcTemplate.executeUpdate(sql, new JdbcUpdateInterface() {
			@Override
			public void update(PreparedStatement ps) throws SQLException {
				ps.setInt(1, param.getI_board());
				ps.setInt(2, param.getI_user());
			}				
		});
	}
}
