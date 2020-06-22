package com.kita.first.level3;

public class BoardVO {
	// private은 같은 클래스 내에서만 접근 가능
	private String title;
	private String content;
	private int writeId;
	// 객체를 만들 때 마다 다른 값을 저장시키는 용도
	
	BoardVO(){
		//생성자가 하나도 없을때는 기본생성자를 자동 생성해주지만
		//생성자가 있을때는 자동생성 해주지않아서 넣어줘야됌.
		
	}
	public BoardVO(String s1, String s2, int n){
		super();
		this.title = s1;
		this.content = s2;
		this.writeId = n;
	}
	// getter (return) / setter (no return)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getWriteId() {
		return writeId;
	}
	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}
}
