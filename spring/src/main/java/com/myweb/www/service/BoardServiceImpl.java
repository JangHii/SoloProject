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
		return bdao.getList(pgvo);
	}

	@Transactional
	@Override
	public BoardDTO getDetail(long bno) {
		bdao.upReadCount(bno, 1);
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		return bdto;
	}
	
	@Transactional
	@Override
	public int modify(BoardDTO bdto) {
		int isOk = bdao.upReadCount(bdto.getBvo().getBno(), -2);
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bdto.getBvo().getBno());
				isOk += fdao.insertFile(fvo);
			}
		}
		return isOk;
		
	}

	@Override
	public void remove(BoardVO bvo) {
		cdao.deleteAll(bvo);
		fdao.deleteAll(bvo);
		bdao.delete(bvo);	
	}

	@Override
	public int totalCount(PagingVO pgvo) {
		return bdao.totalCount(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		return fdao.deleteFile(uuid);
	}



	
}
