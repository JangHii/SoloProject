package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	void register(BoardVO bvo);

	List<BoardVO> selectList();

	BoardVO detail(int bno);

	void updateCount(int bno);
	
	void modify(BoardVO bvo);

	int delete(int bno);

	void insert(BoardVO bvo); // 게시글300개 테스트를 위한 구문 


}
