package com.myweb.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j // log를 사용하기 위해
@RequestMapping("/board/*") // board로 시작되는 모든 요청을 여기서 처리하겠다
@RequiredArgsConstructor // final로 매개변수를 하면 생성자를 생성해준다
@Controller // 해당 클래스는 controller로 등록이된다.
public class BoardController {

	private final BoardService bsv;
	private final FileHandler fh;
	
	// 조회할때만 사용
	// URL에 데이터가 노출됨
	@GetMapping("/register")
	public void register() {}
	
	
	// 데이터를 저장할때 사용
	// URL의 데이터가 노출되지않음
	@PostMapping("/register")
	public String register(BoardVO bvo , @RequestParam(name="files" , required = false) MultipartFile[] files) {
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		int isOk = bsv.register(new BoardDTO(bvo , flist));
		return "index";
	}
	
	
	@GetMapping("/list")
	public void list (Model m, PagingVO pgvo) {
		log.info(">>> pgvo >>> {}", pgvo);
		List<BoardVO> list = bsv.getList(pgvo);
		int totalCount = bsv.totalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		m.addAttribute("list", list);
		m.addAttribute("ph", ph);
	}
	
	
	@GetMapping({"/detail", "/modify"})
	public void detail (Model m, @RequestParam("bno") long bno) {
		m.addAttribute("bdto", bsv.getDetail(bno));
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo , RedirectAttributes re ,@RequestParam(name = "files" , required = false) MultipartFile[] files) {
		
		List<FileVO> flist = null;
		System.out.println(files);
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.modify(new BoardDTO(bvo , flist));
		re.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail?bno=" + bvo.getBno();
	}
	
	
	@GetMapping("/remove")
	public String remove(BoardVO bvo) {
		bsv.remove(bvo);
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value="/file/{uuid}", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid") String uuid) {
		int isOk = bsv.removeFile(uuid);
		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
					new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
}
