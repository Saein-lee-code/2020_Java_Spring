package com.koreait.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.koreait.board.vo.BoardVO;

// data access object
public class BoardDAO {
	// C create
	public static int boardInsert(BoardVO param) {		
		Connection con = null;
		PreparedStatement ps = null;
		/*
		 * CREATE SEQUENCE SEQ_BOARD
			INCREMENT BY 1
			START WITH 10;		 
		 */
		String sql = " INSERT INTO T_BOARD (I_BOARD, TITLE, CTNT, I_STUDENT) " 
//				" SELECT NVL(MAX(I_BOARD),0) + 1 AS COUNT, ?, ?, ? FROM T_BOARD ";
				+ " VALUES"
				+ " (SEQ_BOARD.NEXTVAL, ?,?,?) ";
		int result = 0;
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);					
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCtnt());
			ps.setInt(3, param.getI_student());
			result = ps.executeUpdate();					
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			DbCon.close(con, ps);
		}
		return result;
	} 
	// R read
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
				vo.setI_board(param.getI_board());
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
	
	public static int boardUpdate(BoardVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = " UPDATE T_BOARD SET TITLE = ?, CTNT = ? WHERE I_BOARD = ? ";
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCtnt());
			ps.setInt(3, param.getI_board());
			result = ps.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DbCon.close(con, ps);
		}
		return result;
	}
	
	public static int boardDelete(int i_board) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = " DELETE FROM T_BOARD WHERE I_BOARD = ? ";
		int result = 0;
		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_board);
			result = ps.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbCon.close(con, ps);
		}
		return result;
	}
}
