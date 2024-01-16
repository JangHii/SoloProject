package com.myweb.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j // log를 사용하기 위해
@RequestMapping("/board/*") // board로 시작되는 모든 요청을 여기서 처리하겠다
@RequiredArgsConstructor // final로 매개변수를 하면 생성자를 생성해준다
@Controller // 해당 클래스는 controller로 등록이된다.
public class BoardController {

	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("register")
	public String register(BoardVO bvo) {
		log.info(">>>>> bvo 들어가있는지 확인하자 >>>>> {} " , bvo);
		
		bsv.register(bvo);
		
		return "index";
	}
	
}
