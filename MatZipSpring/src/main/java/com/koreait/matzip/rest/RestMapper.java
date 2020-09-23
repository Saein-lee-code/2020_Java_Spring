package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;

@Mapper
public interface RestMapper {
	// public abstarct 생략
	int insRest(RestPARAM param);
	List<RestDMI> selRestList(RestPARAM param);
	RestDMI selRest(RestPARAM param);
	List<RestRecMenuVO> selRestRecMenus(RestPARAM param);
	int delRest(RestPARAM param);
	int delRestRecMenu(RestPARAM param);
	int delRestMenu(RestPARAM param);
	int insRestRecMenu(RestRecMenuVO param);
	int delRecMenu(RestPARAM param);
}
