package com.koreait.matzip.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// 인터페이스는 부모역할 (자식을 카르키겠다는 말)
// 자식이 객체가된다는말.
// interface는 구현체 O 객체화 된다는말

// 추상클래스는 내용이없어서 객체화가 안됌. (구현체가 없다는말) 
// (추상클래스) 객체화를 막기위해 쓰기도 함. (다른 방법, 생성자앞에 private)

// int update 앞에 public abstract가 생략되있음 
public interface JdbcUpdateInterface {
	void update (PreparedStatement ps) throws SQLException;	
} 
