package com.myweb.www.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class BoardTest {

	@Inject
	private BoardDAO bdao;
	
	
	@Test
	public void insertBoard() {
		log.info(">>>>> Test Insert in >>>>>");
	
	
	for(int i=0; i<300; i++) {
		BoardVO bvo = new BoardVO();
		bvo.setTitle("테스트의 제목" + i);
		bvo.setWriter("테스트의 작성자" + i);
		bvo.setContent("테스트의 내용" + i);
		
		// 글쓰기 bdao를 그대로 가져와야한다
		bdao.register(bvo);
		}
	}
	
	
}
