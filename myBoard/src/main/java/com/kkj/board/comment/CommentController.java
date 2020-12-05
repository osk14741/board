package com.kkj.board.comment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kkj.board.cmn.Auth;

@Controller
public class CommentController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired CommentService commentService;
	
	// 코멘트 리스트 가져오기(boardSeq)
	@Auth
	@RequestMapping(value = "comment/doSelectListComment.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectListComment(CommentVO commentVO) {
		LOG.debug("==comment/doSelectListComment.do==");
		
		List<CommentVO> parentCommentList = new ArrayList<CommentVO>();
		List<CommentVO> childCommentList = new ArrayList<CommentVO>();
		List<CommentVO> outList = new ArrayList<CommentVO>();
		
		List<CommentVO> tmpList = commentService.doSelectListByBoardSeq(commentVO);
		for(CommentVO vo : tmpList) {
			if(vo.getParentSeq() == 0) {
				parentCommentList.add(vo);
			} else {
				childCommentList.add(vo);
			}
		}
		
		for(CommentVO vo : parentCommentList) {
			outList.add(vo);
		}
		for(CommentVO vo : childCommentList) {
			outList.add(vo);
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(outList);
		return json;
	}
	
}
