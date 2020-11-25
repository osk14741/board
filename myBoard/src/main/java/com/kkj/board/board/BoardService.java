package com.kkj.board.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired BoardDao boardDao;
	
	public BoardVO doSelectOne(BoardVO boardVO) {
		return boardDao.doSelectOne(boardVO);
	}

	public List<BoardVO> doSelectList(BoardVO boardVO) {
		return boardDao.doSelectList(boardVO);
	}

	public int doInsert(BoardVO boardVO) {
		return boardDao.doInsert(boardVO);
	}

	public int doUpdate(BoardVO boardVO) {
		return boardDao.doUpdate(boardVO);
	}

	public int doDelete(BoardVO boardVO) {
		return boardDao.doDelete(boardVO);
	}
}
