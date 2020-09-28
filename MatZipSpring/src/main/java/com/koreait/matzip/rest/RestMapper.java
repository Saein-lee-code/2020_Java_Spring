package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestFile;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;
// mapper 있는이유 : DAO 대신
// interface 가 부모역할을 함.
// xml 이랑 짝맞추기 쉬움.
// mybatis 가 얘네 맞는 클래스를 다만들어주고 일을 다해줌

@Mapper
public interface RestMapper {
	// public abstarct 생략
	int insRest(RestPARAM param);
	int insRestRecMenu(RestRecMenuVO param);
	int insRestMenu(RestRecMenuVO param);	

	int selRestChkUser(int i_rest);
	List<RestDMI> selRestList(RestPARAM param);
	RestDMI selRest(RestPARAM param);
	List<RestRecMenuVO> selRestRecMenus(RestPARAM param);
	List<RestRecMenuVO> selRestMenus(RestPARAM param);	
	
	int updAddHits(RestPARAM param);
	
	int delRest(RestPARAM param);
	int delRestRecMenu(RestPARAM param);
	int delRestMenu(RestPARAM param);
	
}
