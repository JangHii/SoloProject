package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		return cdao.insert(cvo);
	}

	@Override
	public PagingHandler getList(long bno , PagingVO pgvo) {
		List<CommentVO> list = cdao.getList(bno , pgvo);
		int cmtCount = cdao.totalCount(bno);
		PagingHandler ph = new PagingHandler(pgvo, cmtCount, list);
		return ph;
	}

	@Override
	public int edit(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int remove(long cno) {
		return cdao.delete(cno);
	}
	
}
