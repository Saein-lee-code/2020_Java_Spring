package com.koreait.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.koreait.board.vo.BoardVO;

public class BoardDAO {
	public static List<BoardVO> selBoardList(){
		List<BoardVO> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT I_BOARD, TITLE, I_STUDENT FROM T_BOARD ORDER BY I_BOARD";
		try {
			con = DbCon.getCon();		
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {			
				BoardVO vo = new BoardVO();
				vo.setI_board(rs.getInt("i_board"));
				vo.setTitle(rs.getNString("title"));
				vo.setI_student(rs.getInt("i_student"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbCon.close(con, ps, rs);
		}
		return list;
	}
	// 원본을 그대로 가져오는것이 좋다
	public static BoardVO boardDetail(final BoardVO param) {
		BoardVO vo = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String sql = " SELECT TITLE, I_STUDENT, CTNT FROM T_BOARD WHERE I_BOARD = ? ";
		try {
			con = DbCon.getCon();		
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			rs = ps.executeQuery();	
			// pk 값이 넘어올때 경우의수는 0 또는 1줄
			// list 출력일때는 pk값이아닌 from으로 하기때문에
			// while로 써줌
			if(rs.next()) {		
				vo = new BoardVO();
				int i_student = rs.getInt("i_student");
				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				vo.setTitle(title);
				vo.setI_student(i_student);
				vo.setCtnt(ctnt);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbCon.close(con, ps, rs);
		}
		return vo;
	}

	public static void boardInsert(BoardVO param) {		
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		String sql = " INSERT INTO T_BOARD (I_BOARD, TITLE, CTNT, I_STUDENT) " + 
				" SELECT NVL(MAX(I_BOARD),0) + 1 AS COUNT, ?, ?, ? FROM T_BOARD ";
		String sql2 = " SELECT COUNT(*) + 1 AS COUNT FROM T_BOARD ";
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps2 = con.prepareStatement(sql2);
			rs = ps2.executeQuery();	
			if(rs.next()) {			
				ps.setString(1, param.getTitle());
				ps.setString(2, param.getCtnt());
				ps.setInt(3, param.getI_student());
				ps.executeUpdate();
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbCon.close(con, ps, rs);
		}		
	} 
}
