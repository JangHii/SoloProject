package com.myweb.www.service;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log를 사용하기위해
@RequiredArgsConstructor // final 등록시 생성자 사용
@Service // 서비스로 사용하겠다

public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;

	@Override
	public void register(BoardVO bvo) {
		bdao.register(bvo);
	}
	
}
