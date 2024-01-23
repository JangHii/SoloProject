package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getdetail(int bno);

	void modify(BoardVO bvo);

	int delete(int bno);

	int gettotalCount(PagingVO pgvo);


}
