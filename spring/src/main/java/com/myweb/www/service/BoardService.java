package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO getDetail(long bno);

	int modify(BoardDTO bdto);

	void remove(BoardVO bvo);

	int totalCount(PagingVO pgvo);

	int removeFile(String uuid);





}
