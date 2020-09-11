package com.koreait.matzip;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.matzip.db.JdbcSelectInterface;
import com.koreait.matzip.db.JdbcTemplate;
import com.koreait.matzip.vo.CodeDomain;

public class CommonDAO {
	public static List<CodeDomain> selCodeList(final int i_m){
		List<CodeDomain> list = new ArrayList(); // generic  <CodeDomain> list.뭐시기 다 CodeDomain만 나옴. 들어갈때 코드도메인 나올때도 코드도메인.
		
		// generic 안붙으면 들어갈때 아무거나 들어가고 나올때 체크함 
		// List 와 ArrayList. 
		// List는 interface(부모역할만 함). ArrayList는 객체.
		// 유연하다. 왜냐면 new ArrayList를 LinkedList로 바꿔도 문제가 없기때문
		String sql = " SELECT i_m, cd, val FROM c_code_d WHERE i_m = ? ";
		
		JdbcTemplate.executeQuery(sql, new JdbcSelectInterface() {

			@Override
			public void prepared(PreparedStatement ps) throws SQLException {				
					ps.setInt(1, i_m);								
			}

			@Override
			public void executeQuery(ResultSet rs) throws SQLException {
				while(rs.next()) {
					CodeDomain cd = new CodeDomain();
					cd.setVal(rs.getNString("val"));
					cd.setCd(rs.getInt("cd"));
					list.add(cd);
				}				
			}			
		});
		
		return list;
	}
}
