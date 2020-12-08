package com.kkj.board.comment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kkj.board.cmn.Auth;
import com.kkj.board.member.MemberVO;

@Controller
public class CommentController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired CommentService commentService;
	
	// 코멘트 등록 
	@Auth
	@RequestMapping(value = "comment/doInsertComment.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsertComment(CommentVO commentVO, HttpServletRequest req) {
		LOG.debug("==comment/doInsertComment.do==");
		// boardSeq,parentSeq,content,regId
		
		HttpSession session = req.getSession();
		MemberVO sessionId = (MemberVO) session.getAttribute("sessionId");
		
		String regId = sessionId.getId();
		commentVO.setRegId(regId);
		
		commentService.doInsert(commentVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(commentVO);
		
		return json;
	}
	
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
		for(int i = childCommentList.size(); i > 0 ; i--) {
			outList.add(childCommentList.get(i - 1));
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(outList);
		return json;
	}
	
}
