package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(long bno);

	void update(BoardVO bvo);

	void delete(BoardVO bvo);

	int upReadCount(@Param("bno") long bno, @Param("count") int count);

	int totalCount(PagingVO pgvo);

	void getCmtCount();

	long selectOneBno();





	





}
