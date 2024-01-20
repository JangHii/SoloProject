package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {

	private int pageNo; // 현재 페이지 번호
	private int qty; // 한페이지당 표시될 리스트 개수
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo , int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	// 시작 번지 구하기
	public int getStartPage() {
		return (this.pageNo-1) * qty;
	}
	
	// type의 값을 배열로 생성
	// 복합 타입의 키워드일경우 각자 검색해야되기때문에 배열을 생성
	public String[] getTypetoArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
} 
