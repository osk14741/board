package com.kkj.board.comment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired CommentDao commentDao;
	final static Logger LOG = LoggerFactory.getLogger(CommentService.class);
	
	public int doDeleteRecommendUser(CommentRecommendVO commentRecommendVO) {
		return commentDao.doDeleteRecommendUser(commentRecommendVO);
	}
	
	public int doInsertRecommendUser(CommentRecommendVO commentRecommendVO) {
		return commentDao.doInsertRecommendUser(commentRecommendVO);
	}
	
	public int doDeleteChildren(CommentVO commentVO) {
		return commentDao.doDeleteChildren(commentVO);
	}
	
	public int doInsert(CommentVO commentVO) {
		return commentDao.doInsert(commentVO);
	}
	
	public int doDelete(CommentVO commentVO) {
		return commentDao.doDelete(commentVO);
	}
	
	public int doUpdate(CommentVO commentVO) {
		return commentDao.doUpdate(commentVO);
	}
	
	public CommentVO doSelectOne(CommentVO commentVO) {
		return commentDao.doSelectOne(commentVO);
	}
	
	public List<CommentVO> doSelectListByBoardSeq(CommentVO commentVO){
		return commentDao.doSelectListByBoardSeq(commentVO);
	}
	
}
