package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	void register(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getdetail(int bno);

	void modify(BoardVO bvo);

	int delete(int bno);


}
