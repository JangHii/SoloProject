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
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNO , int qwy) {
		this.pageNo = pageNO;
		this.qty = qwy;
	}
	
	// 시작 번지 구하기
	public int getPageStart() {
		return (this.pageNo-1) * qty;
	}
	
}
