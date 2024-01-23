package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log를 사용하기위해
@RequiredArgsConstructor // final 등록시 생성자 사용
@Service // 서비스로 사용하겠다
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;
	private final CommentDAO cdao;
	private final FileDAO fdao;

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		int isOk = bdao.register(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			long bno = bdao.selectOneBno();
			
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk += fdao.insertFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.getCmtCount();
		return bdao.selectList(pgvo);
	}
	
	@Override
	public int gettotalCount(PagingVO pgvo) {
		return bdao.totalCount(pgvo);
	}

	@Override
	public BoardVO getdetail(int bno) {
		log.info(" >>>>> bno 들어오는지 확인하자 >>>>> {}" , bno);
		bdao.updateCount(bno);
		return bdao.detail(bno);
	}

	@Override
	public void modify(BoardVO bvo) {
		log.info(" >>>>> bvo 들어오는지 확인하자 >>>>> {}" , bvo);
		bdao.modify(bvo);
	}

	@Override
	public int delete(int bno) {
		log.info(" >>>>> bno 들어오는지 확인하자 >>>>> {}" , bno);
		int isOk = bdao.delete(bno);
		return isOk;
	}



	
}
