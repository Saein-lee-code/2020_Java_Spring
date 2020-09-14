package com.koreait.matzip.vo;

public class RestaurantDomain extends RestaurantVO {
	private String userNm;
	private int cntHits;
	private int cntFavorite;
	private String cdCategoryName;
	
	public int getCntHits() {
		return cntHits;
	}
	public void setCntHits(int cntHits) {
		this.cntHits = cntHits;
	}
	public int getCntFavorite() {
		return cntFavorite;
	}
	public void setCntFavorite(int cntFavoite) {
		this.cntFavorite = cntFavoite;
	}
	public String getCdCategoryName() {
		return cdCategoryName;
	}
	public void setCdCategoryName(String cdCategoryName) {
		this.cdCategoryName = cdCategoryName;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}	
}
