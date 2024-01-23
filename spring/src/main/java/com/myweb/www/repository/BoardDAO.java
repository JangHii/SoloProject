package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> selectList(PagingVO pgvo);

	BoardVO detail(int bno);

	void updateCount(int bno);
	
	void modify(BoardVO bvo);

	int delete(int bno);

	int totalCount(PagingVO pgvo);

	void getCmtCount();

	long selectOneBno();



}
